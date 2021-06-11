package com.residencia.dell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.ProductsRepository;

@Service
public class ProductsService {
	
	@Autowired
	public ProductsRepository productsRepository;
	
	public Products findById(Integer id){
		return productsRepository.findById(id).get() ;
		
		//findById(id).get()
	}
	
	public List<Products> findAll(){
		return productsRepository.findAll();
	}
	
	public Long count() {
		return productsRepository.count();
	}
	
	public Products save(Products products){
		Products newProducts = productsRepository.save(products);
		return  newProducts;
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  productsRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	    }
	
	public Products update ( Products products) {
		return productsRepository.save(products);
	}

}
