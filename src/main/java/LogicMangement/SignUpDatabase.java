package LogicMangement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.database;

public class SignUpDatabase {
	
	public boolean exportSignUp(String name,String dob,String phone,String mail,String pan,String marital,String gender,String password,String ifsc,String bankAccountNumber) throws SQLException {
			if(isIfscCode(ifsc,bankAccountNumber)) {
				
			}
			else {
				return false;
				
			}
		try {
			String dematAccountNumber=dematAccountGenerator();
			PreparedStatement stmt=database.dbConnection.prepareStatement("insert into DematAccount values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2,dob);
			stmt.setString(3, phone);
			stmt.setString(4, mail);
			stmt.setString(5, pan);
			stmt.setString(6, marital);
			stmt.setString(7, gender);
			stmt.setString(8, dematAccountNumber);
			stmt.setString(9, password);
			stmt.setString(10,bankAccountNumber);
			stmt.setString(11, ""+0.00);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	private String dematAccountGenerator() throws SQLException {
		Long dematAccountNumber;
		while(true) {
			dematAccountNumber=(long)(Math.random()*1000000000000000l)+1600000000000000l; 
			PreparedStatement stmt=database.dbConnection.prepareStatement("select * from DematAccount where DematAccountNumber=?");
			stmt.setString(1,""+dematAccountNumber);
			ResultSet rs=stmt.executeQuery();
			if(!rs.next()) {
				break;
			}
		}
		return ""+dematAccountNumber;
		
	}
	private boolean isIfscCode(String ifsc,String bankAccountNumber) throws SQLException {
		PreparedStatement stmt=database.dbConnection.prepareStatement("select * from BankDetails where IFSC_Code=? and BankAccountNumber=? limit 1");
		stmt.setString(1,""+ifsc);
		stmt.setString(2, bankAccountNumber);
		ResultSet rs=stmt.executeQuery();
		System.out.println(ifsc);
		System.out.println(bankAccountNumber);
		if(rs.next()) {
			return true;
		}
		else {
			
			return false;
		}
	}
}
