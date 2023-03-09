package LogicMangement;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.Cookie;

public class OtpGenerateJava {
	private static String generateOTP() {
	      String numbers = "1234567890";
	      Random random = new Random();
	      String otp="";

	      for(int i = 0; i< 6 ; i++) {
	         otp += numbers.charAt(random.nextInt(numbers.length()));
	      }
	      return otp;
	   }
	public String triggerOtp(String mail) {
		String otpCode="";
		try {
	          URL url = new URL("https://api.sendinblue.com/v3/smtp/email");
	          HttpURLConnection con = (HttpURLConnection) url.openConnection();
	          con.setRequestMethod("POST");
	          con.setRequestProperty("Content-Type", "application/json");
	          con.setRequestProperty("api-key", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	          con.setDoOutput(true);
	          otpCode=generateOTP();
	          
	          
	          String payload = "{\"sender\":{\"email\":\"growmore01992@gmail.com\"},\"to\":[{\"email\":\""+mail+"\"}],\"subject\":\"GrowMore OTP Verifiction\",\"textContent\":\""+"Your GrowMore DematAccount Activation Code "+otpCode+"\"}";
	          OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
	          writer.write(payload);
	          writer.flush();
	          writer.close();

	          int responseCode = con.getResponseCode();
	          System.out.println("Response code: " + responseCode);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		return otpCode;
	}
}
