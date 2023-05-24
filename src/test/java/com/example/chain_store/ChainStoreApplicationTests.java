package com.example.chain_store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.repository.ProductDao;

@SpringBootTest
class ChainStoreApplicationTests {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	MembersDao membersDao;

	@Test
	void contextLoads() {
	}

	@Test
	void daoTest() {
		System.out.println(productDao.findByProductCode("a"));

	}
	
	@Test
	void  findByUseraccount() {
		membersDao.findByUseraccount("qwer");
		System.out.println(membersDao.findByUseraccount("retwyw"));
	}

}
