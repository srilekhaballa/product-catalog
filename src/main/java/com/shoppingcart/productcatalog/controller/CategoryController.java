package com.shoppingcart.productcatalog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingcart.productcatalog.common.ApiResponse;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.service.CategoryService;
import com.shoppingcart.productcatalog.utils.Helper;

@RestController
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/get")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createCategory(@Validated @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PutMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Validated @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
}

