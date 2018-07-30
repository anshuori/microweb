package com.anshu.microweb.authentication.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//CUSTOMER1(PARENT)<---->1CREDDENTILS(CHILD) FK
//Child table
//a relationship owner enity 


@Entity
@Table(name="USER_AUTH")
public class Credentials implements Serializable {
	@Column(name="user_auth_id")
	@Id	
	private Long id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_name")
	private String userNm;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@MapsId
    private Customer customerRef;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserNm() {
		return userNm;
	}


	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}


	public Customer getCustomerRef() {
		return customerRef;
	}


	public void setCustomerRef(Customer customerRef) {
		this.customerRef = customerRef;
	}
	
	
	@Override
	public String toString() {
		return "Credentials [id=" + id + ", password=" + password + ", userNm=" + userNm + ", customerRef="
				+ customerRef + "]";
	}

}
