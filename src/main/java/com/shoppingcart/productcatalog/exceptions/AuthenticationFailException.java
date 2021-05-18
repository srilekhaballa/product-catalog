package com.shoppingcart.productcatalog.exceptions;

public class AuthenticationFailException extends IllegalArgumentException  {
	   
		/**
	 * 
	 */
	private static final long serialVersionUID = 3710944503503759213L;

		public AuthenticationFailException(String msg) {
	        super(msg);
	    }
	}


