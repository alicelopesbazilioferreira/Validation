package com.residencia.dell.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;

@Repository
public interface OrderlinesRepository extends JpaRepository <Orderlines, Integer>{
	
	Orderlines findByOrderlinesidAndOrderid(Integer orderlinesid, Orders orders);
	
//	Orderlines findByOrderlinesIdAndOrderIdAndProdId(Integer orderlinesId, Orders orders, Integer prodId);
//	
//	List<Orderlines> findByProdId(Integer prodId);
	
}
