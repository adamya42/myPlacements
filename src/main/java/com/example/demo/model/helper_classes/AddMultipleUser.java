package com.example.demo.model.helper_classes;

import java.util.ArrayList;
import java.util.List;



public class AddMultipleUser {
	List<String> emailList = new ArrayList<String>();
	private int roleId;
	//private String email;

//	public List<String> getMultipleEmails() {
//		return emails;
//	}
//	public void setMultipleEmails(List<String> multipleEmails) {
//		this.emails= multipleEmails;
//	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
//	public List<AddMultipleUser> getListentry() {
//		return listentry;
//	}
//	public void setListentry(List<AddMultipleUser> listentry) {
//		this.listentry = listentry;
//	}
//	public String getEmails() {
//		return email;
//	}
//	public void setEmails(String emails) {
//		this.email = emails;
//	}
//	public List<String> getEmails() {
//		return emails;
//	}
//	public void setEmails(List<String> emails) {
//		this.emails = emails;
//	}

	public List<String> getEmailList() {
		return emailList;
	}
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	}
