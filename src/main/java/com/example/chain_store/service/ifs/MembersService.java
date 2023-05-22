package com.example.chain_store.service.ifs;

import java.util.List;

import com.example.chain_store.entity.Members;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

public interface MembersService {
	public MembersResponse addMember(MemberRequest memberRequest);//ï¿½sï¿½Wï¿½|ï¿½ï¿½
	
	public List<Members> readMember();//ï¿½dï¿½ß·|ï¿½ï¿½
	
	public MembersResponse readMember2(MemberRequest memberRequest);//¬d¸ß·|­û
	
	public MembersResponse selectMember(MemberRequest memberRequest);//ï¿½Î±bï¿½ï¿½ï¿½ï¿½IDï¿½dï¿½ß·|ï¿½ï¿½
	
	public MembersResponse updateMember(MemberRequest memberRequest);//ï¿½ï¿½sï¿½|ï¿½ï¿½
	
	public MembersResponse deleteMember(MemberRequest memberRequest);//§R°£·|­û
	
	public MembersResponse loginMember(MemberRequest memberRequest);//µn¿ý·|­û

}
