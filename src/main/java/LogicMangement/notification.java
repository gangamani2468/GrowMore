package LogicMangement;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class notification {
	public String notifyCheck() {
		String output="";
		JSONObject obj=priceData.outputArr;
		List<JSONObject> priceData=new ArrayList<JSONObject>();
		JSONObject adaniPower=(JSONObject) obj.get("adaniPower");
		JSONObject adaniGreen=(JSONObject) obj.get("adaniGreen");
		JSONObject itc=(JSONObject) obj.get("itc");
		JSONObject kalyan=(JSONObject) obj.get("kalyan");
		JSONObject manappuram=(JSONObject) obj.get("manappuram");
		JSONObject natco=(JSONObject) obj.get("natco");
		JSONObject tataPower=(JSONObject) obj.get("tataPower");
		JSONObject tataMotors=(JSONObject) obj.get("tataMotors");
		priceData.add(adaniPower);
		priceData.add(adaniGreen);
		priceData.add(itc);
		priceData.add(kalyan);
		priceData.add(manappuram);
		priceData.add(natco);
		priceData.add(tataPower);
		priceData.add(tataMotors);
		for(JSONObject stocks:priceData) {
			
			if(((Double)stocks.get("changePercent"))>3.00) {
					output+="<div class=\"stockUpdate\" style=\"font-size:25px;color:green;\">"+stocks.get("stockName")+" up by 3%</div>";
			}
			else if(((Double)stocks.get("changePercent"))<(-3.00)) {
					output+="<div class=\"stockUpdate\" style=\"font-size:25px;color:red;\">"+stocks.get("stockName")+" down by 3%</div>";
			}
			else {
				
			}
		}
		return output;
	}

}
