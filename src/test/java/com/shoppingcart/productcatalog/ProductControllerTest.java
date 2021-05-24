package com.shoppingcart.productcatalog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.productcatalog.dto.product.ProductDto;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.model.Product; 
import com.shoppingcart.productcatalog.repository.ProductRepository;
import com.shoppingcart.productcatalog.service.ProductService;

public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private ProductService productService;
	
	@Test
	@Order(1)
	void testGetProducts() throws Exception {
    	ProductDto product1 = new ProductDto(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),187);
    	ProductDto product2 = new ProductDto(2,"realme xt","smart phone with 64MP camera","sri",new Date(),"sri",new Date(),2);
    	List<ProductDto> productList = new ArrayList<ProductDto>();
		productList.add(product1);
		productList.add(product2);
		
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		list.add(product1);
		list.add(product2);
		
		Mockito.when(productService.listProducts()).thenReturn(list);		
		mockMvc.perform(get("/api/product"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].productName").value("womens high waist distress jean"))
		.andExpect(jsonPath("$[1].productName").value("realme xt"));
	 }
	
	@Test
	@Order(2)
	void testAddProduct() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Category category= new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
		ProductDto product1 = new ProductDto(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),187);
		Product product=productService.addProduct(product1, category);
		Mockito.when(productService.addProduct(Mockito.any(ProductDto.class),Mockito.any(Category.class))).thenReturn(product);
		String url = "/product";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(product)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.productName").value("womens high waist distress jean"))
				.andExpect(jsonPath("$.description").value("high raise ripped jeans for women"))
				.andExpect(jsonPath("$.createdBy").value("sri"));
	}
	
	@Test
	@Order(3)
	void testUpdateProduct() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Category category= new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
		ProductDto product1 = new ProductDto(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),187);
		Product product=productService.addProduct(product1, category);
		product.setProductId(3);
		Mockito.when(productService.addProduct(Mockito.any(ProductDto.class),Mockito.any(Category.class))).thenReturn(product);
		String url = "/product";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(product)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.productId").value(3));
	}
	
}
