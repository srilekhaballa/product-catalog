package com.shoppingcart.productcatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.productcatalog.common.ApiResponse;
import com.shoppingcart.productcatalog.dto.cart.AddCartDto;
import com.shoppingcart.productcatalog.exceptions.AuthenticationFailException;
import com.shoppingcart.productcatalog.exceptions.ProductNotExistException;
import com.shoppingcart.productcatalog.exceptions.UserNotExistException;
import com.shoppingcart.productcatalog.model.Product;
import com.shoppingcart.productcatalog.model.User;
import com.shoppingcart.productcatalog.service.AuthenticationService;
import com.shoppingcart.productcatalog.service.CartService;
import com.shoppingcart.productcatalog.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
    private AuthenticationService authenticationService;
	
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartDto addCartDto,@RequestParam("token") String token)
			throws UserNotExistException, ProductNotExistException,AuthenticationFailException {
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		Product product = productService.getProductById(addCartDto.getProductId());
		if (product == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product is invalid"), HttpStatus.CONFLICT);
		}
		logger.info("product to add", product.getProductName());
		cartService.addToCart(addCartDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the cart"), HttpStatus.OK);
	}

}
