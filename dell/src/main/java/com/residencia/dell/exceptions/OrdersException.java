package com.residencia.dell.exceptions;

public class OrdersException extends RuntimeException {
	
	private String message;
	
	protected OrdersException() {
	}
	
	 public OrdersException(String message) {
		    this.message = message;
	 }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
