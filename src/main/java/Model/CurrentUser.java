package Model;

import java.util.List;

public class CurrentUser {
	DematAccount demat;
	List<Holdings> holdlist;
	List<BankDetails> bankList;
	public CurrentUser(DematAccount demat, List<Holdings> holdlist, List<BankDetails> bankList) {
		super();
		this.demat = demat;
		this.holdlist = holdlist;
		this.bankList = bankList;
	}
	public DematAccount getDemat() {
		return demat;
	}
	public void setDemat(DematAccount demat) {
		this.demat = demat;
	}
	public List<Holdings> getHoldlist() {
		return holdlist;
	}
	public void setHoldlist(List<Holdings> holdlist) {
		this.holdlist = holdlist;
	}
	public List<BankDetails> getBankList() {
		return bankList;
	}
	public void setBankList(List<BankDetails> bankList) {
		this.bankList = bankList;
	}
	
	 
}
