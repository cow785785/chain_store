package com.example.chain_store.controller;

import java.util.List;
import java.util.Map;

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

	@PostMapping("/new_order")
	public OrderdetailsResponse newOrder(@RequestBody OrderdetailsRequest request) {
		return orderdetailsService.newOrder(request);
	}

	@PostMapping("/update_order")
	public OrderdetailsResponse updateOrder(@RequestBody OrderdetailsRequest request) {
		return orderdetailsService.updateOrder(request);
	}

	@PostMapping("/del_order")
	public OrderdetailsResponse delOrder(@RequestBody OrderdetailsRequest request) {
		return orderdetailsService.delOrder(request);
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
	
	@PostMapping("/get_orderdetails_by_useraccount_limit")
	public List<Orderdetails> getOrderdetailsByUseraccountLimit(@RequestBody Map<String, Object> requestBody) {
		String useraccount = (String) requestBody.get("useraccount");
		int limit = (int) requestBody.get("limit");
		return orderdetailsService.findOrderdetailByUseraccountOrderByOrderTime(useraccount, limit);
	}

	@PostMapping("/get_order_by_useraccount_and_order_status")
	public List<Orderdetails> findByUseraccountAndOrderStatus(@RequestBody Map<String, Object> requestBody){
		String useraccount = (String) requestBody.get("useraccount");
		String orderStatus = (String) requestBody.get("orderStatus");
		return orderdetailsService.findByUseraccountAndOrderStatus(useraccount, orderStatus);
	}

}
