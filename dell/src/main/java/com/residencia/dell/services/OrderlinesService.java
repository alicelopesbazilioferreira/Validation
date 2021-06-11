package com.residencia.dell.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.repositories.OrderlinesRepository;
import com.residencia.dell.repositories.OrdersRepository;


@Service
public class OrderlinesService {
	
	@Autowired
	public OrderlinesRepository orderlinesRepository;
	
	@Autowired
	public OrdersRepository ordersRepository;
	
	public Orderlines findById(Integer orderlinesId, Integer ordersId) {
		Orders orders = ordersRepository.findById(ordersId).get();
		return orderlinesRepository.findByOrderlinesidAndOrderid(orderlinesId, orders);
	}
	
	public List<Orderlines> findAll(){
		return orderlinesRepository.findAll();
	}
		
	public Orderlines save(Orderlines orderlines) {
		return orderlinesRepository.save(orderlines);
	}
	
	
	public Long count() {
		return orderlinesRepository.count();
	}
	
	public boolean delete(Integer id){
		  if(id != null) {
			  orderlinesRepository.deleteById(id);
			  return true;
		  }
		  else {
			  return false;
		  }
	    }
	

	public Orderlines update ( Orderlines orderlines) {
		return orderlinesRepository.save(orderlines);
	}

	
//	public List<OrderlinesVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
//		Pageable page = null;
//		List<Orderlines> listOrderlines = null;
//		List<Orderlines> listOrderlinesComPaginacao = null;
//		List<OrderlinesVO> listOrderlinesVO = new ArrayList<>();
//
//		try {
//			if (null != pagina && null != qtdRegistros) {
//
//				page = PageRequest.of(pagina, qtdRegistros);
//				listOrderlinesComPaginacao = orderlinesRepository.findAll(page).getContent();
//
//				for (Orderlines lOrders : listOrderlinesComPaginacao) {
//					listOrderlinesVO.add(convertEntidadeParaVO(lOrders));
//				}
//
//			} else {
//				listOrders = orderlinesRepository.findAll();
//
//				for (Orders lOrders : listOrders) {
//					listOrdersVO.add(convertEntidadeParaVO(lOrders));
//				}
//
//			}
//		} catch (Exception e) {
//			throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
//		}
//
//		return listOrderlinesVO;
//	}
//
//	private OrdersVO convertEntidadeParaVO(Orderlines orderlines) {
//		OrderlinesVO orderlinesVO = new OrderlinesVO();
//		List<OrdersVO> listOrdersVO = new ArrayList<>();
//
//		orderlinesVO.setNetAmount(orderlines.getNetAmount());
//		orderlinesVO.setOrderDate(orderlines.getOrderDate());
//		orderlinesVO.setOrderId(orderlines.getOrderId());
//		orderlinesVO.setTax(orderlines.getTax());
//		orderlinesVO.setTotalAmount(orderlines.getTotalAmount());
//
//		for (Orders lOrders : orders.getListOrders()) {
//			OrderlinesVO orderlinesVO = new OrderlinesVO();
//
//			orderlinesVO.setOrderDate(lOrderLines.getOrderdate());
//			orderlinesVO.setOrderLineId(lOrderLines.getOrderlineid());
//			orderlinesVO.setProdId(lOrderLines.getProdId());
//			orderlinesVO.setQuantity(lOrderLines.getQuantity());
//
//			listOrderLinesVO.add(orderlinesVO);
//		}
//
//		ordersVO.setListOrderLinesVO(listOrderLinesVO);
//
//		return ordersVO;
//	}
//
//	public Long countVO() {
//		return orderlinesRepository.count();
//	}
//
//	public Orderlines saveVO(Orderlines orderlines) {
//		Orderlines newOrderlines = orderlinesRepository.save(orderlines);
//		return newOrderlines;
//	}
//
//	public void deleteVO(Integer id) {
//		orderlinesRepository.deleteById(id);
//	}
//
//	public Orderlines updateVO(Integer id, Orderlines orderlines) {
//		Orderlines newOrderlines = orderlinesRepository.findById(id).get();
//		updateDados(newOrderlines, orderlines);
//		return orderlinesRepository.save(newOrderlines);
//	}
//
//	private void updateDados(Orders newOrders, Orders orders) {
//		newOrders.setOrderDate(orders.getOrderDate());
//		newOrders.setCustomers(orders.getCustomers());
//		newOrders.setNetAmount(orders.getNetAmount());
//		newOrders.setTax(orders.getTax());
//		newOrders.setTotalAmount(orders.getTotalAmount());
//	}
	
	

}
