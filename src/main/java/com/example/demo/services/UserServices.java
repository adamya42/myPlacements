package com.example.demo.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.UserLevel2;
import com.example.demo.model.UserLevel4;
import com.example.demo.model.UserLevel6;
import com.example.demo.model.Users;
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
	
	@Autowired
	private AdminServices adminServices;

	public ResponseEntity<?> UpdateUserInfo(@Valid UserInfo userInfo) {
		
		//Optional<Role> roleDetail = roleServices.getRoleByName(userInfo.getRole());
		
		Optional<Users> users = adminServices.getUserById(userInfo.getId());
		if(users.get().getRoleId()==0)
			return new ResponseEntity<>("Update Successful..Hello"+" "+ users.get().getEmail(), HttpStatus.OK);
		else if(users.get().getRoleId()==2) {
			
			UserLevel2 user = level2Repository.findById(userInfo.getLevelId()).get();
			user.setDesignation(userInfo.getDepartment());
			user.setDob(userInfo.getBday());
			user.setEmployeeId(userInfo.getUniqueId());
			user.setGender((byte) 1);
			//user.setId(userInfo.getLevelId());
			user.setName(userInfo.getName());
			user.setPhoneNo(userInfo.getPhoneNo());
			//user.setUserID(userInfo.getId());
			
			level2Repository.save(user);
			return new ResponseEntity<>("Update Successful..Hello"+" "+ users.get().getEmail(), HttpStatus.OK);
		}
			
			else if(users.get().getRoleId()==4) {
				
				UserLevel4 user = level4Repository.findById(userInfo.getLevelId()).get();
				user.setDesignation(userInfo.getDepartment());
				user.setDob(userInfo.getBday());
				user.setEmployeeId(userInfo.getUniqueId());
				user.setGender((byte) 1);
			//	user.setId(userInfo.getLevelId());
				user.setName(userInfo.getName());
				user.setPhoneNo(userInfo.getPhoneNo());
				//user.setUserID(userInfo.getId());
				
				level4Repository.save(user);
				return new ResponseEntity<>("Update Successful..Hello"+" "+ users.get().getEmail(), HttpStatus.OK);
			
		}
		
			else {
				
				UserLevel6 user =level6Repository.findById(userInfo.getLevelId()).get();
				String[] department = userInfo.getDepartment().split("-");
				user.setSchool(department[0]);
				user.setBranch(department[1]);
				user.setDob(userInfo.getBday());
				user.setRegistrationId(userInfo.getUniqueId());
				user.setGender((byte) 1);
				//user.setId(userInfo.getLevelId());
				user.setName(userInfo.getName());
				user.setPhoneNo(userInfo.getPhoneNo());
			//	user.setUserID(userInfo.getId());
				
				level6Repository.save(user);
				return new ResponseEntity<>("Update Successful..Hello"+" "+ users.get().getEmail(), HttpStatus.OK);
			}
		
		
		
		
		
		//return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//##################################### 	END OF USER SERVICES		##########################################
	
}
