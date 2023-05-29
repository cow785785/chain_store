package com.example.chain_store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.repository.ProductDao;

@SpringBootTest
class ChainStoreApplicationTests {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	ProductDao productDao;

	@Autowired
	MembersDao membersDao;

	@Test
	void contextLoads() {
	}

	@Test
	void daoTest() {
		System.out.println(productDao.findByNameOrCategory("Lunch").size());

	}

	@Test
	void findByUseraccount() {
		membersDao.findByUseraccount("qwer");
		System.out.println(membersDao.findByUseraccount("retwyw"));
	}

	@Test
	void sendToGmail() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo("qwer030199@gmail.com");
		message.setSubject("測試透過 Gmail 去發信");
		message.setText("org.springframework.mail.SimpleMailMessage 透過 Gmail 發信。");

		mailSender.send(message);
	}

}
