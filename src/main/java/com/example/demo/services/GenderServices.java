package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Gender;
import com.example.demo.repository.GenderRepository;

@Service
public class GenderServices {
	
	@Autowired
	private GenderRepository genderRepository;

	public Optional<Gender> getGenderById(int genderId) {
		//Byte gen = new Byte(genderId);
	Optional <Gender> gender = genderRepository.findById(genderId);
		return gender;
	}
}
