package com.example.chain_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.Orderdetails;

public interface OrderdetailsDao extends JpaRepository<Orderdetails, String> {
	
}
