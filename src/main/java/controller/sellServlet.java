package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import LogicMangement.buyAndSellService;

/**
 * Servlet implementation class sellServlet
 */
@WebServlet("/sellServlet")
public class sellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
//     */
//    public sellServlet() {
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
		String userInput="";
		String jsoninput ="";
		DecimalFormat fmt=new DecimalFormat("#.##");
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
		String sharePriceStr=(String) json.get("sharePrice");
		String company=(String) json.get("company");
		String qtyStr=(String) json.get("qty");
		buyAndSellService service=new buyAndSellService();
		JSONObject obj=service.alreadyThisStock(company);
		System.out.println((int)obj.get("oldQty"));
		
	 if((int)obj.get("oldQty")!=0 &&(int)obj.get("oldQty")>0 && ((int)obj.get("oldQty")>Integer.parseInt(qtyStr)) ) {
			if ((int)obj.get("oldQty")==Integer.parseInt(qtyStr)) {
				service.holdDelete(company);
				service.updateAmount(Integer.parseInt(qtyStr),Double.parseDouble(sharePriceStr));
			}
			else {
				service.holdSellUpdate(company, Double.parseDouble(sharePriceStr), Integer.parseInt(qtyStr));
				service.updateAmount(Integer.parseInt(qtyStr),Double.parseDouble(sharePriceStr));
				
			}
	
			response.getWriter().append("order success");
		}
		else {
			response.getWriter().append("order failure");
		}
	}

}
