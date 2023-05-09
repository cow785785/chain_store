package com.example.chain_store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Members {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "useraccount")
	private String userAccount;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String userName;

	@Column(name = "birthdate")
	private Date birthDate;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "registration_time")
	private Date registrationTime;

	public Members() {

	}

	public Members(String id, String userAccount, String password, String userName, Date birthDate, String address,
			String phone, Date registrationTime) {
		this.id = id;
		this.userAccount = userAccount;
		this.password = password;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.registrationTime = registrationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
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

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	
}