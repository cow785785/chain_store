package com.example.chain_store.service.ifs;

import java.util.List;

import com.example.chain_store.entity.Members;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

public interface MembersService {
	public MembersResponse addMember(MemberRequest memberRequest);//新增會員
	
	public List<Members> readMember();//查詢會員
	
	public MembersResponse readMember(MemberRequest memberRequest);//查詢會員
	
	public MembersResponse selectMember(MemberRequest memberRequest);//用帳號或ID查詢會員
	
	public MembersResponse updateMember(MemberRequest memberRequest);//更新會員
	
	public MembersResponse deleteMember(MemberRequest memberRequest);//刪除會員
	
	public MembersResponse loginMember(MemberRequest memberRequest);//登錄會員
}
