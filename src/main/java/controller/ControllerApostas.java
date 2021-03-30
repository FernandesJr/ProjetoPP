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
 * Servlet implementation class ControllerApostas
 */
public class ControllerApostas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerApostas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("cheguei no ControllerApostas - sessao" + request.getSession().getAttribute("sessaoUser"));
		
		int idUser = Integer.parseInt(request.getParameter("id"));

		Usuario usuario = new Usuario(idUser);
		try {
			usuario.buscarUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sessao = (String) request.getSession().getAttribute("sessaoUser");
		
		//Validando se a sessao e usuario estão de acordo
		
		if(sessao != null && sessao.equals(usuario.getEmail())) {
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("apostas.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("deslogar.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
