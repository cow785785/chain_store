package com.example.chain_store.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.chain_store.entity.Members;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.service.ifs.MembersService;
import com.example.chain_store.vo.MemberRequest;
import com.example.chain_store.vo.MembersResponse;

@Service
public class MemberServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;

	// �閰梁�璊
	// ������耦撘�閰梁��������������釆������
	// �閰梁���ull���蝛箝�������������������false��������
	// 甇�閬”�"(09-\\d{8})|(886-9\\d{7})"��蝙����閰梁��敶Ｗ���釆������
	// �閰梁���摮��瑽�����"09-xxxxxxxx"���"886-9xxxxxxx"����耦撘������赫摰��������摮�”�������
	// �閰梁���耦撘閬辣�����������true�����������false��������
	public boolean checkPhoneNumber(String phoneNumber) {
		// �閰梁���征������Ⅱ隤�����(瑼Ｘ�閰梯�Ⅳ�����征��)
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}

		// �閰梁��敶Ｗ���辣����������迤閬”���蝙���璊釆�����(雿輻甇���”�����閰梯�Ⅳ��撘�蝚血����)
		// �閰梁���摮��瑽��������10獢���隞桀������(�ㄐ��身�閰梯�Ⅳ����摮�摨衣10雿�)
		String pattern = "(09-\\d{8})|(886-9\\d{7})";
		return phoneNumber.matches(pattern);
	}

	// 隤�璊(��蝭����)
	public boolean checkBirthDay(LocalDate birthDate) {
	    if (birthDate == null) {
	        return false;
	    }

	    LocalDate currentDate = LocalDate.now();

	    // ��敹�������
	    if (birthDate.isAfter(currentDate)) {
	        return false;
	    }

	    // 瑼Ｘ���撘�蝚血����
	    String pattern = "\\d{4}-\\d{2}-\\d{2}";
	    String birthDateString = birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    if (!birthDateString.matches(pattern)) {
	        return false;
	    }
	    // �隞�璇辣��炎�嚗���撠��憭批僑朣�
	    LocalDate maximumValidDate = currentDate.minusYears(150); // ��身��憭批僑朣∠150甇�
	    LocalDate minimumValidDate = currentDate.minusYears(0); // ��挽��撠僑朣∠0甇�

	    if (birthDate.isBefore(maximumValidDate) || birthDate.isAfter(minimumValidDate)) {
	        return false;
	    }

	    return true;
	}


	// ������憭扳������������������辣��������(閬��Ⅳ敹��之撖怠���)
	private boolean containsUppercase(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public MembersResponse addMember(MemberRequest memberRequest) {
		// 瑼Ｘ�閰梯�Ⅳ������
		if (!checkPhoneNumber(memberRequest.getPhone())) {
			return new MembersResponse("憭望��!�閰梯�Ⅳ�撘���");
		}
		if (memberRequest.getPhone() == null || memberRequest.getPhone().isEmpty()) {
			return new MembersResponse("憭望��閰梯�Ⅳ銝�蝛�");
		}

		// 瑼Ｘ��������
		if (!this.checkBirthDay(memberRequest.getBirthDate())) {
			return new MembersResponse("憭望��������蝭��");
		}
		if (memberRequest.getBirthDate() == null) {
			return new MembersResponse("憭望���銝�蝛�");
		}

//		 瑼Ｘ撣唾��撌脩��
		Optional<Members> optionMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (optionMember.isPresent()) {
			return new MembersResponse("憭望��董��歇摮");
		}

		if (!containsUppercase(memberRequest.getPassword())) {
			return new MembersResponse("撖Ⅳ敹��銝��之撖怠���");
		}

		if (memberRequest.getPhone() == null || memberRequest.getPhone().isEmpty()
				|| memberRequest.getBirthDate() == null || memberRequest.getUseraccount() == null
				|| memberRequest.getUseraccount().isEmpty() || memberRequest.getPassword() == null
				|| memberRequest.getPassword().isEmpty() || memberRequest.getUsername() == null
				|| memberRequest.getUsername().isEmpty() || memberRequest.getAddress() == null
				|| memberRequest.getAddress().isEmpty()) {
			return new MembersResponse("憭望��撓��潔��蝛�");
		}

		Members members = new Members(memberRequest.getUseraccount(), memberRequest.getPassword(),
				memberRequest.getUsername(), memberRequest.getBirthDate(), memberRequest.getAddress(),
				memberRequest.getPhone(), memberRequest.getRegistrationTime());

		// �����撘�
		long timestampMillis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(timestampMillis);
		// ��鞈�澈�������末�����
		Members savedMember = membersDao.save(members);
		return new MembersResponse(memberRequest.getUseraccount(), memberRequest.getPassword(),
				memberRequest.getUsername(), memberRequest.getBirthDate(), memberRequest.getAddress(),
				memberRequest.getPhone(), "閮餃����");
	}

	@Override
	public List<Members> readMember() {
		return membersDao.findAll();
	}

	@Override
	public MembersResponse readMember2(MemberRequest memberRequest) {
		List<Members> members = membersDao.findAll();
		return new MembersResponse(members, "�閰Ｘ���");
	}

	@Override
	public MembersResponse selectMember(MemberRequest memberRequest) {
		if (!StringUtils.hasText(memberRequest.getUseraccount())) {
			return new MembersResponse("憭望��!撣唾���蝛�");
		}

		Optional<Members> optional = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optional.isPresent()) {
			return new MembersResponse("閬�!���董������撣唾�隤�");
		}
		Members members = optional.get();
		return new MembersResponse(members.getUseraccount(),members.getPassword(),members.getUsername(),members.getBirthDate(),members.getAddress(),members.getPhone());
	}

	@Override
	public MembersResponse updateMember(MemberRequest memberRequest) {
		Members members = membersDao.findByUseraccount(memberRequest.getUseraccount()).orElse(null);
		// 瑼Ｘ敹‵甈��
		if (!StringUtils.hasText(memberRequest.getUseraccount()) || !StringUtils.hasText(memberRequest.getPassword())) {
			return new MembersResponse("憭望��董����Ⅳ�敹‵甈��");
		}

		// 瑼Ｘ���撘�
		if (memberRequest.getBirthDate() == null) {
			return new MembersResponse("憭望���甈�敹‵甈��");
		}
		if(!checkBirthDay(memberRequest.getBirthDate())) {
			return new MembersResponse(memberRequest.getPhone(),"憭望����撘���");
		}

		// 瑼Ｘ�閰�
		if (!checkPhoneNumber(memberRequest.getPhone())) {
			return new MembersResponse(memberRequest.getPhone(),"憭望��閰梯�Ⅳ�撘���");
		}

		// 瑼Ｘ撣唾��撌脩��
		Optional<Members> optionMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionMember.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(),"憭望��董����");
		}

		if (members != null) {
			// ����鞈��
			members.setUseraccount(memberRequest.getUseraccount());
			members.setPassword(memberRequest.getPassword());
			members.setUsername(memberRequest.getUsername());
			members.setBirthDate(memberRequest.getBirthDate());
			members.setAddress(memberRequest.getAddress());
			members.setPhone(memberRequest.getPhone());
		} else {
			return new MembersResponse(memberRequest.getUseraccount(),"�銝閰脫�嚗�憭望��");
		}

		Members updatedMember = membersDao.save(members);
		return new MembersResponse(updatedMember.getUseraccount(), updatedMember.getPassword(),
				updatedMember.getUsername(), updatedMember.getBirthDate(), updatedMember.getAddress(),
				updatedMember.getPhone(), "������");
	}

	@Override
	public MembersResponse deleteMember(MemberRequest memberRequest) {
		if (memberRequest.getUseraccount() == null) {
			return new MembersResponse("撣唾���蝛�");
		}

		// 瑼Ｘ撣唾��摮
		Optional<Members> optionalStudent = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalStudent.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(),"撣唾���");
		}
		// ��撣唾��
		Members members = optionalStudent.get();
		membersDao.delete(members);

		return new MembersResponse(memberRequest.getUseraccount(),"������");
	}

	@Override
	public MembersResponse loginMember(MemberRequest memberRequest) {
		Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalMember.isPresent()) {
			return new MembersResponse("撣唾��Ⅳ撽�仃���");
		}
		Members member = optionalMember.get();
		if (!member.getPassword().equals(memberRequest.getPassword())) {
			return new MembersResponse("撣唾��Ⅳ撽�仃���");
		}

		return new MembersResponse(member.getUsername(), "������");
	}

}
