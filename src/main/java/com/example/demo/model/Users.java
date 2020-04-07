package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class Users {
	
	

	@Id
	@Column(name = "Userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;

	
	@Size(min = 4,max = 8,message = "Please eneter between 4 to 8 characters")
	@Column(name = "Password")
	private String password;

	
	@Column(name = "Email",unique = true)
	private String email;
	
	
	
	@Column(name = "RoleId")
	private int roleId;
	
	  
	@Column(name = "IsVerified")
	private int isVerified;

	  
	@Column(name = "IsActive")
	private int isActive;

	@Column(name = "IsDeleted")
	private int isDeleted;
	

	


	//// use @CreationTimestamp to generate current date or use below
	//// @Temporal(TemporalType.TIMESTAMP) with @PrePersist

	@Column(name = "CreatedOn")
	@CreationTimestamp
	@JsonFormat(pattern = "dd-mm-yyyy")
	private LocalDateTime createdOn;

	

	@Column(name = "CreatedBy")
	private Integer createdBy;

	

	@Column(name = "ModifiedOn")
	@UpdateTimestamp
	@JsonFormat(pattern = "dd-mm-yyyy")
	private LocalDateTime modifiedOn;



	@Column(name = "ModifiedBy")
	private Integer modifiedBy;
	

	//##################################  Getter and Setter #################################

		

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}



	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	

}
