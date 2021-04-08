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
 * Servlet implementation class ControllerLogin
 */

public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ControllerLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(request.getSession().getAttribute("sessaoUser") == null) {
				response.sendRedirect("index.jsp");
			}
		}catch(IOException e) {
			response.sendRedirect("index.jsp");
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Capturando a entrada de dados via formulario
		String nome = (String) request.getParameter("email");
		String senha = (String) request.getParameter("senha");
		Usuario usuario = new Usuario(nome, senha);
		
		try {
			//Validando user no BD
			if(usuario.validarUser()) {
				usuario.calcularDash();
				String sessao = usuario.getEmail();
				request.getSession().setAttribute("sessaoUser", sessao);
				request.setAttribute("usuario", usuario);
				RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
				rd.forward(request, response);
				System.out.println("Enviei tudo para o DASHBOARD");
			}else {
				String msg = "<script> alert('Usuário ou Senha inválido.'); </script>";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRO-CONTROLLERLOGIN "+e);
		}
	}

}
