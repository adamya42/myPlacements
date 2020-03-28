package com.example.demo.services;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserOTP;
import com.example.demo.repository.UserOTPRepository;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@Service
public class OTPServices {
	
	@Autowired
	private UserOTPRepository userOTPRepository;
	@Autowired
    private JavaMailSender javaMailSender;
	

	//################  OTP Generation ###############################
	
	public int generatingOTP() {
		Random rand = new Random();
		int GeneratedOTP;
		int length;
		do {
		GeneratedOTP = rand.nextInt(999999);
		length = (int) Math.log10(GeneratedOTP) + 1;
		}while(length!=6);
		return GeneratedOTP;

	}

	//################# Adding OTP to DB 	######################################

	public void addOTP(int otp, int id) {
		UserOTP userOTP = new UserOTP();
		userOTP.setUserId(id);
		
		
		LocalDateTime validTill = LocalDateTime.now().plus(Duration.of(30, ChronoUnit.MINUTES));
		userOTP.setValidTill(validTill);
		userOTP.setOtp(otp);

		
		userOTPRepository.save(userOTP);

	}

	

	//###################### OTP Verification 	############################
	
	public UserOTP verifyOTP(List<UserOTP> optUser, int enteredOTP) {
		UserOTP validateOTP = new UserOTP();
		int flag=0;
		for(int i=0;i<optUser.size();i++)
		{
			 validateOTP=optUser.get(i);
			if(enteredOTP==validateOTP.getOtp())
			{
				flag=1;
				break;
			}
			else 
			{
				flag=0;
			}
		}
		if(flag==1)
		{

			validateOTP.setIsOTPVerified(1);
			userOTPRepository.save(validateOTP);
			return null;
		}
		else
		{
			return validateOTP; 
		}

	}
		
	
	//################# fetching user by OTP 	#############################
 
	public List<UserOTP> getUserByUserId(int userId) {
	 List<UserOTP> optUserByUserId = userOTPRepository.findByUserId(userId);

     return optUserByUserId;
 }
	

//	//####################  SMS OTP Delivery  ####################################
//
//	public void smsOTPDelivery(long mobile_number, String name,String text, int otp) {
//
//		try {
//			// Mobile number with country code
//			String apiKey = "apikey=" + "your_api_key";
//
//			String message = text+" " + otp;
//			String sender = "&amp;sender=" + "OrangeSkills Technologies";
//			String numbers = "&amp;numbers=" + mobile_number;
//
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//			String data = apiKey + numbers + message + sender;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				stringBuffer.append(line);
//			}
//			rd.close();
//
//			System.out.println("OTP send Successfully");
//
//		} catch (Exception e) {
//			System.out.println("Error SMS " + e);
//
//		}
//	}

	//#########################  Mail Delivery  #############################



	public void mailDelivery(String to, String value,String subject,String text) throws MessagingException {

       SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(text + " " + value);

        javaMailSender.send(msg);
        System.out.println("Email sended!");
       
	}
	
	
}
