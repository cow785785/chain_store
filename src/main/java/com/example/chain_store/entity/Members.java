
package com.example.chain_store.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
	private LocalDate birthDate;
	@Column(name = "address")
	private String address;
	@Column(name = "phone")
	private String phone;
	@Column(name = "registration_time")
	private Timestamp registrationTime;
	@Column(name = "status")
	private boolean status;
	@Column(name = "email")
	private String email;
	@Column(name = "point")
	private int point;

	public Members() {

	}

	public Members(String useraccount, String password, String username, LocalDate birthDate, String address,
			String phone, Timestamp registrationTime) {
		super();
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.registrationTime = registrationTime;
	}

	public Members(UUID id, String useraccount, String password, String username, LocalDate birthDate, String address,
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

	public Members(UUID id, String useraccount, String password, String username, LocalDate birthDate, String address,
			String phone, Timestamp registrationTime, boolean status, String email, int point) {
		super();
		this.id = id;
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.registrationTime = registrationTime;
		this.status = status;
		this.email = email;
		this.point = point;
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

	@Override
	public String toString() {
		return "Member{" + "id='" + id + '\'' + ", useraccount='" + useraccount + '\'' + ", password='" + password
				+ '\'' + ", username='" + username + '\'' + ", birthdate=" + birthDate + ", address='" + address + '\''
				+ ", phone='" + phone + '\'' + ", registrationTime=" + registrationTime + '}';
	}
}