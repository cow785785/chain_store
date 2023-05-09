package com.example.chain_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.Product;

public interface ProductsDao extends JpaRepository<Product, String> {

}
