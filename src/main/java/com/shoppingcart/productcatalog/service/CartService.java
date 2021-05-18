package com.shoppingcart.productcatalog.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.productcatalog.dto.cart.AddCartDto;
import com.shoppingcart.productcatalog.model.Cart;
import com.shoppingcart.productcatalog.repository.CartRepository;

@Service
@Transactional
public class CartService {
	
	@Autowired
    private  CartRepository cartRepository;
	
	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ss:aa")
    Date date = new Date();

    public CartService(){
    	
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddCartDto addToCartDto) {
        Cart cart = new Cart(addToCartDto);
        cart.setCreatedBy("sri");
        cart.setCreatedOn(date);
        cart.setUpdatedBy("sri");
        cart.setUpdatedOn(date);
        cartRepository.save(cart);
    }
}
