package com.shoppingcart.productcatalog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.productcatalog.dto.product.ProductDto;
import com.shoppingcart.productcatalog.exceptions.ProductNotExistException;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.model.Product;
import com.shoppingcart.productcatalog.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ss:aa")
    static Date date = new Date();
    	
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product) {
    	ProductDto productDto = new ProductDto();
    	productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setCreatedBy("sri");
        productDto.setCreatedOn(date);
        productDto.setUpdatedBy("sri");
        productDto.setUpdatedOn(date);
        productDto.setDescription(product.getDescription());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public Product addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setProductId(productID);
        productRepository.save(product);
        return product;
    }
    
    public Product getProductById(Integer productId) throws ProductNotExistException {
    	Product optionalProduct = productRepository.findById(productId).orElse(null);
        return optionalProduct;
    }
    
    public List<Product> listAll() {
        return productRepository.findAll(Sort.by("productId").ascending());
    }
}
    
