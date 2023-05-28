package com.example.chain_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.service.ifs.OrderdetailInfoService;
import com.example.chain_store.vo.request.OrderdetailInfoRequest;
import com.example.chain_store.vo.response.OrderdetailInfoResponse;

@CrossOrigin
@RestController
public class OrderdetailInfoController {

	@Autowired
	private OrderdetailInfoService service;

	@PostMapping("/new_orderdetail_info")
	public OrderdetailInfoResponse newOrderdetailInfo(@RequestBody OrderdetailInfoRequest req) {
		return service.newOrderdetailInfo(req);
	}

	public OrderdetailInfoResponse updateOrderdetailInfo(@RequestBody OrderdetailInfoRequest req) {
		return service.updateOrderdetailInfo(req);
	}

	public OrderdetailInfoResponse delOrderdetailInfo(@RequestBody OrderdetailInfoRequest req) {
		return service.delOrderdetailInfo(req);
	}
	
	

}
