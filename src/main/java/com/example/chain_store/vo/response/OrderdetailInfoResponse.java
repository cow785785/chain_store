package com.example.chain_store.vo.response;

public class OrderdetailInfoResponse {

	private String code;

	private String message;

	public OrderdetailInfoResponse() {
	}

	public OrderdetailInfoResponse(String code, String message) {
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
