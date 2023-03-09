package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.database;
import LogicMangement.urlClass;

/**
 * Servlet implementation class stockCandle
 */
@WebServlet("/stockCandle")
public class stockCandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public stockCandle() {
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
		//System.out.println("in");
		String company=request.getParameter("name");
		
		System.out.println(company);
		String companyName=company.equals("adaniPower")?"ADANIPOWER":company.equals("adaniGreen")?"ADANIGREEN":company.equals("itc")?"ITC":company.equals("kalyan")?"KALYANKJIL":company.equals("manappuram")?"MANAPPURAM":company.equals("natco")?"NATCOPHARM":company.equals("tataPower")?"TATAPOWER":company.equals("tataMotors")?"TATAMOTORS":"";
		System.out.println(companyName);
		String output=new urlClass().url("https://groww.in/v1/api/charting_service/v2/chart/delayed/exchange/NSE/segment/CASH/"+companyName+"/5y?intervalInDays=25");
//		String url2=urlClass.url("https://groww.in/v1/api/stocks_data/v1/company/search_id/itc-ltd?page=0&size=10");
		if(output.contains("candles")) {
			
			database.data.put(companyName, output);
		}else {
			System.out.println("yeah");
			output=database.data.getIfPresent(companyName);
		
		}
		
		response.getWriter().append(output);
	}

}
