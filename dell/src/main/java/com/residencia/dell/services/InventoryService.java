package com.residencia.dell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.dell.repositories.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	public InventoryRepository inventoryRepository;
 
}
