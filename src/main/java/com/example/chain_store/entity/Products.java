package com.example.chain_store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Products {
	@Id
	@Column(name = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Products() {
	}

	public Products(String id) {
		this.id = id;
	}
	

}
