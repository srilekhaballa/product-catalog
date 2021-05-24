package com.shoppingcart.productcatalog;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.shoppingcart.productcatalog.dto.product.ProductDto;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.model.Product;
import com.shoppingcart.productcatalog.repository.ProductRepository;
import com.shoppingcart.productcatalog.service.ProductService;

public class ProductServiceTest {
	
	@Autowired
    ProductService productService;
    
    @Autowired
    ProductRepository productRepository;
    
    @Test
    @Order(1)
    public void testListProducts() {
    	Category category1= new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
    	Category category2= new Category(2,"Electronic goods","Mobiles,Electric trimmer,TV","sri",new Date(),"sri",new Date());
    	Product product1 = new Product(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),category1);
    	Product product2 = new Product(2,"realme xt","smart phone with 64MP camera","sri",new Date(),"sri",new Date(),category2);
    	List<Product> productList = new ArrayList<Product>();
		productList.add(product1);
		productList.add(product2);
		Mockito.when(productRepository.findAll()).thenReturn(productList);
		List<ProductDto> responseList = productService.listProducts();
		assertThat(responseList).size().isGreaterThan(0);
		assertThat(responseList.get(0)).isNotNull();
		assertThat(responseList.get(0).getProductName()).isEqualTo("realme xt");
    }
    
    @Test
    @Order(2)
    public void testAddProduct() {
    	ProductDto productDto=new ProductDto(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),187);
    	Category category= new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
    	Product product = new Product(productDto, category);
    	Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
		Product response = productService.addProduct(productDto,category);
		assertThat(response.getProductName()).isEqualTo("womens high waist distress jean");
    }
    
    @Test
    @Order(3)
    public void testUpdateProduct() {
    	ProductDto productDto=new ProductDto(233,"womens high waist distress jean","high raise ripped jeans for women","sri",new Date(),"sri",new Date(),187);
    	Category category= new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
    	Product product = new Product(productDto, category);
		product.setProductId(3);
		Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		Product response = productService.updateProduct(3,productDto,category);
		assertThat(response.getProductId()).isEqualTo(3);
    }
}
