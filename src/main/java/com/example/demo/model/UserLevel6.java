package com.example.demo.model;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "level6")
public class UserLevel6 {  // Students
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Userid")
	private int userID;

	@Column(name = "Name")
	private String name;
	

	@Column(name = "RegistrationId")
	private String registrationId;
	

	@Column(name = "School")
	private String school;

	@Column(name = "Branch")
	private String branch;

	@Column(name = "Gender")
	private byte gender = 4;// 4 MEANS NOT FILLED
	
//	@Column(name = "DOB")
//	private LocalDateTime dob;
	
	@Column(name = "DOB")
	private Date dob;
	
	
	

	@Column(name = "PhoneNo",columnDefinition = "0")
	private long phoneNo;
	
	
	

	//############################# 	Getter & Setter 	#################################################################

	
//	public LocalDateTime getDob() {
//		return dob;
//	}
//
//	public void setDob(LocalDateTime dob) {
//		this.dob = dob;
//	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}
	

}
