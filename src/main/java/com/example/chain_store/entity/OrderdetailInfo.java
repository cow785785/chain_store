package com.example.chain_store.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail_info")
public class OrderdetailInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "order_id")
	private String orderId;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product productsId;

	@Column(name = "info_total")
	private BigDecimal infoTotal;

	@Column(name = "info_quantity")
	private int infoQuantity;

	public OrderdetailInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Product getProductsId() {
		return productsId;
	}

	public void setProductsId(Product productsId) {
		this.productsId = productsId;
	}

	public BigDecimal getInfoTotal() {
		return infoTotal;
	}

	public void setInfoTotal(BigDecimal infoTotal) {
		this.infoTotal = infoTotal;
	}

	public int getInfoQuantity() {
		return infoQuantity;
	}

	public void setInfoQuantity(int infoQuantity) {
		this.infoQuantity = infoQuantity;
	}

}
