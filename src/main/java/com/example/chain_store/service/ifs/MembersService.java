package com.example.chain_store.service.ifs;

import java.util.List;

import com.example.chain_store.entity.Members;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

public interface MembersService {
	public MembersResponse addMember(MemberRequest memberRequest);//�s�W�|��
	
	public List<Members> readMember();//�d�߷|��
	
	public MembersResponse readMember2(MemberRequest memberRequest);//�d�߷|��
	
	public MembersResponse selectMember(MemberRequest memberRequest);//�αb����ID�d�߷|��
	
	public MembersResponse updateMember(MemberRequest memberRequest);//��s�|��
	
	public MembersResponse deleteMember(MemberRequest memberRequest);//�R���|��
	
	public MembersResponse loginMember(MemberRequest memberRequest);//�n���|��
	
	public MembersResponse checkAccountExist(MemberRequest memberRequest);
	


}
