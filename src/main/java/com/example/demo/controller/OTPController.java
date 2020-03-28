package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserOTP;
import com.example.demo.model.Users;
import com.example.demo.services.AdminServices;
import com.example.demo.services.MessageServices;
import com.example.demo.services.OTPServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;


@RestController
@RequestMapping("/otp")
public class OTPController {
	
	@Autowired
	private OTPServices otpServices;
	
	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private MessageServices messageServices;
	
	
	
	
	// verifying OTP by using UserOTPId

	


	@GetMapping("/ValidateOTP")
	public ResponseEntity<?> verifyOTP(@Valid @RequestBody UserOTP userOTP, BindingResult result) {

		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
	
		List<UserOTP> optUser = otpServices.getUserByUserId(userOTP.getUserId()); 
		if (!optUser.isEmpty())
		{
			int enteredOTP=userOTP.getOtp();
			UserOTP userOTPValidate = otpServices.verifyOTP(optUser,enteredOTP);

				if (userOTPValidate == null)
				{
					return new ResponseEntity<>("User << " + userOTP.getUserId() + " >> Verified successfully!",
							HttpStatus.OK);
				}
				else 
				{
					return new ResponseEntity<>("OTP not Verified!", HttpStatus.NOT_FOUND);
				}

		}
		else 
		{
			return new ResponseEntity<>("Wrong UserID entered!! ", HttpStatus.NOT_FOUND);
		}
		
	}	
		
		
		// Forget Password
		@PutMapping("/ForgetPassword")
		public ResponseEntity<?> mailOTPDelivery(@Valid @RequestBody Users users, BindingResult result) throws MessagingException {
			
			if(result.hasErrors()) {
				return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
			}
			
			Optional<Users> optUser = (Optional<Users>) adminServices.getUserByMail(users.getEmail()); 
			
			if (optUser.isPresent())
			{
				int id=optUser.get().getUserID(); 
				
				
				
				int genOTP = otpServices.generatingOTP();
				otpServices.addOTP(genOTP, id);
				
				String otp=" "+genOTP;
				
				String mail = optUser.get().getEmail();
				String subject = messageServices.getMailSubjectForget();
				String text = messageServices.getMailTextForget();
				
				// otpServices.mailOTPDelivery(optUser.get().getEmail(), genOTP,subject,text);
				otpServices.mailDelivery(mail, otp,subject,text);
				return new ResponseEntity<>("Mail Send Successfully" + id, HttpStatus.OK); 
				} 
			else {
				
				return new ResponseEntity<>("User Not exist." + optUser.get().getUserID() + "", HttpStatus.NOT_FOUND);
			}

		}
		
		
//************	Resend OTP	*************
		
		@PutMapping("/ResendOTP")
		public ResponseEntity<?> resendOTP(@Valid @RequestBody Users users,BindingResult result) throws MessagingException {
			//@RequestParam ("userId") int userId
		
			if(result.hasErrors()) {
				return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
			}
			
			int userId= users.getUserID();
			Users user1 = adminServices.getUserById(userId);
				int genOTP = otpServices.generatingOTP();
				otpServices.addOTP(genOTP, userId);
				
				String otp=" "+genOTP;
				
				String subject = messageServices.getMailSubjectResend();
				String textMail = messageServices.getMailTextResend();
				
				
				otpServices.mailDelivery(user1.getEmail(), otp,subject,textMail);
				
			
				return new ResponseEntity<>("Resend OTP  Successfull" + userId, HttpStatus.OK);
			
			
		}



		
//##################################### 	END OF OTP CONTROLLER		######################################################################
}
