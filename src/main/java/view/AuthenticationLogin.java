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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Connection.database;



/**
 * Servlet Filter implementation class AuthenticationLogin
 */
@WebFilter("/AuthenticationLogin")
public class AuthenticationLogin extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
//     */
//    public AuthenticationLogin() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see Filter#destroy()
	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
//		chain.doFilter(request, response);
		JSONObject responseObj=new JSONObject();
		
		boolean valid=false;
		String phone =request.getParameter("phone");
		String pass =request.getParameter("pass");
			
				
				try {

					PreparedStatement psmt =database.dbConnection.prepareStatement("select * from DematAccount where phoneNo = ?");
					psmt.setString(1,phone);
				
					ResultSet rs=psmt.executeQuery();
					if(rs.next()&&rs.getString(9).equals(pass)) {
						valid=true;

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
