package com.example.chain_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Orderdetails;
import com.example.chain_store.service.ifs.OrderdetailsService;
import com.example.chain_store.vo.request.OrderdetailsRequest;
import com.example.chain_store.vo.response.OrderdetailsResponse;

@CrossOrigin
@RestController
public class OrderdetailsController {

	@Autowired
	private OrderdetailsService orderdetailsService;

	public OrderdetailsResponse newOrder(@RequestBody OrderdetailsRequest request) {
		return new OrderdetailsResponse();
	}

	public OrderdetailsResponse updateOrder(@RequestBody OrderdetailsRequest request) {
		return new OrderdetailsResponse();
	}

	public OrderdetailsResponse delOrder(@RequestBody OrderdetailsRequest request) {
		return new OrderdetailsResponse();
	}

	// 取得整個資料表
	@GetMapping("/get_all_orderdetails")
	public List<Orderdetails> getAllOrderdetails() {
		return orderdetailsService.getAllOrderdetails();
	}

	@PostMapping("get_orderdetails_by_useraccount")
	public List<Orderdetails> getOrderdetailsByUseraccount(@RequestBody String useraccount) {
		return orderdetailsService.getOrderdetailsByUserAccount(useraccount);
	}
}
