package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aposta;
import model.Usuario;

/**
 * Servlet implementation class ControllerNovaAposta
 */
public class ControllerNovaAposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerNovaAposta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		if(action.equals("novaaposta")) {
			this.novaAposta(request, response);
		}else if(action.equals("dashboard")) {
			try {
				this.dashboard(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Capturando o valor da aposta
		double valorAposta = Double.parseDouble(request.getParameter("valorAposta"));
		System.out.println("Valor da aposta foi de: " + valorAposta);
		
		//Capturando os números escolhidos pelo usuario
		String[] numEscolhidos = request.getParameterValues("numEscolhido");
		
		// mensagem caso valor de números inválidos
		String msg = "";
		
		//Caputrando a sessao
		String sessao = (String) request.getSession().getAttribute("sessaoUser");
		
		//Buscando demais informações do usuário para associar a aposta feita
		Usuario usuario = new Usuario();
		usuario.setEmail(sessao);
		try {
			usuario.buscarUserSessao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(numEscolhidos == null) {
			System.out.println("NULL");
			msg = "<script>alert('Você não escolheu seus números. Por favor verifique.')</script>";
			request.setAttribute("msg", msg);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("novaaposta.jsp");
			rd.forward(request, response);
		}else if(numEscolhidos.length > 5) {
			System.out.println("Escolheu mais que cinco por favor verifique.");
			msg = "<script>alert('Você escolheu MAIS que 5 números. Por favor verifique.')</script>";
			request.setAttribute("msg", msg);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("novaaposta.jsp");
			rd.forward(request, response);
			
		}else if(numEscolhidos.length < 5) {
			System.out.println("Selecione 5 números.");
			msg = "<script>alert('Você escolheu MENOS que 5 números. Por favor verifique.')</script>";
			request.setAttribute("msg", msg);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("novaaposta.jsp");
			rd.forward(request, response);
		}else{
			
			for(String v: numEscolhidos) {
				System.out.println("Números escolhidos no FRONT: " + v);
			}
			
			//Criando a aposta
			Aposta aposta = new Aposta(numEscolhidos, usuario, valorAposta);
			int idAposta = aposta.sorteio();
			int erros = 5 - aposta.getAcertos(); //Calculando quantidade de erros da aposta
			
			
			//Convertendo vetor em array o jsp não entedeu o vetor :@
			ArrayList<String> sorteados  = new ArrayList<String>();
			ArrayList<String> escolhidos = new ArrayList<String>();
			
			for (int n : aposta.getNumSorteados()) {
				sorteados.add(String.valueOf(n));
			}
			
			for (String n : numEscolhidos) {
				escolhidos.add(n);
			}
			
			//Alterando as cores para Danger caso erre a aposta
			//<span class="badge badge-pill badge-primary p-3 text-lg"></span>
			
			ArrayList<String> escolhidosCor = new ArrayList<String>();
			ArrayList<String> escolhidosAcertos = new ArrayList<String>();
			
			//Analizando qaul acertou
			for(String sort : sorteados) {
	
				for(String esco: escolhidos) {
					if(sort.equals(esco)) {
						escolhidosCor.add("<span class='badge badge-pill badge-primary p-3 text-lg'>"+esco+"</span>");
						escolhidosAcertos.add(esco);
					}
				}
			}
			
			//Adicionando a cor vermelha aos números errados
			for(String esco1 : escolhidos) {
				boolean acertou = false;
				for (String acer : escolhidosAcertos) {
					if(acer.equals(esco1)) {
						acertou = true;
					}
				}
				if(!acertou) {
					escolhidosCor.add("<span class='badge badge-pill badge-danger p-3 text-lg'>"+esco1+"</span>");
				}
			}
			
			
			//Setando atributos para vizualização do usuário no front-end
			request.setAttribute("erros", String.valueOf(erros));
			request.setAttribute("ganho", String.valueOf(aposta.getLucroUser()));
			request.setAttribute("acertos", String.valueOf(aposta.getAcertos()));
			request.setAttribute("numSorteados", sorteados);
			request.setAttribute("numEscolhidos", escolhidosCor); //Veio da requisição e vai proseguir adiante apenas com as cores alteradas
			request.setAttribute("idAposta", String.valueOf(idAposta));
			request.setAttribute("usuario", usuario);
			
			
			//Despachandoa requisição
			RequestDispatcher rd = request.getRequestDispatcher("resultado.jsp");
			rd.forward(request, response);
				
		}
		
	}
	
	protected void novaAposta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("sessaoUser") != null) {
					
			String sessao = (String) request.getSession().getAttribute("sessaoUser");
			
			int idUser = Integer.parseInt(request.getParameter("user"));
			
			Usuario usuario = new Usuario(idUser);
			boolean auth = false;
			try {
				auth = usuario.buscarUser();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("validando email e senha: EMAIL>" + usuario.getEmail() + "SESSAO>"+sessao);
			if(auth && usuario.getEmail().equals(sessao)) {
				request.setAttribute("usuario", usuario);
				RequestDispatcher rd = request.getRequestDispatcher("novaaposta.jsp");
				rd.forward(request, response);
			}else {
				System.out.println("Sessão inválida");
				response.sendRedirect("deslogar.jsp");
			}
		}else {
			response.sendRedirect("deslogar.jsp");
		}
	}
	
	private void dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		
		//Enviando para o método resposavel por montar o dashboard
		
		if(request.getSession().getAttribute("sessaoUser") != null) {
			
			//Capturando do dashboard
			int idUser = Integer.parseInt(request.getParameter("id"));
			
			//Enviando para o controller responsável de montar o dashboard
			ControllerDashboard board = new ControllerDashboard();
			board.dashboard(request, response, idUser);
			
		}
		
	}
}
