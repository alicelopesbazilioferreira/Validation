package com.residencia.dell.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.CustomersRepository;
import com.residencia.dell.repositories.OrdersRepository;
import com.residencia.dell.repositories.ProductsRepository;
import com.residencia.dell.vo.ItemOrderlinesVO;
import com.residencia.dell.vo.NotaFiscalVO;
import com.residencia.dell.vo.OrderlinesVO;
import com.residencia.dell.vo.OrdersVO;

/**
 *
 * @author Dayane
 */
@Service
public class OrdersService {
	
	@Autowired
	public ProductsRepository productsRepository;

	@Autowired
	public OrdersRepository ordersRepository;
	
	@Autowired
	public CustomersRepository customersRepository;

	public Orders findById(Integer id) {
		// return alunosRepository.getById(id).getNome();
		return ordersRepository.findById(id).get();
	}

//	public List<Orders> findAll() {
//		return ordersRepository.findAll();
//	}

//	public List<Orders> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
//		Pageable page = null;
//		List<Orders> listOrders = null;
//		List<Orders> listOrdersComPaginacao = null;
//
//		try {
//			if (null != pagina && null != qtdRegistros) {
//				page = PageRequest.of(pagina, qtdRegistros);
//				listOrdersComPaginacao = ordersRepository.findAll(page).getContent();
//
//				return listOrdersComPaginacao;
//			} else {
//				listOrders = ordersRepository.findAll();
//
//				return listOrders;
//			}
//		} catch (Exception e) {
//			throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
//		}
//	}

	public List<OrdersVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
		Pageable page = null;
		List<Orders> listOrders = null;
		List<Orders> listOrdersComPaginacao = null;
		List<OrdersVO> listOrdersVO = new ArrayList<>();

		try {
			if (null != pagina && null != qtdRegistros) {

				page = PageRequest.of(pagina, qtdRegistros);
				listOrdersComPaginacao = ordersRepository.findAll(page).getContent();

				for (Orders lOrders : listOrdersComPaginacao) {
					listOrdersVO.add(convertEntidadeParaVO(lOrders));
				}

			} else {
				listOrders = ordersRepository.findAll();

				for (Orders lOrders : listOrders) {
					listOrdersVO.add(convertEntidadeParaVO(lOrders));
				}

			}
		} catch (Exception e) {
			throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
		}

		return listOrdersVO;
	}

	private OrdersVO convertEntidadeParaVO(Orders orders) {
		OrdersVO ordersVO = new OrdersVO();
		List<OrderlinesVO> listOrderlinesVO = new ArrayList<>();

		ordersVO.setNetAmount(orders.getNetAmount());
		ordersVO.setOrderDate(orders.getOrderDate());
		ordersVO.setOrderId(orders.getOrderid());
		ordersVO.setTax(orders.getTax());
		ordersVO.setTotalAmount(orders.getTotalAmount());

		for (Orderlines lOrderLines : orders.getListOrderlines()) {
			OrderlinesVO orderlinesVO = new OrderlinesVO();

			orderlinesVO.setOrderDate(lOrderLines.getOrderdate());
			orderlinesVO.setOrderLineId(lOrderLines.getOrderlinesid());
			orderlinesVO.setProdId(lOrderLines.getProdId());
			orderlinesVO.setQuantity(lOrderLines.getQuantity());

			listOrderlinesVO.add(orderlinesVO);
		}

		ordersVO.setListOrderLinesVO(listOrderlinesVO);

		return ordersVO;
	}
	
	private Orders convertVOParaEntidade(OrdersVO ordersVO) {
		Orders orders = new Orders();
		Customers customer = customersRepository.getById(ordersVO.getCustomerid());

//		orders.setOrderId((null == id) ? ordersVO.getOrderId() : id);
		List<Orderlines> listOrderlines = new ArrayList<>();
		
		orders.setOrderid(ordersVO.getOrderId());
		orders.setOrderDate(ordersVO.getOrderDate());
		orders.setNetAmount(ordersVO.getNetAmount());
		orders.setTax(ordersVO.getTax());
		orders.setTotalAmount(ordersVO.getTotalAmount());
		orders.setCustomers(customer);
		
		
		for (OrderlinesVO lOrderLinesVO : ordersVO.getListOrderLinesVO()) {
			Orderlines orderlines = new Orderlines();

			orderlines.setOrderdate(lOrderLinesVO.getOrderDate());
			orderlines.setOrderlinesid(lOrderLinesVO.getOrderLineId());
			orderlines.setProdId(lOrderLinesVO.getProdId());
			orderlines.setQuantity(lOrderLinesVO.getQuantity());

			listOrderlines.add(orderlines);
		}

		orders.setListOrderlines(listOrderlines);
		
		
		return orders;
	}

	public Long count() {
		return ordersRepository.count();
	}

