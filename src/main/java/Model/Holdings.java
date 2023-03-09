package Model;

public class Holdings {
	Long dematAccountNumber;
	String shareName;
	Double ltp;
	Double shareAvgPrice;
	Integer quantity;
	Double profitAndLoss;
	Double investedAmount;
	Double currentAmount;
	
	
	
	 
	public Holdings(Long dematAccountNumber, String shareName, Double shareAvgPrice, Integer quantity,
			Double profitAndLoss, Double investedAmount, Double currentAmount,Double Ltp) {
		super();
		this.dematAccountNumber = dematAccountNumber;
		this.shareName = shareName;
		this.shareAvgPrice = shareAvgPrice;
		this.quantity = quantity;
		this.profitAndLoss = profitAndLoss;
		this.investedAmount = investedAmount;
		this.currentAmount = currentAmount;
		this.ltp=Ltp;
	}
	
	public Double getLtp() {
		return ltp;
	}

	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}

	public Long getDematAccountNumber() {
		return dematAccountNumber;
	}
	public void setDematAccountNumber(Long dematAccountNumber) {
		this.dematAccountNumber = dematAccountNumber;
	}
	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
 
	public Double getShareAvgPrice() {
		return shareAvgPrice;
	}
	public void setShareAvgPrice(Double shareAvgPrice) {
		this.shareAvgPrice = shareAvgPrice;
	}
	public Double getInvestedAmount() {
		return investedAmount;
	}
	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}
	public Double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getProfitAndLoss() {
		return profitAndLoss;
	}
	public void setProfitAndLoss(Double profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	
}
