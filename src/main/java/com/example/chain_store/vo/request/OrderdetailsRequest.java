package com.example.chain_store.vo.request;

import java.util.List;

import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.entity.Orderdetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderdetailsRequest {

	@JsonProperty("order_list")
	private List<OrderdetailInfo> orderList;

	@JsonProperty("new_list")
	private List<OrderdetailInfo> newList;

	private Orderdetails orderdetails;

	public OrderdetailsRequest() {

	}

	public List<OrderdetailInfo> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderdetailInfo> orderList) {
		this.orderList = orderList;
	}

	public List<OrderdetailInfo> getNewList() {
		return newList;
	}

	public void setNewList(List<OrderdetailInfo> newList) {
		this.newList = newList;
	}

	public Orderdetails getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Orderdetails orderdetails) {
		this.orderdetails = orderdetails;
	}

}
