package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminServices;
import com.example.demo.services.RoleServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleServices roleServices;

	
	@PostMapping("login")
	public ResponseEntity<?> login(@Valid @RequestBody Users user, BindingResult result) throws MessagingException {
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
		Optional<Users> optUser = adminServices.getUserByMail(user.getEmail());
		
		
		int role = adminServices.authenticateLogin(optUser,user);
		Optional<Role> optRole = roleServices.getRoleById(role);
		if (role==0) {			
			return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
		}
		
		else if (role==2) {			
			return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
		}
		
		else if (role==4) {			
			return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
		}
		
		else if (role==6) {			
			return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
		}
		
		else {
		return new ResponseEntity<>("User << " + user.getEmail() + " >> Login Failed", HttpStatus.NOT_FOUND);
	}
	}

	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody Users user) throws MessagingException {
		Users optUser = adminServices.getUserById(user.getUserID());
				//session required
		if (optUser.getIsActive()==1) {
			optUser.setIsActive(0);
			userRepository.save(optUser);
			return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
		}
		else {
			
		return new ResponseEntity<>("User << " + optUser.getEmail() + " >> Logout Failed", HttpStatus.NOT_FOUND);
	}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//##################################### 	END OF LOGIN CONTROLLER		######################################################################
}