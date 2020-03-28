package com.example.demo.model.SiteModel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "schools")
public class Schools {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "SchoolName")
	private String schoolName;

	@Column(name = "SchoolCode",unique = true)
	private String schoolCode;
	
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
	
//####################### 	GETTERS & SETTERS 	#########################################################

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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
	
	
//####################### 	END OF SCHOOL MODEL 	#########################################################

}
