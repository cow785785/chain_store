package com.example.chain_store.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "price")
	private Integer price;

	@Column(name = "product_img")
	private String productImg;

	@Column(name = "product_info")
	private String productInfo;

	@Column(name = "product_describe")
	private String productDescribe;

	@Column(name = "category")
	private String category;

	@Column(name = "inventory")
	private Integer inventory;

	public Product() {
		super();
	}

	public Product(UUID id, String productCode, String productName, Integer price,
			String productImg, String productInfo, String productDescribe,
			String category, Integer inventory) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.productImg = productImg;
		this.productInfo = productInfo;
		this.productDescribe = productDescribe;
		this.category = category;
		this.inventory = inventory;
	}

	public Product(String productCode) {
		this.productCode = productCode;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

}
