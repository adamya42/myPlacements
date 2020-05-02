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
import com.example.demo.model.SiteModel.Schools;
import com.example.demo.model.helper_classes.UserInfo;
import com.example.demo.services.AdminServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@CrossOrigin
@RequestMapping("/site/api")
public class SiteController {
	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private ValidationErrorServices validationErrorServices;
	
	
	//##################################### 	Add School		##########################################
	
	@PostMapping("/admin/addSchool")
	public ResponseEntity<?> addUser(@Valid @RequestBody Schools school, BindingResult result) throws MessagingException {
		
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
		
		Optional<Schools> optSchool = adminServices.getSchoolbyCode(school.getSchoolCode());
		
		if (optSchool.isPresent()) {
			return new ResponseEntity<>("School Name already exist.", HttpStatus.NOT_FOUND);
		}
		else {
			
	//	school.setCreatedBy('session user');	
			
		
		adminServices.addSchool(school);		

		
		return new ResponseEntity<>("School << " + school.getSchoolName() + " >> Registered Successfully!", HttpStatus.OK);
	}
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//########################## 	END OF SITE CONTROLLER 	############################################################
}	
