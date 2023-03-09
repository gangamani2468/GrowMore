package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import LogicMangement.OtpGenerateJava;
import LogicMangement.SignUpDatabase;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json=(JSONObject) request.getAttribute("userDetails");

		
		String name=(String) json.get("name");
		System.out.println(name);
		String dob=(String) json.get("date");
		String phone=(String) json.get("phone");
		String mail=(String) json.get("mail");
		String pan=(String) json.get("pan");
		String marital=(String) json.get("marital");
		String gender=(String) json.get("gender");
		String password=(String) json.get("password");
		String bankAccountNumber=(String) json.get("bankAccountNumber");
		System.out.println(gender);
		String ifscCode=(String) json.get("ifsc");
		HttpSession session=request.getSession();
		session.setAttribute("userName", name);
		session.setAttribute("date", dob);
		session.setAttribute("phoneNumber",phone);
		session.setAttribute("mail", mail);
		session.setAttribute("pan", pan);
		session.setAttribute("marital", marital);
		session.setAttribute("gender", gender);
		session.setAttribute("password", password);
		session.setAttribute("bankAccount", bankAccountNumber);
		session.setAttribute("ifsc", ifscCode);
		
		System.out.println("kiii");
		response.getWriter().append("1");
		
	}
	
		
//		while(true) {
//			dematAccountNumber=(long)(Math.random()*1000000000000000l)+1600000000000000l; 
//			Iterator sc=MainClass.clients.iterator();
//			while(sc.hasNext()) {
//				DematAccount data=(DematAccount)sc.next();
//				if(data.getDematAccountNumber().equals(dematAccountNumber)) {
//				continue x;	
//				}		
//			}
		
	
	

}
