
package com.example.chain_store.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.Date;
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

	@Column(name = "point")
	private int point;

	@Column(name = "email")
	private String email;
	
	@Column(name = "captcha")
	private int captcha;
	

	@Column(name = "registration_time")
	private Timestamp registrationTime;
	
	@Column(name = "active")
	private boolean active;

	public Members() {

	}
	
	

	public Members(String useraccount, String password, String username, LocalDate birthDate, String address,
			String phone, int point, String email, int captcha, Timestamp registrationTime,
			boolean active) {
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.point = point;
		this.email = email;
		this.captcha = captcha;
		this.registrationTime = registrationTime;
		this.active = active;
	}



	public Members(String useraccount, String password, String username, LocalDate birthDate, String address,
			String phone, String email,int captcha, Timestamp registrationTime) {
		this.useraccount = useraccount;
		this.password = password;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.captcha=captcha;
		this.registrationTime = registrationTime;
	}



//	public Members(String useraccount, String password, String username, LocalDate birthDate, String address,
//			String phone, int point, String email, Timestamp registrationTime) {
//		this.useraccount = useraccount;
//		this.password = password;
//		this.username = username;
//		this.birthDate = birthDate;
//		this.address = address;
//		this.phone = phone;
//		this.point = point;
//		this.email = email;
//		this.registrationTime = registrationTime;
//	}

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


	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	
	
//	public void setEmailVerified(boolean emailVerified) {
//        this.emailVerified = emailVerified;
//    }
//
//    public String getEmailVerificationToken() {
//        return emailVerificationToken;
//    }
//
//    public void setEmailVerificationToken(String emailVerificationToken) {
//        this.emailVerificationToken = emailVerificationToken;
//    }

	


	@Override
	public String toString() {
		return "Members [id=" + id + ", useraccount=" + useraccount + ", password=" + password + ", username="
				+ username + ", birthDate=" + birthDate + ", address=" + address + ", phone=" + phone + ", point="
				+ point + ", email=" + email + ", captcha=" + captcha + ", registrationTime=" + registrationTime
				+ ", active=" + active + "]";
	}



	public int getCaptcha() {
		return captcha;
	}



	public void setCaptcha(int captcha) {
		this.captcha = captcha;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}


}