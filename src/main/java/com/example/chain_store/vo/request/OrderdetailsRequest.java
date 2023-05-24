package com.example.chain_store.vo.request;

import java.util.List;

import com.example.chain_store.entity.Orderdetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderdetailsRequest {

	@JsonProperty("order_list")
	private List<Orderdetails> orderList;

	@JsonProperty("new_list")
	private List<Orderdetails> newList;

	public OrderdetailsRequest() {

	}

	public OrderdetailsRequest(List<Orderdetails> orderList) {
		this.orderList = orderList;
	}

	public OrderdetailsRequest(List<Orderdetails> orderList, List<Orderdetails> newList) {
		this.orderList = orderList;
		this.newList = newList;
	}

	public List<Orderdetails> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orderdetails> orderList) {
		this.orderList = orderList;
	}

	public List<Orderdetails> getNewList() {
		return newList;
	}

	public void setNewList(List<Orderdetails> newList) {
		this.newList = newList;
	}

}
