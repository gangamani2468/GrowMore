package LogicMangement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.json.simple.JSONObject;

import Connection.database;
import Model.BankDetails;
import Model.DematAccount;
import Model.Holdings;

public class buyAndSellService {
	DecimalFormat fmt=new DecimalFormat("#.##");
	public boolean enoughMoney(Integer qty,Double price) {
		return ((qty*price)<mainClass.user.getDemat().getGrow_More_Balance());
	}
	public JSONObject alreadyThisStock(String share)  {
		JSONObject out=new JSONObject();
		List<Holdings> holdList1=mainClass.user.getHoldlist();
		JSONObject holdStock=null;
		for (Holdings holds:holdList1) {
			
			if(holds.getShareName().equals(share)) {
				holdStock=new JSONObject();
				
				holdStock.put("shareAvg", holds.getShareAvgPrice());
				holdStock.put("quantity", holds.getQuantity());
				holdStock.put("profitAndLoss",holds.getProfitAndLoss());
				holdStock.put("InvestedAmount", holds.getInvestedAmount());
				
				break;
			} 
			
		}//
		if(holdStock==null) {
			out.put("oldQty", 0);
			out.put("invest",00.00);
			out.put("do","insert");
			return out;
		}
		out.put("oldQty", holdStock.get("quantity"));
		System.out.println("buy...");
		out.put("invest", holdStock.get("InvestedAmount"));
		out.put("do", "update");
		return out;
	}
	public boolean holdUpdate(String shareName,Double sharePrice,int qty,Double invested) {
		PreparedStatement psmt;
	
		
		try {
		
			psmt=database.dbConnection.prepareStatement("update Holdings set ShareAvgPrice=? ,ShareQuantity=? ,InvestedAmount=? where DematAccountNumber=? and ShareName=?");
			psmt.setString(1, ""+sharePrice);
			psmt.setString(2, ""+qty);
			psmt.setString(3, ""+invested);
			psmt.setString(4, ""+mainClass.user.getDemat().getDematAccountNumber());
			psmt.setString(5,shareName );
			return psmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
		}
	public boolean holdInsert(String shareName,Double sharePrice,int qty,Double invested) {
		PreparedStatement psmt;
		try {
			psmt=database.dbConnection.prepareStatement("insert into Holdings values(?, ?, ?, ?, ?, ?, ?,?)");
			psmt.setString(1, ""+mainClass.user.getDemat().getDematAccountNumber());
			psmt.setString(2, shareName);
			psmt.setString(3, ""+sharePrice);
			psmt.setString(4, ""+qty);
			psmt.setString(5, ""+0.00);
			psmt.setString(6, ""+invested);
			psmt.setString(7, ""+0.00);
			psmt.setString(8, ""+0.00);
			return psmt.execute();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
		
	}
	public boolean dematAmountUpdate(int qty,Double sharePrice) {
		PreparedStatement psmt;
		try {
			Double amount=Double.parseDouble(fmt.format(qty*sharePrice));
			System.out.println(mainClass.user.getDemat().getGrow_More_Balance()-amount);
			mainClass.user.getDemat().setGrow_More_Balance(mainClass.user.getDemat().getGrow_More_Balance()-amount);
			System.out.println(mainClass.user.getDemat().getGrow_More_Balance());
			psmt=database.dbConnection.prepareStatement("update DematAccount set grow_more_balance = ? where DematAccountNumber = ?");
			psmt.setString(1, ""+mainClass.user.getDemat().getGrow_More_Balance());
			psmt.setString(2, ""+mainClass.user.getDemat().getDematAccountNumber());
			
			System.out.println(psmt.executeUpdate());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("money update.....");
		return false;
		
	}
	public boolean holdSellUpdate(String shareName,Double sharePrice,int qty) {
		
		List<Holdings> holdList1=mainClass.user.getHoldlist();
		JSONObject holdStock=null;
		for (Holdings holds:holdList1) {
			
			if(holds.getShareName().equals(shareName)) {
				holdStock=new JSONObject();
				
				holdStock.put("shareAvg", holds.getShareAvgPrice());
				holdStock.put("quantity", holds.getQuantity());
				holdStock.put("profitAndLoss",holds.getProfitAndLoss());
				holdStock.put("InvestedAmount", holds.getInvestedAmount());
				holdStock.put("ltp",holds.getLtp());
				break;
			} 
			
		}
		Double amount=Double.parseDouble(fmt.format(qty*(Double)holdStock.get("shareAvg")));
		PreparedStatement stmt;
		try{
			stmt=database.dbConnection.prepareStatement("update Holdings set ShareQuantity = ?,InvestedAmount = ? where shareName = ? and DematAccountNumber = ?");
			stmt.setString(1, ""+(((int)holdStock.get("quantity"))-qty));
			stmt.setString(2, ""+fmt.format((Double)holdStock.get("InvestedAmount")-amount));
			stmt.setString(3, shareName);
			stmt.setString(4, ""+mainClass.user.getDemat().getDematAccountNumber());
			return stmt.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	public boolean holdDelete(String shareName) {
		PreparedStatement psmt;
		try {
			psmt=database.dbConnection.prepareStatement("delete from Holdings where DematAccountNumber=? and ShareName=?");
			psmt.setString(1, ""+mainClass.user.getDemat().getDematAccountNumber());
			psmt.setString(2, shareName);
			return psmt.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean updateAmount(int qty,Double sharePrice) {
		PreparedStatement psmt;
		try {
			Double amount=Double.parseDouble(fmt.format(qty*sharePrice));
			mainClass.user.getDemat().setGrow_More_Balance(mainClass.user.getDemat().getGrow_More_Balance()+amount);
			psmt=database.dbConnection.prepareStatement("update DematAccount set grow_more_balance = ? where DematAccountNumber = ?");
			psmt.setString(1, ""+mainClass.user.getDemat().getGrow_More_Balance());
			psmt.setString(2, ""+mainClass.user.getDemat().getDematAccountNumber());
			return psmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	public boolean addMoney(Double amount) {
		BankDetails bank= mainClass.user.getBankList().get(0);
		if(bank.getBalance()>100.00) {
			PreparedStatement psmt,psmt1;
			try {
				psmt=database.dbConnection.prepareStatement("update DematAccount set grow_more_balance = ? where DematAccountNumber = ?");
				psmt.setString(1,""+(mainClass.user.getDemat().getGrow_More_Balance()+amount));
				psmt.setString(2,""+mainClass.user.getDemat().getDematAccountNumber());
				psmt.execute();
				psmt1=database.dbConnection.prepareStatement("update BankDetails set balance = ? where BankAccountNumber = ? ");
				psmt1.setString(1, ""+(bank.getBalance()-amount));
				psmt1.setString(1, ""+bank.getBankAccountNumber());
				psmt1.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		else {
			return false;
		}
	}
	public boolean withdrawMoney(Double amount) {
		DematAccount demat= mainClass.user.getDemat();
		if(demat.getGrow_More_Balance()>0.00) {
			PreparedStatement psmt,psmt1;
			try {
				psmt=database.dbConnection.prepareStatement("update DematAccount set grow_more_balance = ? where DematAccountNumber = ?");
				psmt.setString(1,""+(demat.getGrow_More_Balance()-amount));
				psmt.setString(2,""+demat.getDematAccountNumber());
				psmt.execute();
				psmt1=database.dbConnection.prepareStatement("update BankDetails set balance = ? where BankAccountNumber = ? ");
				psmt1.setString(1, ""+(mainClass.user.getBankList().get(0).getBalance()+amount));
				psmt1.setString(2, ""+mainClass.user.getBankList().get(0).getBankAccountNumber());
				psmt1.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		else {
			return false;
		}
		
	}
}
