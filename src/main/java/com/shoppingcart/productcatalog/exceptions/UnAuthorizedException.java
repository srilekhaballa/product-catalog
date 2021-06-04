package com.shoppingcart.productcatalog.exceptions;

@SuppressWarnings("serial")
public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String msg){
        super(msg);
    }
}
