package com.example.chain_store.service.ifs;

import java.util.List;

import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.vo.request.OrderdetailInfoRequest;
import com.example.chain_store.vo.response.OrderdetailInfoResponse;

public interface OrderdetailInfoService {

	public OrderdetailInfoResponse newOrderdetailInfo(OrderdetailInfoRequest req);

	public OrderdetailInfoResponse updateOrderdetailInfo(OrderdetailInfoRequest req);

	public OrderdetailInfoResponse delOrderdetailInfo(OrderdetailInfoRequest req);
	
	public List<OrderdetailInfo> findByOrderId(String orderId);
}
