package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.database;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public logout() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		
		try {
			PreparedStatement deleteSession=database.dbConnection.prepareStatement("delete from sessions where  sessionid =? ");
			deleteSession.setString(1, ""+session.getAttribute("SESSION-ID"));
			deleteSession.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("SESSION-ID");
		session.removeAttribute("phone");
		response.getWriter().append("success");
		
	}

}
