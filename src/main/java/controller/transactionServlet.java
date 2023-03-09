package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import LogicMangement.buyAndSellService;

/**
 * Servlet implementation class transactionServlet
 */
@WebServlet("/transactionServlet")
public class transactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public transactionServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		Double amount=Double.parseDouble(request.getParameter("amount"));
		JSONObject responseObj=new JSONObject();
		if(action.equals("addMoney")) {
			if(new buyAndSellService().addMoney(amount)) {
				
				responseObj.put("statusCode", 200);
				responseObj.put("message", "successfull");
			}
			else {
				responseObj.put("statusCode", 400);
				responseObj.put("message", "invalid input");
			}
		}
		else {
			if(new buyAndSellService().withdrawMoney(amount)) {
				
				responseObj.put("statusCode", 200);
				responseObj.put("message", "successfull");
			}
			else {
				responseObj.put("statusCode", 400);
				responseObj.put("message", "invalid input");
			}
		}
		response.getWriter().append(responseObj.toString());
//		doGet(request, response);
	}

}
