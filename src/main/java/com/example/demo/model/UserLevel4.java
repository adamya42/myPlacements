package com.example.demo.model;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "level4")
public class UserLevel4 { // department coordinator
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Userid")
	private int userID;

	@Column(name = "Name")
	private String name;
	

	@Column(name = "Designation")
	private String designation;
	

	@Column(name = "EmployeeId")
	private String employeeId;
	
	@Column(name = "PhoneNo")
	private long phoneNo;

	
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
	

}
