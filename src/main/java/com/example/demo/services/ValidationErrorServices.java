package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorServices {
	
	public ResponseEntity<?> ValidationServiceHandler(BindingResult result){
	
		if(result.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors() ) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<String>("Invalid user object passed",HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
