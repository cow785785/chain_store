package com.example.chain_store.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.*;

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

	//電話番号検査
	//指定された形式に電話番号が一致するかどうかを検証します。
	//電話番号がnullまたは空の文字列かどうかをチェックし、その場合はfalseを返します。
	//正規表現"(09-\\d{8})|(886-9\\d{7})"を使用して、電話番号の形式を検証します。
	//電話番号が数字のみで構成され、"09-xxxxxxxx"または"886-9xxxxxxx"という形式であることを仮定しています（xは数字を表します）。
	//電話番号が形式の要件を満たしている場合はtrueを返し、そうでなければfalseを返します。
	public boolean checkPhoneNumber(String phoneNumber) {
		// 電話番号が空でないかを確認します。(檢查電話號碼是否為非空值)
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}

		// 電話番号の形式が要件に適合しているかを正規表現を使用して検証します(使用正則表達式驗證電話號碼的格式是否符合要求)
		// 電話番号が数字のみで構成され、長さが10桁であると仮定します。(這裡假設電話號碼只包含數字，長度為10位)
		String pattern = "(09-\\d{8})|(886-9\\d{7})";
		return phoneNumber.matches(pattern);
	}

	// 誕生日検査(生日範圍驗證)
	public boolean checkBirthDay(LocalDate birthDate) {
	    if (birthDate == null) {
	        return false;
	    }

	    LocalDate currentDate = LocalDate.now();

	 // 生日必須在有效範圍
	    if (birthDate.isAfter(currentDate)) {
	        return false;
	    }

	 // 檢查生日格式是否符合要求
	    String pattern = "\\d{4}-\\d{2}-\\d{2}";
	    String birthDateString = birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    if (!birthDateString.matches(pattern)) {
	        return false;
	    }
	 // 其他生日條件的檢查，例如最小或最大年齡
	    LocalDate maximumValidDate = currentDate.minusYears(150); // 假設最大年齡為150歲
	    LocalDate minimumValidDate = currentDate.minusYears(0); // 假设最小年齡為0歲

	    if (birthDate.isBefore(maximumValidDate) || birthDate.isAfter(minimumValidDate)) {
	        return false;
	    }

	    return true;
	}


	// パスワードに大文字のアルファベットを含むことが要件とされます。(規範密碼必須有大寫字母)
	private boolean containsUppercase(String password) {
		//少なくとも8桁必要です
		if (password.length() < 8) {
	        return false;
	    }
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean isCheckAddressFormat(String address) {
	    if (address == null || address.isEmpty()) {
	        return false;
	    }
	    
	    String pattern = "[\\u4e00-\\u9fa5]{2}[市|縣][\\u4e00-\\u9fa5]{2,9}(?:[路|街|巷].*)?";
	    Pattern regex = Pattern.compile(pattern);
	    Matcher matcher = regex.matcher(address);
	    
	    return matcher.matches();
	}


	@Override
	public MembersResponse addMember(MemberRequest memberRequest) {
		// 檢查電話號碼是否有效
		if (!checkPhoneNumber(memberRequest.getPhone())) {
			return new MembersResponse("失敗!電話號碼格式不對");
		}
		if (memberRequest.getPhone() == null || memberRequest.getPhone().isEmpty()) {
			return new MembersResponse("失敗！電話號碼不能為空");
		}

		// 檢查生日是否有效
		if (!this.checkBirthDay(memberRequest.getBirthDate())) {
			return new MembersResponse("失敗！生日無效或超出範圍");
		}
		if (memberRequest.getBirthDate() == null) {
			return new MembersResponse("失敗！生日不能為空");
		}
		
		if(!isCheckAddressFormat(memberRequest.getAddress())) {
			return new MembersResponse("失敗！地址無效或不符合規範");
		}

		//檢查帳號是否已經存在
		Optional<Members> optionMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (optionMember.isPresent()) {
			return new MembersResponse("失敗！帳號已存在");
		}

		if (!containsUppercase(memberRequest.getPassword())) {
			return new MembersResponse("密碼必須包含一個大寫字母");
		}

		if (memberRequest.getPhone() == null || memberRequest.getPhone().isEmpty()
				|| memberRequest.getBirthDate() == null || memberRequest.getUseraccount() == null
				|| memberRequest.getUseraccount().isEmpty() || memberRequest.getPassword() == null
				|| memberRequest.getPassword().isEmpty() || memberRequest.getUsername() == null
				|| memberRequest.getUsername().isEmpty() || memberRequest.getAddress() == null
				|| memberRequest.getAddress().isEmpty()) {
			return new MembersResponse("失敗！輸入值不能為空");
		}

		Members members = new Members(memberRequest.getUseraccount(), memberRequest.getPassword(),
				memberRequest.getUsername(), memberRequest.getBirthDate(), memberRequest.getAddress(),
				memberRequest.getPhone(), memberRequest.getRegistrationTime());

		// 換算時間格式
		long timestampMillis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(timestampMillis);
		// 加入資料庫前先加入換算好的時間
		Members savedMember = membersDao.save(members);
		return new MembersResponse(memberRequest.getUseraccount(), memberRequest.getPassword(),
				memberRequest.getUsername(), memberRequest.getBirthDate(), memberRequest.getAddress(),
				memberRequest.getPhone(), "註冊成功");
	}

	@Override
	public List<Members> readMember() {
		return membersDao.findAll();
	}

	@Override
	public MembersResponse readMember2(MemberRequest memberRequest) {
		List<Members> members = membersDao.findAll();
		return new MembersResponse(members, "查詢成功");
	}

	@Override
	public MembersResponse selectMember(MemberRequest memberRequest) {
		if (!StringUtils.hasText(memberRequest.getUseraccount())) {
			return new MembersResponse("失敗!帳號不得為空");
		}
		
		String keyword = memberRequest.getUseraccount(); // 關鍵字為使用者輸入的帳號
	    List<Members> matchingMembers = membersDao.findByUseraccountContaining(keyword);

	    if (matchingMembers.isEmpty()) {
	        return new MembersResponse("找不到符合搜尋關鍵字的會員");
	    }

		Optional<Members> optional = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optional.isPresent()) {
			return new MembersResponse("親!您的帳號不存在或是帳號錯誤");
		}
		Members members = optional.get();
		return new MembersResponse(members.getUseraccount(),members.getPassword(),members.getUsername(),members.getBirthDate(),members.getAddress(),members.getPhone(),"OK");
	}

	@Override
	public MembersResponse updateMember(MemberRequest memberRequest) {
		Members members = membersDao.findByUseraccount(memberRequest.getUseraccount()).orElse(null);
		// 檢查必填欄位
		if (!StringUtils.hasText(memberRequest.getUseraccount()) || !StringUtils.hasText(memberRequest.getPassword())) {
			return new MembersResponse("失敗！帳號和密碼為必填欄位");
		}

		// 檢查日期格式
		if (memberRequest.getBirthDate() == null) {
			return new MembersResponse("失敗！生日格式無效");
		}
		if(!checkBirthDay(memberRequest.getBirthDate())) {
			return new MembersResponse(memberRequest.getPhone(),"失敗！生日格式無效");
		}

		// 檢查電話
		if (!checkPhoneNumber(memberRequest.getPhone())) {
			return new MembersResponse(memberRequest.getPhone(),"失敗！電話號碼格式無效");
		}
		
		if (!containsUppercase(memberRequest.getPassword())) {
			return new MembersResponse("密碼必須包含一個大寫字母");
		}
		
		if(!isCheckAddressFormat(memberRequest.getAddress())) {
			return new MembersResponse("失敗！地址無效或不符合規範");
		}

		// 檢查帳號是否已經存在
		Optional<Members> optionMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionMember.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(),"失敗！帳號不存在");
		}

		if (members != null) {
			// 更新會員資料
			members.setUseraccount(memberRequest.getUseraccount());
			members.setPassword(memberRequest.getPassword());
			members.setUsername(memberRequest.getUsername());
			members.setBirthDate(memberRequest.getBirthDate());
			members.setAddress(memberRequest.getAddress());
			members.setPhone(memberRequest.getPhone());
		} else {
			return new MembersResponse(memberRequest.getUseraccount(),"找不到該會員，更新失敗");
		}

		Members updatedMember = membersDao.save(members);
		return new MembersResponse(updatedMember.getUseraccount(), updatedMember.getPassword(),
				updatedMember.getUsername(), updatedMember.getBirthDate(), updatedMember.getAddress(),
				updatedMember.getPhone(), "更新成功");
	}

	@Override
	public MembersResponse deleteMember(MemberRequest memberRequest) {
		if (memberRequest.getUseraccount() == null) {
			return new MembersResponse("帳號不得為空");
		}

		// 檢查帳號是否存在
		Optional<Members> optionalStudent = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalStudent.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(),"帳號不存在");
		}
		// 刪除帳號
		Members members = optionalStudent.get();
		membersDao.delete(members);

		return new MembersResponse(memberRequest.getUseraccount(),"刪除成功");
	}
	
	
	//登錄用API
	@Override
	public MembersResponse loginMember(MemberRequest memberRequest) {
		Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalMember.isPresent()) {
			return new MembersResponse("帳號密碼驗證失敗");
		}
		Members member = optionalMember.get();
		if (!member.getPassword().equals(memberRequest.getPassword())) {
			return new MembersResponse("密碼驗證失敗");
		}

		return new MembersResponse(member.getUsername(), "登錄成功");
	}

	
	@Override
	public MembersResponse checkAccountExist(MemberRequest memberRequest) {
	    if (!StringUtils.hasText(memberRequest.getUseraccount())) {
	        return new MembersResponse("帳號不得為空");
	    }

	    Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
	    if (optionalMember.isPresent()) {
	        return new MembersResponse("帳號已存在");
	    } else {
	        return new MembersResponse("帳號可使用");
	    }
	}

	@Override
	public MembersResponse updatePassword(MemberRequest memberRequest) {
		if (!StringUtils.hasText(memberRequest.getUseraccount())) {
	        return new MembersResponse("帳號不得為空");
	    }
		 Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		    if (optionalMember.isPresent()) {
		        Members member = optionalMember.get();
		        member.setPassword(memberRequest.getPassword()); // 更新密码

		        membersDao.save(member);

		        return new MembersResponse(member.getUseraccount(),member.getPassword(), "密碼已更新");
		    } else {
		        return new MembersResponse(memberRequest.getUseraccount(), "帳號不存在");
		    }
	}
	
	
}
