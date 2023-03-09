package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import Connection.database;
import LogicMangement.urlClass;

/**
 * Servlet implementation class companyAbout
 */
@WebServlet("/companyAbout")
public class companyAbout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String company=request.getParameter("path");
		
		//System.out.println(company);
		String companyName=company.equals("adaniPower")?"adani-power-ltd":company.equals("adaniGreen")?"adani-green-energy-ltd":company.equals("itc")?"itc-ltd":company.equals("kalyan")?"kalyan-jewellers-india-ltd":company.equals("manappuram")?"manappuram-finance-ltd":company.equals("natco")?"natco-pharma-ltd":company.equals("tataPower")?"tata-power-company-ltd":company.equals("tataMotors")?"tata-motors-ltd":"";
		
		String output=new urlClass().url("https://groww.in/v1/api/stocks_data/v1/company/search_id/"+companyName+"?page=0&size=10");
		  // put a URL u need
	if(output.contains("header")) {
		
	database.data.put(companyName, output);
		
	}else {
		System.out.println("yeah");
		output=database.data.getIfPresent(companyName);
	}
//	
	//System.out.println(output);
	response.getWriter().append(output);
	}

}
