package com.example.chain_store.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.chain_store.constans.RtnCode;
import com.example.chain_store.entity.Members;
import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.entity.Orderdetails;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.repository.OrderdetailInfoDao;
import com.example.chain_store.repository.OrderdetailsDao;
import com.example.chain_store.repository.ProductDao;
import com.example.chain_store.service.ifs.OrderdetailInfoService;
import com.example.chain_store.vo.request.OrderdetailInfoRequest;
import com.example.chain_store.vo.response.OrderdetailInfoResponse;

@Service
public class OrderdetailInfoServiceImpl implements OrderdetailInfoService {

	@Autowired
	private OrderdetailInfoDao infoDao;
	@Autowired
	private MembersDao memberDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderdetailsDao orderdetailsDao;

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
