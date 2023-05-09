package com.example.chain_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chain_store.entity.Members;

public interface MembersDao extends JpaRepository<Members, String> {
	
}
