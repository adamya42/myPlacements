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

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.model.SiteModel.Schools;
import com.example.demo.model.helper_classes.UserInfo;
import com.example.demo.services.AdminServices;
import com.example.demo.services.RoleServices;
import com.example.demo.services.SiteServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@CrossOrigin
@RequestMapping("/site")
public class SiteController {
	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private SiteServices siteServices;
	
	@Autowired
	private RoleServices roleServices;	
	
	@Autowired
	private ValidationErrorServices validationErrorServices;
	
	

	//##################################### 	Add Role		##########################################
	
	@PostMapping("/addRoles")
	public ResponseEntity<?> addRole(@Valid @RequestBody Role role, BindingResult result) throws MessagingException {
		
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
		
		Optional<Role> optRole = roleServices.getRoleById(role.getRoleId());
		
		if(optRole.isPresent()) {
			return new ResponseEntity<>("Role Name already exist.", HttpStatus.EXPECTATION_FAILED);
		}
		else {
			if(role.getRoleId()>=2) {
			roleServices.addRole(role);
			return new ResponseEntity<>("Role << " + role.getRoleAs() + " >> Registered Successfully!", HttpStatus.OK);
			
		}
			else { return new ResponseEntity<>("Un-Authorised", HttpStatus.EXPECTATION_FAILED);
			}
	
	}
	}
	//##################################### 	Add School		##########################################
	
	@PostMapping("/addSchool")
	public ResponseEntity<?> addSchool(@Valid @RequestBody Schools school, BindingResult result) throws MessagingException {
		
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
		
		Optional<Schools> optSchool = siteServices.getSchoolbyCode(school.getSchoolCode());
		
		if (optSchool.isPresent()) {
			return new ResponseEntity<>("School Name already exist.", HttpStatus.NOT_FOUND);
		}
		else {
			
	//	school.setCreatedBy('session user');	
			
		
		siteServices.addSchool(school);		

		
		return new ResponseEntity<>("School << " + school.getSchoolName() + " >> Registered Successfully!", HttpStatus.OK);
	}
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//########################## 	END OF SITE CONTROLLER 	############################################################
}	
