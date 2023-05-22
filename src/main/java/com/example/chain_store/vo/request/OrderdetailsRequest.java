package com.example.chain_store.vo.request;

import java.util.List;

import com.example.chain_store.entity.Orderdetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderdetailsRequest {

	@JsonProperty("order_list")
	private List<Orderdetails> orderList;

	public OrderdetailsRequest() {

	}

	public OrderdetailsRequest(List<Orderdetails> orderList) {
		this.orderList = orderList;
	}

	public List<Orderdetails> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orderdetails> orderList) {
		this.orderList = orderList;
	}

}
