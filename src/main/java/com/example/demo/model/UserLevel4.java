package com.example.demo.model;



import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "level4")
public class UserLevel4 { // department coordinator
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Userid")
	private int userID;

	@Column(name = "Name")
	private String name;
	

	@Column(name = "Designation")
	private String designation;
	

	@Column(name = "EmployeeId")
	private String employeeId;
	
	@Column(name = "PhoneNo",columnDefinition = "0")
	private long phoneNo;
	

	@Column(name = "Gender")
	private byte gender = 4;// 4 MEANS NOT FILLED
	
	@Column(name = "DOB")
	private LocalDateTime dob;

	
	//############################# 	Getter & Setter 	#################################################################

	

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
