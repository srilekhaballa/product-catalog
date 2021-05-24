package com.shoppingcart.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.productcatalog.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCategoryName(String categoryName);

	public List<Category> findAll();

	Object existsByCategoryName(String anyString);

}
