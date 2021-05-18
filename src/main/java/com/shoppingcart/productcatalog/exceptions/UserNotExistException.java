package com.shoppingcart.productcatalog.exceptions;

public class UserNotExistException extends IllegalArgumentException {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = -4494922704703957380L;

		public UserNotExistException(String msg) {
	        super(msg);
	    }
}
