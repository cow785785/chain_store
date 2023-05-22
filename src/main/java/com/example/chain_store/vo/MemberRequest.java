package com.example.chain_store.vo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class MemberRequest {
	
	private String useraccount;

	
	private String password;

	
	private String username;

	
	private LocalDate birthDate;

	
	private String address;

	
	private String phone;

	
	private Timestamp registrationTime;



	public String getUseraccount() {
		return useraccount;
	}


	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Timestamp getRegistrationTime() {
		return registrationTime;
	}


	public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}


	
}
