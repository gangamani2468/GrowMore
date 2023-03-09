package LogicMangement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Connection.database;
import Model.Holdings;

public class priceData {
	public static JSONObject outputArr=new JSONObject();
	DecimalFormat fmt=new DecimalFormat("#.##");
	public static JSONObject output;
	public JSONObject livePrice() {
		output=new priceData().stocksLivePrice();
		
		
		return output;
	}
	public synchronized JSONObject stocksLivePrice() {
		String adaniPower =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/ADANIPOWER.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		System.out.println(adaniPower);
		JSONObject adaniPowerObj=priceData(adaniPower);
		String adaniGreen =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/ADANIGREEN.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject adaniGreenObj=priceData(adaniGreen);
		String itc =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/ITC.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
	
		JSONObject itcObj=priceData(itc);
		String kalyan =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/KALYANKJIL.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject kalyanObj=priceData(kalyan);
		String manappuram =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/MANAPPURAM.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject manappuramObj=priceData(manappuram);
		String natco =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/NATCOPHARM.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject natcoObj= priceData(natco);
		String tataPower =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/TATAPOWER.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject tataPowerObj= priceData(tataPower);
		String tataMotor =new urlClass().url("https://query1.finance.yahoo.com/v8/finance/chart/TATAMOTORS.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance");
		
		JSONObject tataMotorObj= priceData(tataMotor);
		
		outputArr.put("adaniPower",adaniPowerObj);
		outputArr.put("adaniGreen",adaniGreenObj);
		outputArr.put("itc",itcObj);
		outputArr.put("kalyan",kalyanObj);
		outputArr.put("manappuram",manappuramObj);
		outputArr.put("natco",natcoObj);
		outputArr.put("tataPower",tataPowerObj);
		outputArr.put("tataMotors",tataMotorObj);
		
		dbLiveUpdate();
		
		return outputArr;
	}
	
	public void dbLiveUpdate() {
		List<Holdings> holdings=mainClass.user.getHoldlist();
		for (Holdings hold:holdings) {
			JSONObject obj=(JSONObject) outputArr.get(hold.getShareName());
			if(obj!=null) {
				Double stockPrice=(Double) obj.get("ltp");
				Double currentAmount=Double.parseDouble(fmt.format(hold.getQuantity()*stockPrice));
				Double investedAmount=hold.getInvestedAmount();
				Double profitAndLoss=Double.parseDouble(fmt.format(currentAmount-investedAmount));
				PreparedStatement psmt;
				try {
					psmt=database.dbConnection.prepareStatement("update Holdings set ltp = ?, profitAndLoss = ? , currentAmount = ? where ShareName = ? ");
					psmt.setString(1, ""+stockPrice);
					psmt.setString(2, ""+profitAndLoss);
					psmt.setString(3, ""+currentAmount);
					psmt.setString(4, hold.getShareName());
					psmt.execute();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	public JSONObject priceData(String output) {
		JSONObject chart=(JSONObject) JSONValue.parse(output);
		DecimalFormat fmt=new DecimalFormat("#.##");
		System.out.println(chart);
		JSONObject data=(JSONObject) chart.get("chart");
	 
		JSONArray result= (JSONArray) data.get("result");
	 
		JSONObject result1=(JSONObject) result.get(0);
		JSONObject meta=(JSONObject) result1.get("meta");
		String stock=(String) meta.get("symbol");
		Double yesterDayPrice=Double.parseDouble(""+meta.get("previousClose"));
		Double regularMarketPrice=Double.parseDouble(""+meta.get("regularMarketPrice"));
		Double changePercent=Double.parseDouble(fmt.format(-1*(100.00-(regularMarketPrice/(yesterDayPrice/100)))));
		Double changeAmount=Double.parseDouble(fmt.format(regularMarketPrice-yesterDayPrice));
		String color="red";
		String arrow="<i class=\"fa-solid fa-angle-down\"></i>";
		if(changeAmount>0.00) {
			color="green";
			arrow="<i class=\"fa-sharp fa-solid fa-angle-up\"></i>";
		}
		JSONObject responseObject=new JSONObject();
		responseObject.put("changePercent", changePercent);
		responseObject.put("changeAmount", changeAmount);
		responseObject.put("ltp", regularMarketPrice);
		responseObject.put("bullOrBear", color);
		responseObject.put("arrow", arrow);
		responseObject.put("stockName",stock);
		
		return responseObject;
	}
}
