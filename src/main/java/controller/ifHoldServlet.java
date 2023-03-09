package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import LogicMangement.mainClass;
import LogicMangement.priceData;
import Model.Holdings;

/**
 * Servlet implementation class ifHoldServlet
 */
@WebServlet("/ifHoldServlet")
public class ifHoldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifHoldServlet() {
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
		String Company =request.getParameter("company");
		JSONObject stock=priceData.output;
		JSONObject data=(JSONObject) stock.get(Company);
		Double stockPrice=Double.parseDouble(""+data.get("ltp"));
		System.out.println(stockPrice);
		List<Holdings> holdList1=mainClass.user.getHoldlist();
		JSONObject holdStock=null;
		for (Holdings holds:holdList1) {
			
			if(holds.getShareName().equals(Company)) {
				holdStock=new JSONObject();
				
				holdStock.put("shareAvg", holds.getShareAvgPrice());
				holdStock.put("quantity", holds.getQuantity());
				holdStock.put("profitAndLoss",holds.getProfitAndLoss());
				holdStock.put("InvestedAmount", holds.getInvestedAmount());
				
				break;
			} 
			
		}//
		JSONObject output=new JSONObject();
		if (holdStock==null){
			holdStock=new JSONObject();
			holdStock.put("shareAvg", 00.00);
			holdStock.put("quantity",00.00);
			holdStock.put("profitAndLoss",00.00);
			holdStock.put("InvestedAmount", 00.00);
			 
			output.put("holds", holdStock);
			

			}
		else {
			output.put("holds", holdStock);
		}
		if (Double.parseDouble(""+holdStock.get("profitAndLoss"))<0.00){
			output.put("color", "red");
		
		}
		else {
			output.put("color", "green");
		}
				
		output.put("ltp",stockPrice);
		response.getWriter().append(output.toString());
	}

}
