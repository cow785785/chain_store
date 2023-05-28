package com.example.chain_store.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.chain_store.entity.Members;
import com.example.chain_store.entity.OrderdetailInfo;
import com.example.chain_store.entity.Orderdetails;
import com.example.chain_store.entity.Product;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.repository.OrderdetailInfoDao;
import com.example.chain_store.repository.OrderdetailsDao;
import com.example.chain_store.repository.ProductDao;
import com.example.chain_store.service.ifs.OrderdetailsService;
import com.example.chain_store.vo.request.OrderdetailsRequest;
import com.example.chain_store.vo.response.OrderdetailsResponse;

@Service
public class OrderdetailsServiceImpl implements OrderdetailsService {

	@Autowired
	private OrderdetailsDao orderdetailsDao;
	@Autowired
	private OrderdetailInfoDao infoDao;
	@Autowired
	private MembersDao membersDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public OrderdetailsResponse newOrder(OrderdetailsRequest request) {
		// 排除空的request
		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
			return new OrderdetailsResponse("請檢查request內容");
		}

		List<OrderdetailInfo> orderList = request.getOrderList();
		String newNumber = "";
		Orderdetails order = request.getOrderdetails();
		// 執行內部方法確認order
		String checkResult = this.checkOrderdetails(order);
		if (!checkResult.equals("success")) {
			// 不成功則回傳內部方法訊息
			return new OrderdetailsResponse(checkResult);
		} else {// 開始寫入初始資料

			Optional<Members> memberOP = membersDao.findByUseraccount(order.getUseraccount());
			if (!memberOP.isPresent()) {
				return new OrderdetailsResponse(order.getUseraccount() + "不存在。");
			}
			order.setMemberId(memberOP.get());

//			// 設定初始訂單狀態
//			order.setOrderStatus("收到訂單");
			// 設定訂單時間，將當前系統時間寫入
			long currentTime = System.currentTimeMillis();
			order.setOrderTime(new Timestamp(currentTime));

			// 透過order資料生成訂單號
			String time6 = String.valueOf(currentTime % 1000000);// 時間戳末6碼
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
			String date4 = dateFormat.format(currentTime);// 月份日期4碼
			String account4 = order.getUseraccount().substring(0, 4);// 帳號前4碼
			// 訂單號:時間戳末6碼+月份日期4碼+帳號前4碼+商品號前4碼
			newNumber = time6 + date4 + account4;
			// 設定訂單號
			order.setOrderNumber(newNumber);
		}

		if (CollectionUtils.isEmpty(orderList)) {
			return new OrderdetailsResponse("沒有資料新增");
		}
		orderdetailsDao.save(order);
		for (OrderdetailInfo info : orderList) {
			String infoResult = this.checkOrderInfo(info);
			if (infoResult != "success") {
				return new OrderdetailsResponse(infoResult);
			}
			info.setOrderNumber(newNumber);
			infoDao.save(info);
		}
		return new OrderdetailsResponse("新增成功");
	}

	@Override
	public OrderdetailsResponse updateOrder(OrderdetailsRequest request) {
		// 排除空的request
		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
			return new OrderdetailsResponse("請檢查request內容");
		}
