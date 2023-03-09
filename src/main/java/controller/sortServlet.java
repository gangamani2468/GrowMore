package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LogicMangement.mainClass;
import LogicMangement.sortAction;
import Model.Holdings;

/**
 * Servlet implementation class sortServlet
 */
@WebServlet("/sortServlet")
public class sortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public sortServlet() {
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
		String action =request.getParameter("action");
		sortAction sort=new sortAction();
		List<Holdings> holdList=mainClass.user.getHoldlist();
		List<Holdings> finalList=null;
		switch(action) {
			case "sortNameDesc":
				finalList=sort.nameBaseSortDesc(holdList);
				break;
			case "sortQtyDesc":
				finalList=sort.amountBaseSortDesc(holdList);
				break;
			case "sortAvgDesc":
				finalList=sort.quantityBaseSortDesc(holdList);
				break;
			case "sortLtpDesc":
				finalList=sort.costBaseSortDesc(holdList);
				break;
			case "sortCurrentDesc":
				finalList=sort.ltpBaseSortDesc(holdList);
				break;
			case "sortPAndLDesc":
				finalList=sort.pAndLBaseSortDesc(holdList);
				break;
			case "sortName":
				finalList=sort.nameBaseSort(holdList);
				break;
			case "sortQty":
				finalList=sort.amountBaseSort(holdList);
				break;
			case "sortAvg":
				finalList=sort.quantityBaseSort(holdList);
				break;
			case "sortLtp":
				finalList=sort.costBaseSort(holdList);
				break;
			case "sortCurrent":
				finalList=sort.ltpBaseSort(holdList);
				break;
			case "sortPAndL":
				finalList=sort.pAndLBaseSort(holdList);
				break;
			
		}
		response.getWriter().append(sort.holdListUpdate(finalList).toString());
	}

}
