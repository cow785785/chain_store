package com.example.chain_store.service.ifs;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.chain_store.entity.Members;
import com.example.chain_store.vo.request.MemberRequest;
import com.example.chain_store.vo.response.MembersResponse;

public interface MembersService {
	public MembersResponse addMember(MemberRequest memberRequest);//新增會員
	
	public List<Members> readMember();//查詢全部會員
	
	public MembersResponse readMember2(MemberRequest memberRequest);//查詢全部會員2
	
	public MembersResponse selectMember(MemberRequest memberRequest);//透過帳號查詢會員
	
	public MembersResponse updateMember(MemberRequest memberRequest);//更新會員
	
	public MembersResponse deleteMember(MemberRequest memberRequest);//刪除會員
	
	public MembersResponse loginMember(MemberRequest memberRequest);//登入會員
	
	public MembersResponse checkAccountExist(MemberRequest memberRequest);//檢查帳號
	
	public MembersResponse updatePassword(MemberRequest memberRequest);//修改密碼
	
	public MembersResponse checkcaptcha(MemberRequest memberRequest);//確認驗證碼
	
	public MembersResponse active(MemberRequest memberRequest);//登入狀態
	
	public MembersResponse stopMember(MemberRequest memberRequest);//停用帳號
	
	public Page<Members> readMember(int page, int pageSize);//分頁方法
	


}
