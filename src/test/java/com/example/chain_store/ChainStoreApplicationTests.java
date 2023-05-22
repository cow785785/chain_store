package com.example.chain_store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.chain_store.repository.ProductDao;

@SpringBootTest
class ChainStoreApplicationTests {
	@Autowired
	ProductDao productDao;

	@Test
	void contextLoads() {
	}

	@Test
	void daoTest() {
		System.out.println(productDao.findByProductCode("a"));

	}

}
