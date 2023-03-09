package view;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Connection.database;

/**
 * Servlet Filter implementation class AuthenticationSignUp
 */
@WebFilter("/AuthenticationSignUp")
public class AuthenticationSignUp extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
//    public AuthenticationSignUp() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		JSONObject responseObj=new JSONObject();
		
		boolean valid=false;
		
			
				
				try {
					JSONObject json=(JSONObject) request.getAttribute("userDetails");
					String phoneNo=(String) json.get("phone");
					String mail=(String) json.get("mail");
					String pan=(String) json.get("pan");
					
					
					PreparedStatement psmt =database.dbConnection.prepareStatement("select * from DematAccount where  phoneNo= ? or email=? or pan=?");
					psmt.setString(1,phoneNo);
					psmt.setString(2, mail);
					psmt.setString(3, pan);
					ResultSet rs=psmt.executeQuery();
					if(!rs.next()) {
						valid=true;
						 System.out.println("balama");
					}
					else {
						responseObj.put("statusCode", 400);
						responseObj.put("message", "Unauthenticated User");
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					responseObj.put("statusCode", 500);
					responseObj.put("message", "Some unexpected error occured.");
				}
			
		
		if(valid) {
			chain.doFilter(request, response);
		}
		else {
			response.getWriter().append(responseObj.toString());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}

}
