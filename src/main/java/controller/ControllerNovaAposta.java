package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		doGet(request, response);
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
