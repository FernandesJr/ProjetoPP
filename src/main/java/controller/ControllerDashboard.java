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
 * Servlet implementation class ControllerDashboard
 */
public class ControllerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerDashboard() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void dashboard(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, SQLException, ServletException {
		
		//Sempre que preciso montar o dashboar chamo esse método
		
		Usuario usuario = new Usuario(id);
		usuario.buscarUser();
		String sessao = (String) request.getSession().getAttribute("sessaoUser");
		
		//Validando se a sessao e usuario estão de acordo
		
		if(sessao != null && sessao.equals(usuario.getEmail())) {
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("deslogar.jsp");
		}
	}
}
