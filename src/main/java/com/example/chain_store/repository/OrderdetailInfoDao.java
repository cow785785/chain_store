package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.OrderdetailInfo;

public interface OrderdetailInfoDao extends JpaRepository<OrderdetailInfo, Integer> {

	public List<OrderdetailInfo> findByOrderId(String orderId);
}
