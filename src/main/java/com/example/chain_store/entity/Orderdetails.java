package com.example.chain_store.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "orderdetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orderdetails {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id")
	private UUID id;
	@Column(name = "order_number")
	private String orderNumber;
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Members memberId;
	@ManyToOne
	@JoinColumn(name = "products_id", referencedColumnName = "id")
	private Product productsId;
	@Column(name = "useraccount")
	private String useraccount;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "product_price")
	private BigDecimal productPrice;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "delivery_address")
	private String deliveryAddress;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "order_time")
	private Timestamp orderTime;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	

	public Product getProductsId() {
		return productsId;
	}

	public void setProductsId(Product productsId) {
		this.productsId = productsId;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
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
