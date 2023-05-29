package com.example.chain_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chain_store.entity.Members;

@Repository
public interface MembersDao extends JpaRepository<Members, String> {
	Optional<Members> findByUseraccount(String userAccount);
	
	Optional<Members> findByUsername(String username);

	List<Members> findByUseraccountContaining(String keyword);
	
//	public Members findByUseraccountAndPassword(String account,String pwd); 
	
//	Optional<Members> findByUseraccountAndPassword(String account); 
	
	
}
