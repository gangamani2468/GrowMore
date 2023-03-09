package view;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;

import Connection.database;



/**
 * Servlet Filter implementation class validationLogin
 */
@WebFilter("/validationLogin")
public class validationLogin extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public validationLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		JSONObject responseObj=new JSONObject();
		
		String phone=request.getParameter("phone");
		String pass =request.getParameter("pass");
//		if(Pattern.matches("/^[6789]\\d{9}$/", phone)&&Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16}$", pass)) {
//			chain.doFilter(request, response);
//		}
		System.out.println(Pattern.matches("^[789]\\d{9}$", phone));
		System.out.println(Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16}$", pass));
		if(Pattern.matches("^[789]\\d{9}$", phone)&&Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16}$", pass)) {
			chain.doFilter(request, response);
		}
		else {
			responseObj.put("statusCode", 500);
			responseObj.put("message", "This wrong information");
			response.getWriter().append(responseObj.toString());

		}
		
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}

}
