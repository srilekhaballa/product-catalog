package com.shoppingcart.productcatalog;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.repository.CategoryRepository;
import com.shoppingcart.productcatalog.service.CategoryService;

public class CategoryServiceTest {
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	@Test
	public void testListCategories() {
		Category category1 = new Category(236,"Grocery","cooking accessories","sri",new Date(),"sri",new Date());
		Category category2 = new Category(188,"Jewelry","earrings,necklace,bracelet,anklets","sri",new Date(),"sri",new Date());
		Category category3 = new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
		List<Category> responseList = categoryService.listCategories();
		assertThat(responseList).size().isGreaterThan(0);
		assertThat(responseList.get(0)).isNotNull();
		assertThat(responseList.get(0).getCategoryName()).isEqualTo("Grocery");
	}
	
	@Test
	public void testCreateCategory() {
		Category category1 = new Category(236,"Grocery","cooking accessories","sri",new Date(),"sri",new Date());
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category1);
		Mockito.when(categoryRepository.existsByCategoryName(Mockito.anyString())).thenReturn(false);
		Category response = categoryService.createCategory(category1);
		assertThat(response.getCategoryName()).isEqualTo("Grocery");
	}
	
	@Test
	void testUpdateCategory(){
		Category category1 = new Category(236,"Grocery","cooking accessories","sri",new Date(),"sri",new Date());
		category1.setCategoryId(1);
		Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category1);
		Category response = categoryService.updateCategory(1,category1);
		assertThat(response.getCategoryName()).isEqualTo("Grocery");
	}
	
}
