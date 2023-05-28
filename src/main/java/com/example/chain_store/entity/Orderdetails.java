package com.example.chain_store.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "orderdetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orderdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "order_number")
	private String orderNumber;
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Members memberId;
	@Column(name = "useraccount")
	private String useraccount;
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "delivery_address")
	private String deliveryAddress;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "order_time")
	private Timestamp orderTime;

	public Orderdetails() {
	}

	public Orderdetails(int id, String orderNumber, Members memberId, String useraccount, BigDecimal totalPrice,
			int quantity, String deliveryAddress, String orderStatus, Timestamp orderTime) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.memberId = memberId;
		this.useraccount = useraccount;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.orderStatus = orderStatus;
		this.orderTime = orderTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Members getMemberId() {
		return memberId;
	}

	public void setMemberId(Members memberId) {
		this.memberId = memberId;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

}
