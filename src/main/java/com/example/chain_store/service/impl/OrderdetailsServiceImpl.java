package com.example.chain_store.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
				// 不成功則回傳內部方法訊息
				return new OrderdetailsResponse(checkResult);
			} else {// 開始寫入初始資料

				// 設定初始訂單狀態
				order.setOrderStatus("收到訂單");
				// 設定訂單時間，將當前系統時間寫入
				long currentTime = System.currentTimeMillis();
				order.setOrderTime(new Timestamp(currentTime));

				// 透過order資料生成訂單號
				String time6 = String.valueOf(currentTime % 1000000);// 時間戳末6碼
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
				String date4 = dateFormat.format(currentTime);// 月份日期4碼
				String account4 = order.getUseraccount().substring(0, 4);// 帳號前4碼
				String product4 = order.getProductCode().substring(0, 4);// 商品號前4碼
				// 訂單號:時間戳末6碼+月份日期4碼+帳號前4碼+商品號前4碼
				String newNumber = time6 + date4 + account4 + product4;
				// 設定訂單號
				order.setOrderNumber(newNumber);
			}
		}
		if (CollectionUtils.isEmpty(orderList)) {
			return new OrderdetailsResponse("沒有資料新增");
		}
		return new OrderdetailsResponse(orderList, "新增成功");
	}

	@Override
	public OrderdetailsResponse updateOrder(OrderdetailsRequest request) {
		// 排除空的request
		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
			return new OrderdetailsResponse("請檢查request內容");
		}
		List<Orderdetails> orderList = request.getOrderList();
		List<Orderdetails> upList = new ArrayList<>();
		for (Orderdetails order : orderList) {
			String orderNumber = order.getOrderNumber();
			boolean isUpdated = false;
			Orderdetails orderdetail = orderdetailsDao.findByOrderNumber(orderNumber);
			if (orderdetail == null) {
				return new OrderdetailsResponse("請檢查訂單號" + orderNumber);
			}
			if (StringUtils.hasText(order.getOrderStatus())) {// 若有值則更改訂單狀態
				orderdetail.setOrderStatus(order.getOrderStatus());
				isUpdated = true;
			}
			if (StringUtils.hasText(order.getDeliveryAddress())) {// 若有值則更改寄貨地址
				orderdetail.setDeliveryAddress(order.getDeliveryAddress());
				isUpdated = true;
			}
			if (order.getQuantity() > 0 && order.getQuantity() != orderdetail.getQuantity()) {// 若有變動則變更數量
				orderdetail.setQuantity(order.getQuantity());
				isUpdated = true;
			}
			if (isUpdated) {
				upList.add(orderdetail);
			}
		}
		if (CollectionUtils.isEmpty(upList)) {
			return new OrderdetailsResponse("沒有資料更新");
		}
		orderdetailsDao.saveAll(upList);
		return new OrderdetailsResponse(upList, "更新成功");
	}

	@Override
	public OrderdetailsResponse delOrder(OrderdetailsRequest request) {
		// 排除空的request
		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
			return new OrderdetailsResponse("請檢查request內容");
		}
		List<Orderdetails> orderList = request.getOrderList();
		List<Orderdetails> delList = new ArrayList<>();
		for (Orderdetails order : orderList) {
			String orderNumber = order.getOrderNumber();
			Orderdetails orderdetail = orderdetailsDao.findByOrderNumber(orderNumber);
			if (orderdetail == null) {
				return new OrderdetailsResponse("請檢查訂單號" + orderNumber);
			}
			delList.add(orderdetail);
		}
		if (CollectionUtils.isEmpty(delList)) {
			return new OrderdetailsResponse("沒有資料刪除 ");
		}
		orderdetailsDao.deleteAll(delList);
		return new OrderdetailsResponse(delList, "刪除成功");
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
