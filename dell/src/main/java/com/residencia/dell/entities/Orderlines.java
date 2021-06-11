package com.residencia.dell.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="orderlines")
public class Orderlines {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "orderlineid")
	private Integer orderlinesid;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "orderid",  referencedColumnName ="orderid")
	private Orders orderid;
	
	@Column(name = "prod_id")
	private Integer  prodId;
	
	@Column(name = "orderdate")
	private Calendar orderdate;
	
	@Column(name = "quantity")
    private Integer quantity;
	
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderlinesid() {
		return orderlinesid;
	}

	public void setOrderlinesid(Integer orderlineid) {
		this.orderlinesid = orderlineid;
	}


	public Orders getOrderid() {
		return orderid;
	}

	public void setOrderid(Orders orderid) {
		this.orderid = orderid;
	}

	public Calendar getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Calendar orderdate) {
		this.orderdate = orderdate;
	}
	
	
	
	
	
}
