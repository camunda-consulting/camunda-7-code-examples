package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model;

import java.io.Serializable;

public class PlatformUser implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private String userType; // cockpit, tasklist 
	private String firstName;
	private String lastName;
	private String email;
	
	public PlatformUser(){
		this.userName = null;
		this.password = null;
		this.userType = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
	};
	
	public PlatformUser(String userName, String password, String userType, String firstName, String lastName, String email){
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
