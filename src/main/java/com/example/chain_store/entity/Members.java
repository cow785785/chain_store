package com.example.chain_store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "members")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Members {
	@Id
	@Column(name = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Members() {
	}

	public Members(String id) {
		this.id = id;
	}

}
