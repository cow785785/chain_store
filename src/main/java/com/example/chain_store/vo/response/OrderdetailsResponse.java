package com.example.chain_store.vo.response;

import java.util.List;

import com.example.chain_store.entity.Orderdetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderdetailsResponse {

	@JsonProperty("order_list")
	private List<Orderdetails> orderList;

	private String code;

	private String message;

	public OrderdetailsResponse() {
	}

	public OrderdetailsResponse(String message) {
		this.message = message;
	}

	public OrderdetailsResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public OrderdetailsResponse(List<Orderdetails> orderList, String message) {
		this.orderList = orderList;
		this.message = message;
	}

	public List<Orderdetails> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orderdetails> orderList) {
		this.orderList = orderList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
