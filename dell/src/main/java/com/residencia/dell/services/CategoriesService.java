package com.residencia.dell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.repositories.CategoriesRepository;

@Service
public class CategoriesService {
	
	@Autowired
	public CategoriesRepository categoriesRepository;
	
	
}
