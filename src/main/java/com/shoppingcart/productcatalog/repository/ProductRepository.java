package com.shoppingcart.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoppingcart.productcatalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query(countByCategoryId)
	Integer countByCategoryId(Long category_id);

	final String countByCategoryId= "SELECT COUNT(p) FROM Product p WHERE p.category_id=?1";
}