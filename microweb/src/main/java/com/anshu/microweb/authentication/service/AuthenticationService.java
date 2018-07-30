package com.anshu.microweb.authentication.service;

import org.springframework.stereotype.Service;

import com.anshu.microweb.authentication.domain.CustomerDTO;

@Service
public interface AuthenticationService {
	
	public CustomerDTO saveCustomer(CustomerDTO  customerDTO);

}
