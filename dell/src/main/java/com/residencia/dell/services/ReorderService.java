package com.residencia.dell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.repositories.ReorderRepository;

@Service
public class ReorderService {
	
	@Autowired
	public ReorderRepository reorderRepository;

}
