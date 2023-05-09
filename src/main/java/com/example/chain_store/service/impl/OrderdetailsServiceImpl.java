package com.example.chain_store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chain_store.entity.Orderdetails;
import com.example.chain_store.repository.OrderdetailsDao;
import com.example.chain_store.service.ifs.OrderdetailsService;
import com.example.chain_store.vo.request.OrderdetailsRequest;
import com.example.chain_store.vo.response.OrderdetailsResponse;

@Service
public class OrderdetailsServiceImpl implements OrderdetailsService {

	@Autowired
	private OrderdetailsDao orderdetailsDao;

	@Override
	public OrderdetailsResponse newOrder(OrderdetailsRequest request) {
		return null;
	}

	@Override
	public OrderdetailsResponse updateOrder(OrderdetailsRequest request) {
		return null;
	}

	@Override
	public OrderdetailsResponse delOrder(OrderdetailsRequest request) {
		return null;
	}

	@Override
	public List<Orderdetails> getAllOrderdetails() {
		return orderdetailsDao.findAll();
	}

	@Override
	public List<Orderdetails> getOrderdetailsByUserAccount(String useraccount) {
		return orderdetailsDao.findByUseraccount(useraccount);
	}

}
