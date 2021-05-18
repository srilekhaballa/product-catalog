package com.shoppingcart.productcatalog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private  CategoryRepository categoryRepository;
	
	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ss:aa")
    Date date = new Date();

	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}

	public void createCategory(Category category) {
		categoryRepository.save(category);
		category.setCreatedBy("sri");
		category.setCreatedOn(date);
		category.setUpdatedBy("sri");
		category.setUpdatedOn(date);
	}

	public Category readCategory(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Integer categoryId) {
		return categoryRepository.findById(categoryId);
	}

	public void updateCategory(Integer categoryID, Category newCategory) {
		Category category = categoryRepository.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setProduct(newCategory.getProduct());
		category.setCreatedBy("sri");
		category.setCreatedOn(date);
		category.setUpdatedBy("sri");
		category.setUpdatedOn(date);
		categoryRepository.save(category);
	}
}
