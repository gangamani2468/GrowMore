package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import Connection.database;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//	
//    public Login() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String phone=request.getParameter("phone");
		System.out.println(phone+"phone");
		String pass=request.getParameter("pass");
		System.out.println(pass);
	

		String responseText="";
		try {
			PreparedStatement stmt=database.dbConnection.prepareStatement("select * from DematAccount where phoneNo=?");
			stmt.setString(1, phone);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(9).equals(pass)) {
					while(true) {
						UUID uuid=UUID.randomUUID();
						PreparedStatement stmt2=database.dbConnection.prepareStatement("select * from sessions where sessionid=?");
						stmt2.setString(1,""+uuid);
						ResultSet rs1=stmt2.executeQuery();
						if(!rs1.next()) {
							System.out.println(uuid);
							HttpSession session=request.getSession();
							session.setAttribute("SESSION-ID",""+uuid);
							session.setAttribute("phone",phone);
							PreparedStatement insertSession=database.dbConnection.prepareStatement("insert into sessions values(?,?)");
							insertSession.setString(1, ""+uuid);
							insertSession.setString(2, rs.getString(3));
							insertSession.execute();
							stmt2.setString(1,""+uuid);
							response.getWriter().append("success");
							session.getAttribute("SESSION-ID");
//							((ServletRequest) response).setAttribute("name",phone);
//							((ServletRequest) response).setAttribute("pass",pass);
//							response.sendRedirect("");
							break;
							
							
						}
					}
					 //Generates random UUID  
					
					
					responseText="Welcome,"+rs.getString(1)+"!";
				}
				else {
					responseText="Password Incorrect";
				}
			}
			else {
				responseText="Incorrect Phone Number";
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append(responseText);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
	
	
	
}