//		List<Orderdetails> orderList = request.getOrderList();
//		List<Orderdetails> upList = new ArrayList<>();
		Orderdetails order = request.getOrderdetails();
		String orderNumber = order.getOrderNumber();
		boolean isUpdated = false;// 確認是否有更新
		Orderdetails originalOrder = orderdetailsDao.findByOrderNumber(orderNumber);
		if (originalOrder == null) {
			return new OrderdetailsResponse("訂單號" + orderNumber + "不存在。");
		}
		if (StringUtils.hasText(order.getOrderStatus())) {
			originalOrder.setOrderStatus(order.getOrderStatus());
			isUpdated = true;// 若有值則更改訂單狀態
		}

		if (StringUtils.hasText(order.getDeliveryAddress())) {
			originalOrder.setDeliveryAddress(order.getDeliveryAddress());
			isUpdated = true;// 若有值則更改寄貨地址
		}

		List<OrderdetailInfo> originalList = infoDao.findByOrderId(orderNumber);
		List<OrderdetailInfo> newList = request.getOrderList();
		if (!CollectionUtils.isEmpty(newList) && !newList.equals(originalList)) {
			for (OrderdetailInfo info : newList) {
				String result = this.checkOrderInfo(info);
				if (result != "success") {
					return new OrderdetailsResponse(result);
				}
				info.setOrderNumber(orderNumber);
			}
			infoDao.deleteAll(originalList);
			infoDao.saveAll(newList);
			isUpdated = true;// 若訂單內容不同則更改
		}
		if (order.getTotalPrice().compareTo(originalOrder.getTotalPrice()) != 0) {
			originalOrder.setTotalPrice(order.getTotalPrice());
			isUpdated = true;// 若有變動則變更
		}
		if (order.getQuantity() > 0 && order.getQuantity() != originalOrder.getQuantity()) {
			originalOrder.setQuantity(order.getQuantity());
			isUpdated = true;// 若有變動則變更數量
		}

		if (!isUpdated) {
			return new OrderdetailsResponse("沒有資料更新");
		}
		orderdetailsDao.save(order);
		return new OrderdetailsResponse("更新成功");
	}

	@Override
	public OrderdetailsResponse delOrder(OrderdetailsRequest request) {
//		// 排除空的request
//		if (request == null || CollectionUtils.isEmpty(request.getOrderList())) {
//			return new OrderdetailsResponse("請檢查request內容");
//		}
//		List<Orderdetails> orderList = request.getOrderList();
//		List<Orderdetails> delList = new ArrayList<>();
////		for (Orderdetails order : orderList) {
////			String orderNumber = order.getOrderNumber().getOrderNumber();
////			Orderdetails orderdetail = orderdetailsDao.findByOrderNumber(orderNumber);
////			if (orderdetail == null) {
////				return new OrderdetailsResponse("請檢查訂單號" + orderNumber);
////			}
////			delList.add(orderdetail);
////		}
//		if (CollectionUtils.isEmpty(delList)) {
//			return new OrderdetailsResponse("沒有資料刪除 ");
//		}
//		orderdetailsDao.deleteAll(delList);
//		return new OrderdetailsResponse(delList, "刪除成功");
		return null;
	}

	@Override
	public List<Orderdetails> getAllOrderdetails() {
		return orderdetailsDao.findAll();
	}

	@Override
	public List<Orderdetails> getOrderdetailsByUserAccount(String useraccount) {
		return orderdetailsDao.findByUseraccountOrderByOrderTimeDesc(useraccount);
	}

	public List<Orderdetails> findOrderdetailByUseraccountOrderByOrderTime(String account, int limit) {
		return orderdetailsDao.findOrderdetailByUseraccountAndNotCartOrderByOrderTime(account, limit);
	}

	public List<Orderdetails> findByUseraccountAndOrderStatus(String useraccount, String orderStatus) {
		return orderdetailsDao.findByUseraccountAndOrderStatus(useraccount, orderStatus);
	}

	@Override
	public OrderdetailsResponse refreshCart(OrderdetailsRequest request) {
		if (request == null) {
			return new OrderdetailsResponse("請檢查request內容");
		}
		List<OrderdetailInfo> dbList = request.getOrderList();
		List<OrderdetailInfo> newList = request.getNewList();

		if (!CollectionUtils.isEmpty(dbList)) {// 讓dbList進行delOrder方法
//			this.delOrder(new OrderdetailInfoRequest(dbList));
		}
		if (!CollectionUtils.isEmpty(newList)) {// 讓newList進行newOrder方法
//			this.newOrder(new OrderdetailInfoRequest(newList));
		}
		return new OrderdetailsResponse("交換成功");

	}

	private String checkOrderdetails(Orderdetails order) {
		// 檢查帳號useraccount
		if (!StringUtils.hasText(order.getUseraccount())) {
			return "無法取得useraccount";
		}

		if (!StringUtils.hasText(order.getOrderStatus())) {
			return "無法取得order_status";
		}
		// 檢查商品金額是否大於0
		// BigDecimal.ZERO為BigDecimal類別的0
		// 判斷式類似於(productPrice - 0)<=0，其中(productPrice - 0)為BigDecimal類別
		if (order.getTotalPrice() == null || order.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
			return "total_price異常";
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

	private String checkOrderInfo(OrderdetailInfo info) {
		if (info.getInfoQuantity() <= 0) {
			return "商品數量異常";
		}
		if (info.getInfoTotal() == null || info.getInfoTotal().compareTo(BigDecimal.ZERO) <= 0) {
			return "商品總價異常";
		}

		if (info.getProduct() == null || info.getProduct().getProductCode() == null) {
			return "商品代碼異常";
		}
		Product infoProduct = productDao.findByProductCode(info.getProduct().getProductCode());
		if (infoProduct == null) {
			return "商品代碼異常";
		}
		info.setProduct(infoProduct);
		return "success";
	}
}
