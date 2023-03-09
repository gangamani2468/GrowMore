package Model;

public class BankDetails {
	String bankName;
	String bankBranch;
	String IFSCCode;
	Long BankAccountNumber;
	Double Balance;
	Long phone;
	
	public BankDetails(String bankName, String bankBranch, String iFSCCode, Long bankAccountNumber, Double balance,
			Long phone) {
		super();
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		IFSCCode = iFSCCode;
		BankAccountNumber = bankAccountNumber;
		Balance = balance;
		this.phone = phone;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getIFSCCode() {
		return IFSCCode;
	}
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}
	public Long getBankAccountNumber() {
		return BankAccountNumber;
	}
	public void setBankAccountNumber(Long bankAccountNumber) {
		BankAccountNumber = bankAccountNumber;
	}
	public Double getBalance() {
		return Balance;
	}
	public void setBalance(Double balance) {
		Balance = balance;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
}
