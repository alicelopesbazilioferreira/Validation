package com.residencia.dell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.exceptions.OrdersException;
import com.residencia.dell.services.OrderlinesService;
import com.residencia.dell.services.OrdersService;
import com.residencia.dell.vo.NotaFiscalVO;
import com.residencia.dell.vo.OrdersVO;

/**
 *
 * @author Dayane
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderlinesService orderlinesService ;

	@GetMapping("/{id}")
	public ResponseEntity<Orders> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(ordersService.findById(id), headers, HttpStatus.OK);
	}
	
	@GetMapping("/nf/{id}")
    public ResponseEntity<NotaFiscalVO> emitirNF(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ordersService.emitirNF(id), headers, HttpStatus.OK);
    }

//	@GetMapping
//	public ResponseEntity<List<Orders>> findAll(
//			@RequestParam(required = false) Integer pagina,
//			@RequestParam(required = false) Integer qtdRegistros) 
//					throws Exception {
//		
//		HttpHeaders headers = new HttpHeaders();
//		return new ResponseEntity<>(ordersService.findAll(pagina, 
//				qtdRegistros), headers, HttpStatus.OK);
//	}
	
	@GetMapping("/listar-todos")
	public ResponseEntity<List<OrdersVO>> findAllVO(
			@RequestParam(required = false) Integer pagina,
			@RequestParam(required = false) Integer qtdRegistros) 
					throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(ordersService.findAllVO(pagina, 
				qtdRegistros), headers, HttpStatus.OK);
	}

	@GetMapping("/count")
	public Long count() {
		return ordersService.count();
	}

//	@PostMapping
//	public ResponseEntity<Orders> save(@RequestBody Orders order) {
//		HttpHeaders headers = new HttpHeaders();
//		Orders orders = ordersService.save(order);
//		if (null != orders)
//			return ResponseEntity.ok().body(orders);
//		else
//			return new ResponseEntity<>(ordersService.save(orders), headers, HttpStatus.BAD_REQUEST);
//	}
	
	//  NOSSO SAVE QUE FUNCIONAVA
	
//	@PostMapping ("/save")
//	public ResponseEntity<OrdersVO> save(@RequestBody OrdersVO ordersVO){
//		HttpHeaders headers = new HttpHeaders();
//	
//		OrdersVO novoOrdersVO = ordersService.save(ordersVO);
//		
//		if(null != novoOrdersVO)
//			return new ResponseEntity<>(novoOrdersVO, headers, HttpStatus.OK);
//		else
//			return new ResponseEntity<>(novoOrdersVO, headers, HttpStatus.BAD_REQUEST);
//	}
	
	@PostMapping ("/save")
	public Orders save(@RequestBody OrdersVO ordersVO){
	
		Orders novoOrders = ordersService.save(ordersVO);
		
		Integer id = novoOrders.getOrderid();
		
		for (Orderlines odl :ordersService.findById(id).getListOrderlines()) {
			odl.setOrderid(ordersService.findById(id));
			orderlinesService.save(odl);
		}
		
		return ordersService.findById(id);
	}

	@PutMapping("/{id}")
	public Orders update( @PathVariable Integer id, @RequestBody Orders orders) {
		if(orders.getTax() == null) throw new OrdersException("tax nulo!");
		if(orders.getCustomers() == null) throw new OrdersException("pedido sem cliente!");
		if(orders.getListOrderlines() == null) throw new OrdersException("orderlines null, pedido vazio!");
		return (ordersService.update(id, orders));
	}
	
	//@PathVariable Integer id,
	
//	@PutMapping("/update")
//    public Orders update(@RequestBody Orders orders){
//        if (orders.getTax() == null) throw new CustomException("faltou o tax");
//        return ordersService.update(orders);
//    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Orders> delete(@PathVariable Integer id) {
		try {
			ordersService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	
}

//package com.residencia.dell.controllers;
//
//import com.residencia.dell.entities.Orders;
//import com.residencia.dell.services.OrdersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/orders")
//public class OrdersController {
//    @Autowired
//    private OrdersService ordersService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Orders> findById(@PathVariable Integer id){
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(ordersService.findById(id),headers, HttpStatus.OK);
//    }
//
////    @GetMapping
////    public ResponseEntity<List<Orders>> findAll() throws Exception{
////
////        HttpHeaders headers = new HttpHeaders();
////        return new ResponseEntity<>(ordersService.findAll(),headers,HttpStatus.OK);
////    }
//
//    @GetMapping
//    public ResponseEntity<List<Orders>> findAll(@RequestParam (required = false)Integer pagina,
//                                                @RequestParam(required = false)Integer qtdRegistros)
//            throws Exception{
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(ordersService.findAll(pagina,qtdRegistros),headers,HttpStatus.OK);
//    }
//
//    @GetMapping("/count")
//    public Long count() {
//        return ordersService.count();
//    }
//
//
//    @PostMapping("/save")
//    public ResponseEntity<Orders> save(@RequestBody Orders orders){
//        //return alunoService.save(aluno);
//        HttpHeaders headers = new HttpHeaders();
//
//        if(null != ordersService.save(orders))
//            return new ResponseEntity<Orders>(ordersService.save(orders), headers, HttpStatus.OK);
//        else
//            return new ResponseEntity<Orders>(ordersService.save(orders), headers, HttpStatus.BAD_REQUEST);
//    }
//
//    @PutMapping("/update")
//    public Orders update(@RequestBody Orders orders){
//    return ordersService.update(orders);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<Orders> delete(@RequestParam Integer id){
//        HttpHeaders headers = new HttpHeaders();
//        boolean isRemoved = ordersService.delete(id);
//        if (isRemoved){
//            return new ResponseEntity<>(headers,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
//
//        }
//
//    }
//}
