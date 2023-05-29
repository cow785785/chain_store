package com.example.chain_store.service.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.regex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.chain_store.entity.Members;
import com.example.chain_store.repository.MembersDao;
import com.example.chain_store.service.ifs.MembersService;
import com.example.chain_store.vo.request.MemberRequest;
import com.example.chain_store.vo.response.MembersResponse;

@Service
public class MemberServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;

	@Autowired
	private JavaMailSender mailSender;

	// 電話番号検査
	// 指定された形式に電話番号が一致するかどうかを検証します。
	// 電話番号がnullまたは空の文字列かどうかをチェックし、その場合はfalseを返します。
	// 正規表現"(09-\\d{8})|(886-9\\d{7})"を使用して、電話番号の形式を検証します。
	// 電話番号が数字のみで構成され、"09-xxxxxxxx"または"886-9xxxxxxx"という形式であることを仮定しています（xは数字を表します）。
	// 電話番号が形式の要件を満たしている場合はtrueを返し、そうでなければfalseを返します。
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
		// 少なくとも8桁必要です
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
		// /u4e00"代表中文字符的第一個字符，"/u9fa5"代表中文字符的最後一個字符
		String pattern = "[\\u4e00-\\u9fa5]{2}[市|縣][\\u4e00-\\u9fa5]{2,9}(?:[路|街|巷].*)?";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(address);

		return matcher.matches();
	}

//	// 隨機生成數字
//	String generateRandomNumbers() {
//		Random random = new Random();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < 6; i++) { // 生成6位数字
//			int randomNumber = random.nextInt(10); // 生成0-9之间的随机数字
//			sb.append(randomNumber);
//		}
//		return sb.toString();
//	}

	private int generateRandomNumber() {
		Random random = new Random();
		int randomNumber = 0;
		for (int i = 0; i < 6; i++) { // 生成6位数字
			randomNumber = randomNumber * 10 + random.nextInt(10); // 生成0-9之间的随机数字，并将其追加到随机数字的末尾
		}
		return randomNumber;
	}

	int randomNumbers = generateRandomNumber();
	String emailContent = "歡迎註冊會員！您的驗證碼為：" + randomNumbers;

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

		if (!isCheckAddressFormat(memberRequest.getAddress())) {
			return new MembersResponse("失敗！地址無效或不符合規範");
		}

		// 檢查帳號是否已經存在
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
				|| memberRequest.getAddress().isEmpty() || memberRequest.getEmail().isEmpty()) {
			return new MembersResponse("失敗！輸入值不能為空");
		}

		// 對密碼進行Base64編碼
		String encodedPassword = Base64.getEncoder()
				.encodeToString(memberRequest.getPassword().getBytes(StandardCharsets.UTF_8));
		String partialPassword = memberRequest.getPassword().substring(0, 4); // 提取前四個字元
		String encryptedPassword = Base64.getEncoder()
				.encodeToString(memberRequest.getPassword().getBytes(StandardCharsets.UTF_8)); // 將剩餘部分進行 Base64 編碼

		String displayedPassword = partialPassword + encryptedPassword.substring(4); // 將前四個字元與剩餘部分加密的密碼結合

		Members members = new Members(memberRequest.getUseraccount(), displayedPassword, memberRequest.getUsername(),
				memberRequest.getBirthDate(), memberRequest.getAddress(), memberRequest.getPhone(),
				memberRequest.getEmail(), randomNumbers, memberRequest.getRegistrationTime());
		
		 members.setActive(true); // 設置帳號為激活狀態

		// 換算時間格式
		long timestampMillis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(timestampMillis);
		members.setRegistrationTime(timestamp);
		// 加入資料庫前先加入換算好的時間

