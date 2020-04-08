package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminServices;
import com.example.demo.services.MessageServices;
import com.example.demo.services.OTPServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.mail.iap.Response;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/admin/api")
public class ControllerAdmin {

	
		@Autowired
		private AdminServices adminServices;
			
		@Autowired
		private UserRepository userRepository;

		@Autowired
		private OTPServices otpServices;

		@Autowired
		private MessageServices messageServices;
		
		@Autowired
		private ValidationErrorServices validationErrorServices;
		
		// private static final Logger logger= (Logger)
		// LogFactory.getLog(Controller.class);
		
		private final Logger log = LoggerFactory.getLogger(this.getClass());

	//##################################### 	Welcome		##########################################
		
		@RequestMapping("/welcome")
		public String display() {
			
			log.debug("welcome window");

			return "WELCOME......!!!!YES!!!! Its Working Appliaction , Go Ahead";
			

		}

	//##################################### 	Display All Users		##########################################	
		
		@GetMapping("/users")
		public ResponseEntity<?> getAllUser(@RequestParam(name = "userID",required = false)String id) {
			if(StringUtils.isNotEmpty(id)) {
				return new ResponseEntity<>(adminServices.getUserById(Integer.parseInt(id)),HttpStatus.OK);
			}
			List<Users> userList = adminServices.getAllUsers();
			if (userList.isEmpty()) {
				return new ResponseEntity<>("No Details Found In Database!", HttpStatus.NOT_FOUND);
			}
			log.debug("Users found");
			return new ResponseEntity<>(userList, HttpStatus.OK);

		}

	//##################################### 	Fetch User by Id		##########################################
		
//
//		@GetMapping("/users/{id}")
//		public ResponseEntity<?> getUserById(@Valid @PathVariable(name = "id") int userID) {
//			Users user = adminServices.getUserById(userID);
//
//			if (user == null) {
//				return new ResponseEntity<>("This User << " + userID + " >> details does not exist in database!",
//						HttpStatus.NOT_FOUND);
//			} else {
//
//				// return new ResponseEntity<>("Existing Record for User Id <<
//				// "+user.getUserID()+" >> is --"+ user,HttpStatus.OK);
//				return new ResponseEntity<>(user, HttpStatus.OK);
//			}
//		}

	//##################################### 	Add Single User		##########################################
			
		@PostMapping("/addSingleUser")
		public ResponseEntity<?> addUser(@Valid @RequestBody Users user, BindingResult result) throws MessagingException {
			
			 ResponseEntity<?> errorMap = validationErrorServices.ValidationServiceHandler(result);
		        if(errorMap!=null) return errorMap;
			
			Optional<Users> optUser =  adminServices.getUserByMail(user.getEmail());
			
			
			
			if (optUser.isPresent()) {
				return new ResponseEntity<>("User Name already exist.", HttpStatus.NOT_FOUND);
			}
			else {
				
		//	user.setCreatedBy('session user');	
				
			
			adminServices.addUser(user);

			String mail = user.getEmail();
			String subject = messageServices.getMailSubjectCredentials();
			String text = messageServices.getMailtextCredentials();
			
			String credential=user.getPassword();
			
		//---------Send Credentials---------- 	
			otpServices.mailDelivery(mail, credential,subject,text);
			
			return new ResponseEntity<>("User << " + user.getEmail() + " >> Registered and credentials Send Successfully!", HttpStatus.OK);
		}
		}

		
	//##################################### 	Delete User By Id		##########################################
		
		
		@DeleteMapping("/users/delete/{id}")
		public ResponseEntity<?> deleteUserByID(@Valid @PathVariable(name = "id") int userID) {
			Users user = adminServices.getUserById(userID);
			if (user == null) {
				return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
			}
			adminServices.deleteUserByID(user);
			return new ResponseEntity<>("User with USERID <<  " + user.getUserID() + "  >> Deleted successfully!",
					HttpStatus.OK);

			
		}
		
	//##################################### 	E		##########################################
		
		@DeleteMapping("/users/deleteAll")
		public ResponseEntity<?> deleteAllUsers() {
			
			adminServices.deleteAllUsers();;
			return new ResponseEntity<>("All Users  Deleted successfully!",
					HttpStatus.OK);			
		}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//##################################### 	END OF ADMIN CONTROLLER		######################################################################
}
