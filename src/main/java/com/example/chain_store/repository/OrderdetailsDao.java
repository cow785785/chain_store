package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chain_store.entity.Orderdetails;

public interface OrderdetailsDao extends JpaRepository<Orderdetails, String> {

	public List<Orderdetails> findByUseraccount(String useraccount);

	public Orderdetails findByOrderNumber(String orderNumber);

	@Query(value = "SELECT * FROM orderdetails a WHERE a.useraccount = ?1 ORDER BY a.order_time limit ?2", nativeQuery = true)
	public List<Orderdetails> findOrderdetailByUseraccountOrderByOrderTime(String account, int limit);
}
