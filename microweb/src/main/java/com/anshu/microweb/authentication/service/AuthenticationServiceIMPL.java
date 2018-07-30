package com.anshu.microweb.authentication.service;

import java.security.SecureRandom;
import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.anshu.microweb.authentication.domain.Credentials;
import com.anshu.microweb.authentication.domain.CurrencyDTO;
import com.anshu.microweb.authentication.domain.Customer;
import com.anshu.microweb.authentication.domain.CustomerDTO;
import com.anshu.microweb.authentication.repository.CustomerRepository;


@Service
@Transactional(value=TxType.REQUIRED)
public class AuthenticationServiceIMPL implements AuthenticationService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	@Qualifier("mvcConversionService")
	private ConversionService conversionService;
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDTO saveCustomer(CustomerDTO  customerDTO){		
		//DTO to Enity Object Conversion
			
		Customer customerEnity=	mapper.map(customerDTO, Customer.class);	
		customerEnity.setLoginTime(new Date());
		Credentials credentials = customerEnity.getCredentials();
		credentials.setPassword(String.valueOf(new SecureRandom().nextInt()));
		
		credentials.setCustomerRef(customerEnity);
		customerEnity.setCredentials(credentials);
					
		customerRepository.save(customerEnity);
		
		// START parctice purpose -uses of convertor 
		Integer value=conversionService.convert("1000",Integer.class );		
		System.out.println("value....................."+value);
		
		CurrencyDTO currencyDTO=conversionService.convert("35$", CurrencyDTO.class);
		
		System.out.println("convertor....................."+currencyDTO);
		
		
		//END Practice
		
		return customerDTO;
	}
	
	
	
	

}