//		// 驗證碼確認
//		if (memberRequest.getCaptcha()!=randomNumbers) {
//			return new MembersResponse("失敗！驗證碼錯誤");
//		}

		Members savedMember = membersDao.save(members);

		// 建立郵件訊息
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		// 設定郵件相關屬性
		try {
			// 設定收件人
			helper.setTo(memberRequest.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			// 標題
			helper.setSubject("註冊成功通知");

		} catch (MessagingException e) {

			e.printStackTrace();
		}
		try {

			// 設定郵件內容
			helper.setText("歡迎註冊會員！" + "\n" + "您的驗證碼 : " + emailContent + "\n以下連結回去登入畫面 : " + "http://localhost:5173/");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 設定郵件內容

		mailSender.send(message);

		return new MembersResponse(memberRequest.getUseraccount(), memberRequest.getPassword(),
				memberRequest.getUsername(), memberRequest.getBirthDate(), memberRequest.getAddress(),
				memberRequest.getPhone(), members.getPoint(), memberRequest.getEmail(), "註冊成功");
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
		return new MembersResponse(members.getUseraccount(), members.getPassword(), members.getUsername(),
				members.getBirthDate(), members.getAddress(), members.getPhone(), "OK");
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
		if (!checkBirthDay(memberRequest.getBirthDate())) {
			return new MembersResponse(memberRequest.getPhone(), "失敗！生日格式無效");
		}

		// 檢查電話
		if (!checkPhoneNumber(memberRequest.getPhone())) {
			return new MembersResponse(memberRequest.getPhone(), "失敗！電話號碼格式無效");
		}

		if (!containsUppercase(memberRequest.getPassword())) {
			return new MembersResponse("密碼必須包含一個大寫字母");
		}

		if (!isCheckAddressFormat(memberRequest.getAddress())) {
			return new MembersResponse("失敗！地址無效或不符合規範");
		}

		// 檢查帳號是否已經存在
		Optional<Members> optionMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionMember.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(), "失敗！帳號不存在");
		}

		if (members != null) {
			// 更新會員資料
			members.setUseraccount(memberRequest.getUseraccount());
			members.setPassword(memberRequest.getPassword());
			members.setUsername(memberRequest.getUsername());
			members.setBirthDate(memberRequest.getBirthDate());
			members.setAddress(memberRequest.getAddress());
			members.setPhone(memberRequest.getPhone());
			members.setEmail(memberRequest.getEmail());
		} else {
			return new MembersResponse(memberRequest.getUseraccount(), "找不到該會員，更新失敗");
		}

		Members updatedMember = membersDao.save(members);
		return new MembersResponse(updatedMember.getUseraccount(), updatedMember.getPassword(),
				updatedMember.getUsername(), updatedMember.getBirthDate(), updatedMember.getAddress(),
				updatedMember.getPhone(), updatedMember.getEmail(), "更新成功");
	}

	@Override
	public MembersResponse deleteMember(MemberRequest memberRequest) {
		if (memberRequest.getUseraccount() == null) {
			return new MembersResponse("帳號不得為空");
		}

		// 檢查帳號是否存在
		Optional<Members> optionalStudent = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalStudent.isPresent()) {
			return new MembersResponse(memberRequest.getUseraccount(), "帳號不存在");
		}
		// 刪除帳號
		Members members = optionalStudent.get();
		membersDao.delete(members);

		return new MembersResponse(memberRequest.getUseraccount(), "刪除成功");
	}

	// 登錄用API
	@Override
	public MembersResponse loginMember(MemberRequest memberRequest) {
		Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!optionalMember.isPresent()) {
			return new MembersResponse("帳號密碼驗證失敗");
		}

		Members member = optionalMember.get();
		 if (!member.getActive()) {
		        return new MembersResponse("帳號已被停用");
		    }
		String storedPassword = member.getPassword();
		String partialPassword = storedPassword.substring(0, 4); // 提取前四個字元
		String encryptedPassword = partialPassword.substring(4); // 提取加密後的剩餘部分

		String decodedPassword = new String(Base64.getDecoder().decode(encryptedPassword), StandardCharsets.UTF_8);

		String originalPassword = partialPassword + decodedPassword; // 將前四個字元與解碼後的密碼結合
		if (!memberRequest.getPassword().equals(originalPassword)) {
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

			return new MembersResponse(member.getUseraccount(), member.getPassword(), "密碼已更新");
		} else {
			return new MembersResponse(memberRequest.getUseraccount(), "帳號不存在");
		}
	}

	@Override
	public MembersResponse checkcaptcha(MemberRequest memberRequest) {
		Optional<Members> optionalMember = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (optionalMember.isPresent()) {
			if (optionalMember.get().getCaptcha() == memberRequest.getCaptcha()) {
				return new MembersResponse("驗證成功");
			}

		}
		return new MembersResponse("驗證碼錯誤");
	}

	@Override
	public MembersResponse active(MemberRequest memberRequest) {
		Optional<Members> members = membersDao.findByUseraccount(memberRequest.getUseraccount());
		if (!members.isPresent()) {
			return new MembersResponse("帳號密碼驗證失敗");
		}
		if (!StringUtils.hasText(memberRequest.getUseraccount()) || !StringUtils.hasText(memberRequest.getPassword())) {
			return new MembersResponse("不能是空的");
		}
	
		Members member = members.get();
		String storedPassword = member.getPassword();
		String partialPassword = storedPassword.substring(0, 4); // 提取前四個字元
		String encryptedPassword = storedPassword.substring(4); // 提取加密後的剩餘部分

		String decodedPassword = new String(Base64.getDecoder().decode(encryptedPassword), StandardCharsets.UTF_8);

		String originalPassword = partialPassword + decodedPassword; // 將前四個字元與解碼後的密碼結合
		
		
		if (memberRequest.getPassword().equals(originalPassword)) {
			return new MembersResponse("密碼驗證失敗");
		}
		

		if (member.getActive()) {
			return new MembersResponse("已登入");

		}
		member.setActive(true);
		try {
			membersDao.save(member);
		} catch (Exception e) {
			return new MembersResponse("失敗");
		}

		return new MembersResponse("成功");
	}

	@Override
	public MembersResponse stopMember(MemberRequest memberRequest) {
	    MembersResponse response = new MembersResponse();
	    try {
	        // 从memberRequest中获取需要停用的会员信息
	        String userAccount = memberRequest.getUseraccount();
	        
	        // 根据会员账号执行停用会员的逻辑
	        Optional<Members> memberOptional = membersDao.findByUseraccount(userAccount);
	        if (memberOptional.isPresent()) {
	            Members member = memberOptional.get();
	            member.setActive(false);
	            membersDao.save(member);
	            
	            //停用會員訊息
	            response.setMessage("停用會員成功");

	        } else {
	            response.setMessage("找不到該會員帳號");

	        }
	    } catch (Exception e) {
	        // 发生异常时设置错误信息
	        response.setMessage("停用会员失败: " + e.getMessage());

	    }
	    
	    return new MembersResponse("已停用");
	}


}
