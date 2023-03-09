package view;

import java.io.BufferedReader;
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
import org.json.simple.parser.JSONParser;



/**
 * Servlet Filter implementation class validationFilter
 */
@WebFilter("/validationSignUp")
public class validationSignUp extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
//     */
//    public validationFilter() {
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
		String userInput="";
		String jsoninput ="";
		JSONObject responseObj=new JSONObject();
		BufferedReader reader = request.getReader();
		JSONObject resObj = new JSONObject();
		while((userInput = reader.readLine())!=null) {
			jsoninput+=userInput;
		}
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(jsoninput);
		} catch (org.json.simple.parser.ParseException e1) {
			// TODO Auto-generated catch block
			resObj.put("statusCode", 500);
			resObj.put("message", "error occured plz give valid input !!");
			response.getWriter().append(resObj.toString());		
		}
		
		String name=(String) json.get("name");
		String dob=(String) json.get("date");
		String phone=(String) json.get("phone");
		String mail=(String) json.get("mail");
		String pan=(String) json.get("pan");
		String marital=(String) json.get("marital");
		String gender=(String) json.get("gender");
		String password=(String) json.get("password");
		String bankAccountNumber=(String) json.get("bankAccountNumber");
		String ifscCode=(String) json.get("ifsc");
		System.out.println(dob);
		System.out.println(Pattern.matches("^[a-zA-Z\\s]+$", name));
		System.out.println(Pattern.matches("^[789]\\d{9}$", phone));
		System.out.println(Pattern.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$", mail));
		System.out.println(pan.replaceAll("[A-Z0-9]", "").length()==0);
		System.out.println(password.length()>=8);
		System.out.println(bankAccountNumber.replaceAll("[0-9]", "").length()==0);
		System.out.println(ifscCode.replaceAll("[A-Z0-9]","").length()==0);
		if(Pattern.matches("^[a-zA-Z\\s]+$", name)&&Pattern.matches("^[789]\\d{9}$", phone)&&Pattern.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$", mail)&&pan.replaceAll("[A-Z0-9]", "").length()==0&& password.length()>=8&&bankAccountNumber.replaceAll("[0-9]", "").length()==0&&ifscCode.replaceAll("[	A-Z0-9]","").length()==0) {
			request.setAttribute("userDetails", json);
			chain.doFilter(request, response);
		}else {
			responseObj.put("statusCode", 500);
			responseObj.put("message", "invalid input");
			response.getWriter().append(responseObj.toString());
		}
//		System.out.println(name);
		
//		String phone=request.getParameter("phone");
//		String gmail=request.getParameter("gmail");
//		try {
//			PreparedStatement pstmt = database.dbConnection.prepareStatement("select * from DematAccount where phone = ?");
//			pstmt.setString(1, phone);
//			ResultSet rs=pstmt.executeQuery();
//			if(rs.next()) {
//				responseObj.put("statusCode", 500);
//				responseObj.put("message", "This phone number already exists,please give different phone number");
//				response.getWriter().append(responseObj.toString());
//			}
//			else {
//				PreparedStatement pstmt1 = database.dbConnection.prepareStatement("select * from DematAccount where email = ?");
//				pstmt1.setString(1, gmail);
//				ResultSet rs1=pstmt1.executeQuery();
//				if(rs.next()) {
//					responseObj.put("statusCode", 500);
//					responseObj.put("message", "This gmail already exists,please give different gmail");
//					response.getWriter().append(responseObj.toString());
//				}
//				else {
				
//				}
//			}
//		}
//		catch(Exception e) {
//			
//		
//		}
//		
		
		
//		System.out.println("first filter");
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}

}
