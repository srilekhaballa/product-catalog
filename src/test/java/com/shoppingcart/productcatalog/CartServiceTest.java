package com.shoppingcart.productcatalog;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.shoppingcart.productcatalog.dto.cart.AddCartDto;
import com.shoppingcart.productcatalog.model.Cart;
import com.shoppingcart.productcatalog.repository.CartRepository;
import com.shoppingcart.productcatalog.service.CartService;

public class CartServiceTest {
	
	@Autowired
    CartService cartService;
    
    @Autowired
    CartRepository cartRepository;
    
    @Test
    public void testAddTocart() {
    		Cart cart1 = new Cart(232,4,1,230);
    		Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(cart1);
    		Mockito.when(cartRepository.existsById(Mockito.anyInt())).thenReturn(false);
    		AddCartDto addCart= new AddCartDto(cart1.getProductId(),cart1.getQuantity(),cart1.getUserId(),cart1.getCartId());
    		Cart response = cartService.addToCart(addCart);
    		assertThat(response.getCartId()).isEqualTo(232);
        }
    }
    

