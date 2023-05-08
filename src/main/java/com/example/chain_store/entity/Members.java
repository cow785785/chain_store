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
}
