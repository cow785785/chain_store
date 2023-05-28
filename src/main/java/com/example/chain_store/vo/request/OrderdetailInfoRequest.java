package com.example.chain_store.vo.request;

import java.util.List;

import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.entity.Orderdetails;

public class OrderdetailInfoRequest {
	
	private String useraccount;

	private List<OrderdetailInfo> infoList;
	
	private Orderdetails orderdetails;
	
	private String deliveryAddress;

	public OrderdetailInfoRequest() {
	}

	public List<OrderdetailInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<OrderdetailInfo> infoList) {
		this.infoList = infoList;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public Orderdetails getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Orderdetails orderdetails) {
		this.orderdetails = orderdetails;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

}
