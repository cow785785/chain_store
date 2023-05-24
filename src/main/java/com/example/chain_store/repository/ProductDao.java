package com.example.chain_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.chain_store.entity.Product;

public interface ProductDao extends JpaRepository<Product, String> {
	public Product findByProductCode(String productCode);

	public List<Product> findByProductName(String productName);

	public List<Product> findByCategory(String category);

	@Query(value = "select * from products where product_name regexp :name "
			+ "or category regexp :name", nativeQuery = true)
	public List<Product> findByNameOrCategory(@Param("name") String category);
}
