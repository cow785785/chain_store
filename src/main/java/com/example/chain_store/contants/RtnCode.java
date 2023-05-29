package com.example.chain_store.contants;

public enum RtnCode {
	SSCCESSFUL("200","成功"),
	CANNOT_EMPTY("400","失敗"),
	NOT_FOUNT("404","找不到"),
	ACCOUNT_EXISTED("400","已存在"),
	FAILED("400","失敗"),
	ACCOUNT_ACTIVE("400","account"),
	ACCOUNT_NOT_ACTIVE("400","沒有登錄"),
	PLEAZE_LOGIN("400","請登入");

	private String code;

	private String message;
	

	private RtnCode() {
	}

	RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
