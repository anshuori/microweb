package com.anshu.microweb.authentication.domain;

import java.io.Serializable;

//this is for practice 
//34$= Object .. DTO word why as same sample will be uses to expose data through the rest endpoint.. Otherwise VO
public class CurrencyDTO implements Serializable {
	private Double amount ;
	private String countryCode;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		return "CurrencyDTO [amount=" + amount + ", countryCode=" + countryCode + "]";
	}
	
	
	
	
	
}
