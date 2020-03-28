package com.example.demo.model;


import java.sql.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "level6")
public class UserLevel6 {  // Students
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private int gender;
	
	@Column(name = "PhoneNo")
	private long phoneNo;
	
	@Column(name = "DOB")
	private Date dob;
	

	//############################# 	Getter & Setter 	#################################################################

	

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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	

}
