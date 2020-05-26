package com.example.demo.model;




import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "RoleId")
	private int roleId;
	
	@Column(name = "RoleAs")
	private String roleAs;
	

	//############################# 	Getter & Setter 	#################################################################

	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleAs() {
		return roleAs;
	}

	public void setRoleAs(String roleAs) {
		this.roleAs = roleAs;
	}
}
