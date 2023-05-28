package com.example.chain_store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.chain_store.constans.RtnCode;
import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.service.ifs.OrderdetailInfoService;
import com.example.chain_store.vo.request.OrderdetailInfoRequest;
import com.example.chain_store.vo.response.OrderdetailInfoResponse;

@Service
public class OrderdetailInfoServiceImpl implements OrderdetailInfoService {



	@Override
	public OrderdetailInfoResponse newOrderdetailInfo(OrderdetailInfoRequest req) {

		if (req == null || CollectionUtils.isEmpty(req.getInfoList())) {
			return new OrderdetailInfoResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		return null;
	}

	@Override
	public OrderdetailInfoResponse updateOrderdetailInfo(OrderdetailInfoRequest req) {
		return null;
	}

	@Override
	public OrderdetailInfoResponse delOrderdetailInfo(OrderdetailInfoRequest req) {
		return null;
	}

	@Override
	public List<OrderdetailInfo> findByOrderId(String orderId) {
		return null;
	}
}
