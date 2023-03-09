package LogicMangement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class urlClass {
	public static void main(String[] args) {
		System.out.println(new urlClass().url("https://query1.financek.yahoo.com/v8/finance/chart/ITC.NS?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance"));
	}
	public String url(String url1) {
		String result="";
		try {
		
		URL url = new URL(url1);  // put a URL u need

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        reader.close();
        

		result = sb.toString();
		//System.out.println(result+"this");
		}
		catch(Exception e) {
			System.out.println(e.getMessage()+33);
			result="unreachable"+"kii"
;		}
		return result;
	}


}
