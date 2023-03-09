package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import LogicMangement.SignUpDatabase;

/**
 * Servlet implementation class otpCheck
 */
@WebServlet("/otpCheck")
public class otpCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public otpCheck() {
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
		HttpSession session=request.getSession();
		String otp=request.getParameter("otp");
		String serverOtp=(String) session.getAttribute("otp");
		String name=(String) session.getAttribute("userName");
		System.out.println(name);
		String dob=(String) session.getAttribute("date");
		String phone=(String) session.getAttribute("phoneNumber");
		String mail=(String) session.getAttribute("mail");
		String pan=(String) session.getAttribute("pan");
		String marital=(String) session.getAttribute("marital");
		String gender=(String) session.getAttribute("gender");
		String password=(String) session.getAttribute("password");
		String bankAccountNumber=(String) session.getAttribute("bankAccount");
		System.out.println(gender);
		String ifscCode=(String) session.getAttribute("ifsc");
		JSONObject responseText=new JSONObject();
		
		if(otp.equals(serverOtp)) {
			try {
				if(new SignUpDatabase().exportSignUp(name, dob, phone, mail, pan, marital, gender, password,ifscCode, bankAccountNumber)) {
					System.out.println("win....");
					responseText.put("message","account success");
					
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseText.put("message","invalid input");
		}
		
		response.getWriter().append(responseText.toString());
	}

}
