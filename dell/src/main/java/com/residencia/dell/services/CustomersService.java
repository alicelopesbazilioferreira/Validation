package com.residencia.dell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.repositories.CustomersRepository;

@Service
public class CustomersService {
	
	@Autowired
	public CustomersRepository customersRepository;

}
