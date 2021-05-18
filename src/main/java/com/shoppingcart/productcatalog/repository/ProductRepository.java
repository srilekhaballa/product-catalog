package com.shoppingcart.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.productcatalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

