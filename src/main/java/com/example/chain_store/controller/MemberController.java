package com.example.chain_store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Members;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.service.ifs.MembersService;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

@CrossOrigin
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
		return membersService.readMember();
	}
	
	@PostMapping("/readMember2")
	public MembersResponse readMember2(@RequestBody MemberRequest memberRequest) {
		return membersService.readMember2(memberRequest);
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
	
	@PostMapping("/loginMember")
	public MembersResponse loginMember(@RequestBody MemberRequest memberRequest) {
		return membersService.loginMember(memberRequest);
	}
	
	@PostMapping("/checkAccountExist")
	public MembersResponse checkAccountExist(@RequestBody MemberRequest memberRequest) {
		return membersService.checkAccountExist(memberRequest);
	}
	
	@PostMapping("/updatePassword")
	public MembersResponse updatePassword(@RequestBody MemberRequest memberRequest) {
		return membersService.updatePassword(memberRequest);
	}

}
