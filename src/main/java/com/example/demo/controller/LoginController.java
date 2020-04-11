package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminServices;
import com.example.demo.services.RoleServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private RoleServices roleServices;
	
//	@Autowired
//	private ValidationErrorServices validationErrorServices;

	
	@PostMapping("login")
	public ResponseEntity<?> login(@Valid @RequestBody Users user) throws MessagingException {

		Optional<Users> optUser = adminServices.getUserByMail(user.getEmail());		
		
		ResponseEntity<?> loginResponse = adminServices.authenticateLogin(optUser,user);
		return loginResponse;
		
		
	}

	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@Valid @RequestBody Users user) throws MessagingException {
		Users optUser = adminServices.getUserById(user.getUserID());
				//session required
		if (optUser.isActive()==true) {
			optUser.setActive(false);
			userRepository.save(optUser);
			return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
		}
		else {
			
		return new ResponseEntity<>("User << " + optUser.getEmail() + " >> Logout Failed", HttpStatus.NOT_FOUND);
	}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//##################################### 	END OF LOGIN CONTROLLER		######################################################################
}