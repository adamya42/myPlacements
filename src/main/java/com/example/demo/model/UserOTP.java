package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "userotp")

public class UserOTP {

	@Id

	@Column(name = "UserOTPId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userOTPId;
	
	

	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "OTP")
	private int otp;
	

	@Column(name = "ValidTill")
	@JsonFormat(pattern = "dd-mm-yyyy")
	private LocalDateTime ValidTill;
	
	@Column(name = "IsOTPVerified")
	private int IsOTPVerified;
	
	@Column(name = "CreatedOn")
	@CreationTimestamp
	@JsonFormat(pattern = "dd-mm-yyyy")
	private LocalDateTime createdOn;
	
	
	
	//*****************************Generate Getter and Setter methods************************

	public int getUserOTPId() {
		return userOTPId;
	}

	public void setUserOTPId(int userOTPId) {
		this.userOTPId = userOTPId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int UserId) {
		this.userId = UserId;
	}



	public LocalDateTime getValidTill() {
		return ValidTill;
	}

	public void setValidTill(LocalDateTime validTill) {
		ValidTill = validTill;
	}

	public int getIsOTPVerified() {
		return IsOTPVerified;
	}

	public void setIsOTPVerified(int isOTPVerified) {
		IsOTPVerified = isOTPVerified;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
	
}

