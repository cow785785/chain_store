package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.Product;

public interface ProductDao extends JpaRepository<Product, String> {
	public Product findByProductCode(String productCode);

	public List<Product> findByProductName(String productName);

	public List<Product> findByCategory(String category);
	

}
