package controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import LogicMangement.mainClass;
import Model.BankDetails;
import Model.CurrentUser;
import Model.Holdings;

import java.util.*;

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/userDataServlet")
public class userDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public dashboardServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

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
		HttpSession session =request.getSession();
		JSONObject output=new JSONObject();
		String sessionId=(String) session.getAttribute("SESSION-ID");
		System.out.println(sessionId);
		
		CurrentUser user=new mainClass().userDetails(sessionId);
		if(user!=null) {
			Double currentAmount=0.00;
			Double invested=0.00;
			DecimalFormat fmt=new DecimalFormat("#.##");
			
			String color="red";
			JSONObject demat=new JSONObject();
			demat.put("name", user.getDemat().getName());
			demat.put("dob", user.getDemat().getDate_Of_Birth());
			demat.put("phone", user.getDemat().getPhone_Number());
			demat.put("mail", user.getDemat().getEmail());
			demat.put("pan", user.getDemat().getpAN_Number());
			demat.put("marital", user.getDemat().getMarital_Status());
			demat.put("gender", user.getDemat().getGender());
			demat.put("dematNumber", user.getDemat().getDematAccountNumber());
			demat.put("bankNumber", user.getDemat().getBankAccountNumber());
			demat.put("balance", user.getDemat().getGrow_More_Balance());
			 
			JSONObject hold=new JSONObject();
			JSONObject bank=new JSONObject();
			
			JSONArray holdArr=new JSONArray();
			JSONArray bankArr=new JSONArray();
		 
			for (Holdings holds:user.getHoldlist()) {
				
				JSONObject holdList=new JSONObject();
				holdList.put("shareName",holds.getShareName());
				holdList.put("shareAvg", holds.getShareAvgPrice());
				holdList.put("quantity", holds.getQuantity());
				holdList.put("profitAndLoss",holds.getProfitAndLoss());
				holdList.put("InvestedAmount", holds.getInvestedAmount());
				holdList.put("currentAmount", holds.getCurrentAmount());
				holdList.put("ltp",holds.getLtp());
				String stockSentiment="red";
				if(holds.getProfitAndLoss()>0.00) {
					stockSentiment="green";
				}
				
				holdList.put("color", stockSentiment);
				holdArr.add(holdList);
				currentAmount+=(Double)holds.getCurrentAmount();
				invested+=(Double) holds.getInvestedAmount();
				
						
			}
		 
			for(BankDetails banks:user.getBankList()) {
				System.out.println("bank.....");
				 
				JSONObject bankList=new JSONObject();
				bankList.put("bankName",banks.getBankName());
				bankList.put("bankBranch",banks.getBankBranch());
				bankList.put("ifscCode",banks.getIFSCCode());
				bankList.put("balance",banks.getBalance());
				bankList.put("bankAccount",banks.getBankAccountNumber());
				bankArr.add(bankList);
				 	
			}
			 
			if(currentAmount-invested>0.00) {
				color="green";
			}
			output.put("StatusCode",200);
			output.put("demat",demat);
			output.put("holdArr", holdArr);
			output.put("bankArr", bankArr);
			output.put("currentAmount", fmt.format(currentAmount));
			output.put("invested", fmt.format(invested));
			output.put("profitAndLoss", fmt.format(currentAmount-invested));
			output.put("profitPercent", fmt.format((currentAmount-invested)/(invested/100)));
			output.put("color",color);
			
		
		}
		else {
			output.put("StatusCode",500);
			output.put("message","not logged in");
		}
		
		System.out.println(output.toString());
		response.getWriter().append(output.toString());
	
	}

}