//	public Orders save(Orders orders) {
//		Orders newOrders = ordersRepository.save(orders);
//		return newOrders;
//	}
	
	
	// NOSSO SAVE COM VO QUE FUNCIONAVA
	
//	public OrdersVO save(OrdersVO ordersVO) {
//		Orders newOrders = convertVOParaEntidade(ordersVO, null);
//		ordersRepository.save(newOrders);
//        return convertEntidadeParaVO(newOrders);
//    }
	public Orders save(OrdersVO ordersVO) {
		Orders newOrders = ordersRepository.save(convertVOParaEntidade(ordersVO));
		return newOrders;
	}


	public void delete(Integer id) {
		ordersRepository.deleteById(id);
	}

	public Orders update(Integer id, Orders orders) {
		Orders newOrders = ordersRepository.findById(id).get();
		updateDados(newOrders, orders);
		return ordersRepository.save(newOrders);
	}
	
	public Orders update(Orders orders){
        return ordersRepository.save(orders);
    }

	private void updateDados(Orders newOrders, Orders orders) {
		newOrders.setOrderDate(orders.getOrderDate());
		newOrders.setCustomers(orders.getCustomers());
		newOrders.setNetAmount(orders.getNetAmount());
		newOrders.setTax(orders.getTax());
		newOrders.setTotalAmount(orders.getTotalAmount());
	}
	
	private ItemOrderlinesVO converteOrderlineParaNFPVO(Orderlines orderline) {
		ItemOrderlinesVO nfpVO = new ItemOrderlinesVO();
        Products product = productsRepository.getById(orderline.getProdId());
        		
        nfpVO.setProdId(orderline.getProdId());
        nfpVO.setQuantity(orderline.getQuantity());
        nfpVO.setTitle(product.getTitle());

        return nfpVO;
    }
	
	public NotaFiscalVO emitirNF(Integer ordersId) {
		
		Orders orders = ordersRepository.getById(ordersId);
//		Orders orders = ordersRepository.getById(ordersId);
		List<Orderlines> listOrderlines = orders.getListOrderlines();

		//List<OrderLines> listOrderLinesII = orderLinesRepository.findByOrders(orders);
		
		NotaFiscalVO notaFiscalVO = new NotaFiscalVO();
		
		notaFiscalVO.setCustomerFirstName(orders.getCustomers().getFirstname());
		notaFiscalVO.setCustomerLastName(orders.getCustomers().getLastname());
		notaFiscalVO.setNetAmount(orders.getNetAmount());
		notaFiscalVO.setOrderDate(orders.getOrderDate());
		notaFiscalVO.setOrderId(orders.getOrderid());
		notaFiscalVO.setTotalAmount(orders.getTotalAmount());

		List<ItemOrderlinesVO> listItemOrderLinesVO = new ArrayList<>();
		for(Orderlines orderlines : listOrderlines) {
			ItemOrderlinesVO nfpVO = converteOrderlineParaNFPVO(orderlines);
			
			listItemOrderLinesVO.add(nfpVO);
		}

		notaFiscalVO.setListItemOrderLinesVO(listItemOrderLinesVO);		
		
		return notaFiscalVO;
		
	}
}
