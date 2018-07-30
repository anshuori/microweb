package com.anshu.microweb.authentication.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.anshu.microweb.authentication.util.SunriseJsonComponent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Component
@JsonPropertyOrder({"_id,","UserName","FirstName","LastName","CreateTimestamp","PhoneNumber","lstUpdateTime"})
public class CustomerDTO implements Serializable {	


	private Long   id; 

	@JsonProperty("FirstName")	
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	private String   firstName;

	@JsonProperty("LastName")
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	private String   lastNm;

	@JsonProperty("UserName")
	@NotBlank(message="User Name is manditory")
	private String   userNm;

	@JsonProperty("CreateTimestamp")
	@JsonSerialize(using=SunriseJsonComponent.JsonDateToMillisecondsSerializer.class)
	private Date   createTime;

	@JsonProperty("PhoneNumber")
	@JsonDeserialize(using=SunriseJsonComponent.JsonMillisecondsToDateDeSerializer.class)
	@Digits(integer=100,fraction=100)
	private Long phoneNo;

	@JsonIgnore
	private Date lstUpdateTime;

	@JsonProperty("guid")	
	private String  guid;

	@JsonProperty("currency")
	@JsonDeserialize(using=SunriseJsonComponent.CurrencyJsonDeserializer.class)
	private CurrencyDTO currencyDTO;
	

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNm() {
		return lastNm;
	}

	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getLstUpdateTime() {
		return lstUpdateTime;
	}

	public void setLstUpdateTime(Date lstUpdateTime) {
		this.lstUpdateTime = lstUpdateTime;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", firstName=" + firstName + ", lastNm=" + lastNm + ", userNm=" + userNm
				+ ", createTime=" + createTime + ", phoneNo=" + phoneNo + ", lstUpdateTime=" + lstUpdateTime + ", guid="
				+ guid + "]";
	}


	

}
