package com.anshu.microweb.authentication.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonRootName;

@Component
@JsonRootName("status")
public class ResponseDTO {
	
	private String id="388383-73636-663636";
	private String code = "success";
	private String message="Customer is created Sucessfully. Default Password generated!";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
