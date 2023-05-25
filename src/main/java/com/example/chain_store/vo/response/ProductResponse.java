package com.example.chain_store.vo.response;

import java.util.List;

import com.example.chain_store.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

	private Product product;

	private String message;

	private List<Product> list;

	public ProductResponse() {
		super();
	}

	public ProductResponse(Product product, String message) {
		super();
		this.product = product;
		this.message = message;
	}

	public ProductResponse(Product product) {
		super();
		this.product = product;
	}

	public ProductResponse(List<Product> list) {
		super();
		this.list = list;
	}

	public ProductResponse(String message) {
		super();
		this.message = message;
	}

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

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

}
