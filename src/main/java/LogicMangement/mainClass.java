package LogicMangement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import Connection.database;
import Model.BankDetails;
import Model.CurrentUser;
import Model.DematAccount;
import Model.Holdings;

public class mainClass {

	public static CurrentUser user;
	private BankDetails bank;
	private static DematAccount account;
	
	private static List<Holdings> holdList;
	private static List<BankDetails> bankList;
	
	public synchronized static CurrentUser userDetails(String sessionId) {
		
		PreparedStatement sessions;
		PreparedStatement dematInfo;
		PreparedStatement holdInfo;
		PreparedStatement bankInfo;
		try {
			 bankList=new ArrayList<BankDetails>();
			 holdList=new ArrayList<Holdings>();
			 sessions=database.dbConnection.prepareStatement("select * from sessions where sessionid = ? ");
			 sessions.setString(1, sessionId);
			 ResultSet rs=sessions.executeQuery();
			 if(rs.next()) {
				 String phone=rs.getString(2);
				 dematInfo=database.dbConnection.prepareStatement("select * from DematAccount where phoneNo = ?");
				 dematInfo.setString(1, phone);
				 ResultSet dematData=dematInfo.executeQuery();
				 if(dematData.next()) {
					 Long bankAccount=Long.parseLong(dematData.getString(10));
					 Long accountNumber=Long.parseLong(dematData.getString(8));
					 account=new DematAccount(dematData.getString(1),dematData.getString(2),Long.parseLong(dematData.getString(3)),dematData.getString(4),dematData.getString(5),dematData.getString(6),dematData.getString(7),accountNumber,dematData.getString(9),bankAccount,Double.parseDouble(dematData.getString(11)));
					 holdInfo=database.dbConnection.prepareStatement("select * from Holdings where DematAccountNumber = ?");
					 holdInfo.setString(1, ""+accountNumber);
					 ResultSet holdData=holdInfo.executeQuery();
					 System.out.println(bankAccount);
					 System.out.println(accountNumber);
					
					
					 while(holdData.next()) {
						
						 Holdings hold=new Holdings(Long.parseLong(holdData.getString(1)),holdData.getString(2),Double.parseDouble(holdData.getString(3)),Integer.parseInt(holdData.getString(4)),Double.parseDouble(holdData.getString(5)),Double.parseDouble(holdData.getString(6)),Double.parseDouble(holdData.getString(7)),Double.parseDouble(holdData.getString(8)));
						 holdList.add(hold);
					 }
					 bankInfo=database.dbConnection.prepareStatement("select * from BankDetails where BankAccountNumber = ?");
					 bankInfo.setString(1, ""+bankAccount);
					 ResultSet bankData=bankInfo.executeQuery();
					
					 while(bankData.next()) {
						 BankDetails bank=new BankDetails(bankData.getString(1),bankData.getString(2),bankData.getString(3),Long.parseLong(bankData.getString(4)),Double.parseDouble(bankData.getString(5)),Long.parseLong(bankData.getString(6)));
						 bankList.add(bank);
					 }
//					 holdList.sort();
					 Collections.sort(holdList,new amountBasedCompare());
					 
					 System.out.println(holdList.size());
					 System.out.println(bankList.size());
					 user=new CurrentUser(account,holdList,bankList);
				 }
				 else {
					 user=null;
				 }
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return user;
		
		
	}
	
	
}


