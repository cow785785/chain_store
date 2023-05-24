package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chain_store.entity.Orderdetails;

public interface OrderdetailsDao extends JpaRepository<Orderdetails, String> {

	public List<Orderdetails> findByUseraccountOrderByOrderTimeDesc(String useraccount);

	public Orderdetails findByOrderNumber(String orderNumber);

	@Query(value = "SELECT * FROM orderdetails a WHERE a.useraccount = ?1 and a.order_status != 'カート入り'ORDER BY a.order_time DESC limit ?2", nativeQuery = true)
	public List<Orderdetails> findOrderdetailByUseraccountAndNotCartOrderByOrderTime(String account, int limit);

	public List<Orderdetails> findByUseraccountAndOrderStatus(String account, String orderStatus);

}
