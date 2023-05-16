package com.example.chain_store.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.chain_store.repository.MembersDao;

@Entity
@Table(name = "members")
public class Members {
	

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	@Column(name = "useraccount")
	private String useraccount;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "birthdate")
	private Date birthDate;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "registration_time")
	private Timestamp registrationTime;
	

	public Members() {

	}

	public Members(String useraccount) {

	}

	public Members(String useraccount, String password, String username, Date birthDate, String address, String phone,
			Timestamp registrationTime) {
		super();
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.registrationTime = registrationTime;
	}

	public Members(UUID id, String useraccount, String password, String username, Date birthDate, String address,
			String phone, Timestamp registrationTime) {
		this.id = id;
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.registrationTime = registrationTime;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Timestamp getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Override
	public String toString() {
		return "Member{" + "id='" + id + '\'' + ", useraccount='" + useraccount + '\'' + ", password='" + password
				+ '\'' + ", username='" + username + '\'' + ", birthdate=" + birthDate + ", address='" + address + '\''
				+ ", phone='" + phone + '\'' + ", registrationTime=" + registrationTime + '}';
	}
}
