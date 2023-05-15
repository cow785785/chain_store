package com.example.chain_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Members;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.service.ifs.MembersService;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

@RestController
public class MemberController {
	
	@Autowired
	private MembersDao membersDao;
	
	@Autowired
	private MembersService membersService;
	
	@PostMapping("/addMember")
	public MembersResponse addMember(@RequestBody MemberRequest memberRequest) {
		return membersService.addMember(memberRequest);
	}
	
	@PostMapping("/readMember")
	public List<Members> readMember() {
		return membersDao.findAll();
	}
	
	@PostMapping("/selectMember")
	public MembersResponse selectMember(@RequestBody MemberRequest memberRequest) {
		return membersService.selectMember(memberRequest);
	}
	
	@PostMapping("/updateMember")
	public MembersResponse updateMember(@RequestBody MemberRequest memberRequest) {
		return membersService.updateMember(memberRequest);
	}
	
	@PostMapping("/deleteMember")
	public MembersResponse deleteMember(@RequestBody MemberRequest memberRequest) {
		return membersService.deleteMember(memberRequest);
	}

}
