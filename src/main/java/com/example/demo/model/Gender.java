package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name = "gender")
public class Gender {

	

	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genderCode;
	
	@Column(name = "meaning")
	private String gender;
	
	//################################################################
	
	public int getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(int genderCode) {
		this.genderCode = genderCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
