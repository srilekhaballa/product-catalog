package com.shoppingcart.productcatalog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.productcatalog.controller.CartController;
import com.shoppingcart.productcatalog.dto.cart.AddCartDto;
import com.shoppingcart.productcatalog.model.Cart;
import com.shoppingcart.productcatalog.service.CartService;

@WebMvcTest(CartController.class)
public class CartControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CartService cartService;
	
	public void createCartTest() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		AddCartDto addCart= new AddCartDto();
		Cart response = cartService.addToCart(addCart);
		Mockito.when(cartService.addToCart(Mockito.any(AddCartDto.class))).thenReturn(response);
		String url = "/user";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(response)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.productId").value(232))
				.andExpect(jsonPath("$.quantity").value(4))
				.andExpect(jsonPath("$.userId").value(1));
	}
}
