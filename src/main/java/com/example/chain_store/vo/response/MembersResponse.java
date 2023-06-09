package com.example.chain_store.vo.response;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.example.chain_store.entity.Members;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MembersResponse {

	private String userAccount;

	private String password;

	private String userName;

	private LocalDate birthDate;

	private String address;

	private String phone;


	private Integer point;

	private String email;

	private List<Members> members;

	private Timestamp registrationTime;

	private String message;

	public MembersResponse() {

	}
	
	


	public MembersResponse(String userAccount, String password, String userName, LocalDate birthDate, String address,
			String phone, Integer point, String email, String message) {
		this.userAccount = userAccount;
		this.password = password;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.point = point;
		this.email = email;
		this.message = message;
	}




	public MembersResponse(String userAccount, String password, String userName, LocalDate birthDate, String address,
			String phone, String message) {
		this.userAccount = userAccount;
		this.password = password;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.message = message;
	}

	public MembersResponse(String userAccount, String password, String userName, LocalDate birthDate, String address,
			String phone, String email, String message) {
		this.userAccount = userAccount;
		this.password = password;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.message = message;
	}

//	public MembersResponse(String userAccount, String password, String userName, Date birthDate,
//			String address, String phone, Timestamp registrationTime, String message) {
//		this.userAccount = userAccount;
//		this.password = password;
//		this.userName = userName;
//		this.birthDate = birthDate;
//		this.address = address;
//		this.phone = phone;
//		this.registrationTime = registrationTime;
//		this.message = message;
//	}

	public MembersResponse(String message, String userAccount, String userName, LocalDate birthDate, String address,
			String phone) {
		this.message = message;
		this.userAccount = userAccount;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;

	}

//	public MembersResponse(String userAccount, String password, String userName, Date birthDate,
//			String address, String phone, Timestamp registrationTime, String message) {
//		this.userAccount = userAccount;
//		this.password = password;
//		this.userName = userName;
//		this.birthDate = birthDate;
//		this.address = address;
//		this.phone = phone;
//		this.registrationTime = registrationTime;
//		this.message = message;
//	}

	public MembersResponse(String password, String userName, LocalDate birthDate, String address, String phone,
			String message) {
		super();
		this.password = password;
		this.userName = userName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.message = message;
	}

	public MembersResponse(String userAccount, String password, String message) {
		this.userAccount = userAccount;
		this.password = password;
		this.message = message;
	}

	public MembersResponse(String message) {
		this.message = message;
	}

	public MembersResponse(List<Members> members, String message) {
		this.members = members;
		this.message = message;
	}

	public MembersResponse(String userName, String message) {
		this.userName = userName;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Members> getMembers() {
		return members;
	}

	public void setMembers(List<Members> members) {
		this.members = members;
	}




	public Integer getPoint() {
		return point;
	}




	public void setPoint(Integer point) {
		this.point = point;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
