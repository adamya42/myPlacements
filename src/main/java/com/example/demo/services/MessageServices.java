package com.example.demo.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "message.default")
public class MessageServices {
	
	
private String mailTextForget;
	
	private String mailSubjectRegister;
	private String mailTextRegister;
	
	private String mailSubjectResend;
	private String mailTextResend;
	
	
	private String smsTextForget;
	private String smsTextRegister;
	private String smsTextResend;
	
	private String mailSubjectCredentials;	
	private String mailTextCredentials;
	
	
	
	
	
//*****************************Getter and Setter Methods*************************************	
	
	
	public String getMailSubjectForget() {
		return mailSubjectForget;
	}
	public void setMailSubjectForget(String mailSubjectForget) {
		this.mailSubjectForget = mailSubjectForget;
	}
	public String getMailTextForget() {
		return mailTextForget;
	}
	public void setMailTextForget(String mailTextForget) {
		this.mailTextForget = mailTextForget;
	}
	public String getMailSubjectRegister() {
		return mailSubjectRegister;
	}
	public void setMailSubjectRegister(String mailSubjectRegister) {
		this.mailSubjectRegister = mailSubjectRegister;
	}
	public String getMailTextRegister() {
		return mailTextRegister;
	}
	public void setMailTextRegister(String mailTextRegister) {
		this.mailTextRegister = mailTextRegister;
	}
	public String getSmsTextForget() {
		return smsTextForget;
	}
	public void setSmsTextForget(String smsTextForget) {
		this.smsTextForget = smsTextForget;
	}
	public String getSmsTextRegister() {
		return smsTextRegister;
	}
	public String getMailSubjectCredentials() {
		return mailSubjectCredentials;
	}
	public void setMailSubjectCredentials(String mailSubjectCredentials) {
		this.mailSubjectCredentials = mailSubjectCredentials;
	}
	public String getMailtextCredentials() {
		return mailTextCredentials;
	}
	public void setMailtextCredentials(String mailtextCredentials) {
		this.mailTextCredentials = mailtextCredentials;
	}
	public void setSmsTextRegister(String smsTextRegister) {
		this.smsTextRegister = smsTextRegister;
	}
	private String mailSubjectForget;
	public String getMailSubjectResend() {
			return mailSubjectResend;
		}
		public void setMailSubjectResend(String mailSubjectResend) {
			this.mailSubjectResend = mailSubjectResend;
		}
		public String getMailTextResend() {
			return mailTextResend;
		}
		public void setMailTextResend(String mailTextResend) {
			this.mailTextResend = mailTextResend;
		}
		public String getSmsTextResend() {
			return smsTextResend;
		}
		public void setSmsTextResend(String smsTextResend) {
			this.smsTextResend = smsTextResend;
		}
	
	

}

