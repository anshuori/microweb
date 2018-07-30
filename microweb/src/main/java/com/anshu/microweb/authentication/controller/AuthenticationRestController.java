package com.anshu.microweb.authentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anshu.microweb.authentication.domain.CustomerDTO;
//webservice endpoint class
import com.anshu.microweb.authentication.service.AuthenticationService;
@RestController
@RequestMapping("/sso")
public class AuthenticationRestController {


	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(path="/user",method=RequestMethod.POST,
			headers={"Content-Type=application/json",
					"X-OpenIDM-Username=admin","X-OpenIDM-Password=admin"})
	public CustomerDTO createUser(@Valid @RequestBody CustomerDTO customerDTO){
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX........................"+customerDTO);
		//call to service class
		customerDTO=authenticationService.saveCustomer(customerDTO);
		
		return customerDTO ;		
	}



}
