package com.example.demo.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.helper_classes.UserInfo;
import com.example.demo.repository.Level2Repository;
import com.example.demo.repository.Level4Repository;
import com.example.demo.repository.Level6Repository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.SiteRepository.SchoolRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Level2Repository level2Repository;
	
	@Autowired
	private Level4Repository level4Repository;
	
	@Autowired
	private Level6Repository level6Repository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private RoleServices roleServices;
	
	@Autowired
	private GenderServices genderServices;

	public ResponseEntity<?> UpdateUserInfo(@Valid UserInfo userInfo) {
		
		Optional<Role> roleDetail = roleServices.getRoleByName(userInfo.getRole());
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//##################################### 	END OF USER SERVICES		##########################################
	
}
