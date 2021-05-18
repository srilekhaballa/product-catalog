package com.shoppingcart.productcatalog.exceptions;

public class CustomException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -683127626108241480L;

	public CustomException(String msg) {
        super(msg);
    }
}
