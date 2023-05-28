package com.example.chain_store.constans;

public enum RtnCode {

	SUCCESSFUL("200", "successful!!"), //
	CANNOT_EMPTY("400", "can't empty!!"), //
	NOT_FOUND("404", "not found!!"), //
	FAILD("400", "faild!"), //
	ACCOUNT_ACTIVED("400", "account actived!!"), //
	ACCOUNT_UNACTIVE("400", "this account is not active!!"), //
	PLEASE_LOGIN("400", "please login!!"), //
	BAD_QUANTITY("400", "Please set  a correct value for the quantity."), //
	BAD_TOTALPRICE("400", "Please set a correct value for the totalprice."),//
	BAD_ORDERINFO("400", "Please set a correct value for the orderinfo.")
	;

	private String code;
	private String message;

	private RtnCode(String code, String message) {
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
