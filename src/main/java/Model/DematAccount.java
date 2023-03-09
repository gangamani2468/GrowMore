package Model;

import java.util.Date;

public class DematAccount {
	
	private String name;
	private String date_Of_Birth;
	private Long phone_Number;
	private String email;
	private String pAN_Number;
	private String marital_Status;
	private String gender; 
	private Long dematAccountNumber;
	private String password;
	private Long bankAccountNumber;
	private Double grow_More_Balance;

 
	 
	public DematAccount(String name, String date_Of_Birth, Long phone_Number, String email, String pAN_Number,
			String marital_Status, String gender, Long dematAccountNumber, String password, Long bankAccountNumber,
			Double grow_More_Balance) {
//		super();
		this.name = name;
		this.date_Of_Birth = date_Of_Birth;
		this.phone_Number = phone_Number;
		this.email = email;
		this.pAN_Number = pAN_Number;
		this.marital_Status = marital_Status;
		this.gender = gender;
		this.dematAccountNumber = dematAccountNumber;
		this.password = password;
		this.bankAccountNumber = bankAccountNumber;
		this.grow_More_Balance = grow_More_Balance;
	}
	public Long getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(Long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getDate_Of_Birth() {
		return date_Of_Birth;
	}
	public void setDate_Of_Birth(String date_Of_Birth) {
		this.date_Of_Birth = date_Of_Birth;
	}
	public Long getPhone_Number() {
		return phone_Number;
	}
	public void setPhone_Number(Long phone_Number) {
		this.phone_Number = phone_Number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getpAN_Number() {
		return pAN_Number;
	}
	public void setpAN_Number(String pAN_Number) {
		this.pAN_Number = pAN_Number;
	}
	public String getMarital_Status() {
		return marital_Status;
	}
	public void setMarital_Status(String marital_Status) {
		this.marital_Status = marital_Status;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getDematAccountNumber() {
		return dematAccountNumber;
	}
	public void setDematAccountNumber(Long dematAccountNumber) {
		this.dematAccountNumber = dematAccountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getGrow_More_Balance() {
		return grow_More_Balance;
	}
	public void setGrow_More_Balance(Double grow_More_Balance) {
		this.grow_More_Balance = grow_More_Balance;
	}
	 

	
}
