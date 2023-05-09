package com.example.chain_store.vo;

import com.example.chain_store.entity.Product;

public class ProductResponse {
	private Product product;

	private String message;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
