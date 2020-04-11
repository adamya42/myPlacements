package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class Users {//implements UserDetails{
	
	

	@Id
	@Column(name = "Userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;

	
	@Size(min = 4,max = 8,message = "Please eneter between 4 to 8 characters")
	@Column(name = "Password")
	private String password;

	
	@Column(name = "Email",unique = true)
	@Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
	private String email;
	
	
	@com.sun.istack.NotNull
	@Column(name = "RoleId")
	private int roleId;
	
	
	

	@Column(name = "IsVerified",nullable = false, columnDefinition = "bit default 0")
	private boolean isVerified;

	  
	@Column(name = "IsActive",nullable = false, columnDefinition = "bit default 0")
	private boolean isActive;

	
	
	

	


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
	
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

	public boolean isVerified() {
		return isVerified;
	}

	public boolean isActive() {
		return isActive;
	}

	

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}

	
	

}
