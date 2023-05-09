package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.Orderdetails;

public interface OrderdetailsDao extends JpaRepository<Orderdetails, String> {

	public List<Orderdetails> findByUseraccount(String useraccount);
}
