package com.shoppingcart.productcatalog.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7350676819459099365L;

	public ProductNotExistException(String msg) {
        super(msg);
    }
}

