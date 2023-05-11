package com.example.chain_store.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
		// 排除空的request
		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
			return new OrderdetailsResponse("請檢查request內容");
		}
		List<Orderdetails> orderList = request.getOrderList();
		for (Orderdetails order : orderList) {
//			// 檢查帳號useraccount
//			if (!StringUtils.hasText(order.getUseraccount())) {
//				return new OrderdetailsResponse("無法取得useraccount");
//			}
//			// 檢查商品編號productCode
//			if (!StringUtils.hasText(order.getProductCode())) {
//				return new OrderdetailsResponse("無法取得product_code");
//			}
//			// 檢查商品金額是否大於0
//			// BigDecimal.ZERO為BigDecimal類別的0
//			// 判斷式類似於(productPrice - 0)<=0，其中(productPrice - 0)為BigDecimal類別
//			if (order.getProductPrice().compareTo(BigDecimal.ZERO) <= 0) {
//				return new OrderdetailsResponse("product_price異常");
//			}
//			// 判斷數量quantity
//			if (order.getQuantity() <= 0) {
//				return new OrderdetailsResponse("quantity異常");
//			}
//			// 判斷寄貨地址delivery_address
//			if (!StringUtils.hasText(order.getDeliveryAddress())) {
//				return new OrderdetailsResponse("無法取得delivery_address");
//			}
			// 執行內部方法確認order
			String checkResult = this.checkOrderdetails(order);
			if (!checkResult.equals("success")) {
				// 不成功則回傳訊息
				return new OrderdetailsResponse(checkResult);
			} else {//
				// 設定初始訂單狀態
				order.setOrderStatus("收到訂單");
				// 設定訂單時間，將當前系統時間寫入
				order.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
				// 設定ID
				order.setId(UUID.randomUUID().toString());
			}
		}
		orderdetailsDao.saveAll(orderList);
		return new OrderdetailsResponse(orderList, "新增成功");
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

	private String checkOrderdetails(Orderdetails order) {
		// 檢查帳號useraccount
		if (!StringUtils.hasText(order.getUseraccount())) {
			return "無法取得useraccount";
		}
		// 檢查商品編號productCode
		if (!StringUtils.hasText(order.getProductCode())) {
			return "無法取得product_code";
		}
		// 檢查商品金額是否大於0
		// BigDecimal.ZERO為BigDecimal類別的0
		// 判斷式類似於(productPrice - 0)<=0，其中(productPrice - 0)為BigDecimal類別
		if (order.getProductPrice().compareTo(BigDecimal.ZERO) <= 0) {
			return "product_price異常";
		}
		// 判斷數量quantity
		if (order.getQuantity() <= 0) {
			return "quantity異常";
		}
		// 判斷寄貨地址delivery_address
		if (!StringUtils.hasText(order.getDeliveryAddress())) {
			return "無法取得delivery_address";
		}
		return "success";
	}

}
