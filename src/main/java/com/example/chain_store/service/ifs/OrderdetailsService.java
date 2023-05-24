package com.example.chain_store.service.ifs;

import java.util.List;

import com.example.chain_store.entity.Orderdetails;
import com.example.chain_store.vo.request.OrderdetailsRequest;
import com.example.chain_store.vo.response.OrderdetailsResponse;

public interface OrderdetailsService {
	public OrderdetailsResponse newOrder(OrderdetailsRequest request);

	public OrderdetailsResponse updateOrder(OrderdetailsRequest request);

	public OrderdetailsResponse delOrder(OrderdetailsRequest request);

	public List<Orderdetails> getAllOrderdetails();
	
	public List<Orderdetails> getOrderdetailsByUserAccount(String useraccount);
	
	public List<Orderdetails> findOrderdetailByUseraccountOrderByOrderTime(String account, int limit);
	
	public List<Orderdetails> findByUseraccountAndOrderStatus(String useraccount, String orderStatus);
}
