package com.anshu.microweb.authentication.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//parent table, no owning 
//persitance Enities should not be exposed in web layer as rest API resource
//Use DTO



@Entity
@Table(name="CUSTOMER_DTL")
public class Customer implements Serializable {	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private Long id;
	
	@Column(name="cust_name")
	private String customerName;
	
	@Column(name="ph_no")
	private Long phoneNo;
	
	@Column(name="lst_login_time")
	private Date loginTime;
	
	@OneToOne(mappedBy="customerRef",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Credentials credentials;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", phoneNo=" + phoneNo + ", loginTime="
				+ loginTime + ", credentials=" + credentials + "]";
	}
	
	
	
	

}
