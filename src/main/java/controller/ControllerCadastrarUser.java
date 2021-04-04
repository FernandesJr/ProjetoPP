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
 * Servlet implementation class ControllerCadastrarUser
 */
public class ControllerCadastrarUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerCadastrarUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(email,senha);
		usuario.setNome(nome);
		
		boolean valida = false;
		try {
			valida = usuario.buscarUserSessao();
		} catch (SQLException e) {
			System.out.println("Não consegui fazer verficar se o usuario já contém no DB"+e);
		}
		
		if(valida) {
			System.out.println("Email já cadastrado.");
			String msg = "<script>alert('Email já cadastrato. Por favor verifique.');</script>";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrarUser.jsp");
			rd.forward(request, response);
			
		}else {
			
			//Cadastrando o usuário no banco de dados
			try {
				usuario.cadastarUsuario();
				System.out.println("Cadastrado com sucesso.");
				String msg = "<script>alert('Cadastrado com sucesso.')</script>";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
