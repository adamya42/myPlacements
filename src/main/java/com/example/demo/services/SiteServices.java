package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SiteModel.Schools;
import com.example.demo.repository.SiteRepository.SchoolRepository;
@Service
public class SiteServices {

	@Autowired
	private SchoolRepository schoolRepository;
	
	
	
	//################ 	 Find School by Code 	###########################
	
	public Optional<Schools> getSchoolbyCode(String schoolCode) {
		
		 Optional<Schools> optSchoolbyCode = schoolRepository.findBySchoolCode(schoolCode); 
		  return  optSchoolbyCode;				
		
	}


//################ 	 Add School  	###########################			

	public void addSchool(Schools school) {
		schoolRepository.save(school);
		
	}
}
