package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.model.helper_classes.UserInfo;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminServices;
import com.example.demo.services.MessageServices;
import com.example.demo.services.OTPServices;
import com.example.demo.services.UserServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
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
	
	@Autowired
	private UserServices userServices;
	
	
	//##################################### 	Update Single User		##########################################
	
	@PostMapping("/updateUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserInfo userInfo, BindingResult result) throws MessagingException {
		
		 ResponseEntity<?> errorMap = validationErrorServices.ValidationServiceHandler(result);
	        if(errorMap!=null) return errorMap;
	        
	        ResponseEntity<?> updateResponse = userServices.UpdateUserInfo(userInfo);
			return updateResponse;
		

	}














	
	//##################################### 		END OF USER CONTROLLER		######################################

}
