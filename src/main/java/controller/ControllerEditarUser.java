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
 * Servlet implementation class ControllerEditarUser
 */
public class ControllerEditarUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerEditarUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("action");
		if(acao != null) {
			//Excluir usuário
			try {
				this.deleteUsuario(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			//Indo em direção a editarUser.jsp
			this.auth(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			this.updateUsuario(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void auth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("sessaoUser") != null) {
			
			String sessao = (String) request.getSession().getAttribute("sessaoUser");
			
			int idUser = Integer.parseInt(request.getParameter("id"));
			
			Usuario usuario = new Usuario(idUser);
			boolean auth = false;
			try {
				auth = usuario.buscarUser();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Verficando se o acesso está em conformidade com a sessão ativa
			if(auth && usuario.getEmail().equals(sessao)) {
				
				//Enviando informações do usuário para editar
				request.setAttribute("usuario", usuario);
				RequestDispatcher rd = request.getRequestDispatcher("editarUser.jsp");
				rd.forward(request, response);
			}else {
				System.out.println("Sessão inválida");
				response.sendRedirect("deslogar.jsp");
			}
		}else {
			response.sendRedirect("deslogar.jsp");
		}
	}
	
	public void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(id));
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		if(usuario.updateUsuario()) {
			
			usuario.calcularDash();
			String msg = "<script>alert('Atualizado com sucesso.')</script>";
			request.setAttribute("msg", msg);
			request.setAttribute("usuario", usuario);
			request.getSession().setAttribute("sessaoUser", usuario.getEmail());
			
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
			
		}else {
			
			String msg = "<script>alert('Email já utilizado por outro usuário. ESCOLHA OUTRO EMAIL')</script>";
			request.setAttribute("msg", msg);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("editarUser.jsp");
			rd.forward(request, response);
			
			System.out.println("NÃO Consegui atualizar");
		}
		
		
		
	}
	
	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String id = request.getParameter("id");
		Usuario usuario = new Usuario(Integer.parseInt(id));
		usuario.excluirUsuario();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("deslogar.jsp");
		rd.forward(request, response);
		
	}

}
