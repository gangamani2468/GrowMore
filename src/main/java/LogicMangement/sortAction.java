package LogicMangement;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.CurrentUser;
import Model.Holdings;

public class sortAction {
	
	public JSONObject holdListUpdate(List<Holdings> holdArray) {
		JSONObject hold=new JSONObject();
		JSONObject output=new JSONObject();
		JSONArray holdArr=new JSONArray();	 
		for (Holdings holds:holdArray) {
			
			JSONObject holdList=new JSONObject();
			holdList.put("shareName",holds.getShareName());
			holdList.put("shareAvg", holds.getShareAvgPrice());
			holdList.put("quantity", holds.getQuantity());
			holdList.put("profitAndLoss",holds.getProfitAndLoss());
			holdList.put("InvestedAmount", holds.getInvestedAmount());
			holdList.put("currentAmount", holds.getCurrentAmount());
			holdList.put("ltp",holds.getLtp());
			String stockSentiment="red";
			if(holds.getProfitAndLoss()>0.00) {
				stockSentiment="green";
			}
			
			holdList.put("color", stockSentiment);
			holdArr.add(holdList);		
		}
		output.put("holdArr", holdArr);
		return output;
	 
	}
	public List<Holdings> nameBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new nameBasedCompare());
		return  output;
	}
	public List<Holdings> amountBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new amountBasedCompare());
		return  output;
	}
	
	public List<Holdings> quantityBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new quantityBasedCompare());
		return  output;
	}
	
	public List<Holdings> costBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new costBasedCompare());
		return  output;
	}
	public List<Holdings> ltpBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new ltpBasedCompare());
		return  output;
	}
	public List<Holdings> pAndLBaseSort(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new pAndLBasedCompare());
		return  output;
	}
	
	public List<Holdings> nameBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new nameBasedCompareDesc());
		return  output;
	}
	public List<Holdings> amountBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new amountBasedCompareDesc());
		return  output;
	}
	
	public List<Holdings> quantityBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new quantityBasedCompareDesc());
		return  output;
	}
	
	public List<Holdings> costBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new costBasedCompareDesc());
		return  output;
	}
	public List<Holdings> ltpBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new ltpBasedCompareDesc());
		return  output;
	}
	public List<Holdings> pAndLBaseSortDesc(List<Holdings> holdList) {
		List<Holdings> output=holdList;
		Collections.sort(output,new pAndLBasedCompareDesc());
		return  output;
	}
	

}
class nameBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getShareName().compareTo(o2.getShareName()));
	}
	
}
class amountBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getCurrentAmount().compareTo(o2.getCurrentAmount()));
	}
	
}
class quantityBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getQuantity().compareTo(o2.getQuantity()));
	}
	
}
class costBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getShareAvgPrice().compareTo(o2.getShareAvgPrice()));
	}
	
}
class ltpBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getLtp().compareTo(o2.getLtp()));
	}
}
class pAndLBasedCompare implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return -(o1.getProfitAndLoss().compareTo(o2.getProfitAndLoss()));
	}
	
}

class nameBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getShareName().compareTo(o2.getShareName());
	}
	
}
class amountBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getCurrentAmount().compareTo(o2.getCurrentAmount());
	}
	
}
class quantityBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getQuantity().compareTo(o2.getQuantity());
	}
	
}
class costBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getShareAvgPrice().compareTo(o2.getShareAvgPrice());
	}
	
}
class ltpBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getLtp().compareTo(o2.getLtp());
	}
}
class pAndLBasedCompareDesc implements Comparator<Holdings>{

	@Override
	public int compare(Holdings o1, Holdings o2) {
		// TODO Auto-generated method stub
		return o1.getProfitAndLoss().compareTo(o2.getProfitAndLoss());
	}
	
}


