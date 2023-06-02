package com.example.chain_store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Members;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.service.ifs.MembersService;
import com.example.chain_store.vo.request.MemberRequest;
import com.example.chain_store.vo.response.MembersResponse;

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
	
	@PostMapping("/readMemberPage")
	public Page<Members> readMember(@RequestParam("page") int page, @RequestParam("pageSize")int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
	    return membersDao.findAll(pageable);
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
	public MembersResponse loginMember(@RequestBody MemberRequest memberRequest,HttpSession httpSession) {
		MembersResponse membersResponse = membersService.loginMember(memberRequest);
		if(membersResponse.getMessage().equals("登陸成功")) {
			httpSession.setAttribute("account", memberRequest);
			httpSession.setAttribute("password", memberRequest);
			httpSession.setMaxInactiveInterval(30);
		}
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
	
	
	@PostMapping("/checkcaptcha")
	public MembersResponse checkcaptcha(@RequestBody MemberRequest memberRequest) {
		return membersService.checkcaptcha(memberRequest);
	}
	
	@PostMapping("/active")
	public MembersResponse active(@RequestBody MemberRequest memberRequest) {
		return membersService.active(memberRequest);
	}
	
	@PostMapping("/stopMember")
	public MembersResponse stopMember(@RequestBody MemberRequest memberRequest) {
		return membersService.stopMember(memberRequest);
	}

}
