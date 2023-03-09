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
	          con.setRequestProperty("api-key", "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");
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
