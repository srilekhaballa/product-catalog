package com.shoppingcart.productcatalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.productcatalog.controller.CategoryController;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.repository.CategoryRepository;
import com.shoppingcart.productcatalog.service.CategoryService;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@MockBean
	private CategoryService categoryService;
		
	@Test
	void testGetCategories() throws Exception {
		Category category1 = new Category(236,"Grocery","cooking accessories","sri",new Date(),"sri",new Date());
		Category category2 = new Category(188,"Jewelry","earrings,necklace,bracelet,anklets","sri",new Date(),"sri",new Date());
		Category category3 = new Category(187,"Clothing","womens clothes,mens clothes,kids wear","sri",new Date(),"sri",new Date());
		
		List<Category> list = new ArrayList<Category>();
		
		list.add(category1);
		list.add(category2);
		list.add(category3);
		
		Mockito.when(categoryService.listCategories()).thenReturn(list);		
		mockMvc.perform(get("/api/category"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].categoryName").value("Grocery"))
		.andExpect(jsonPath("$[1].categoryName").value("Jewelry"))
		.andExpect(jsonPath("$[2].categoryName").value("Clothing"));
	 }
	
	@Test
	void testCreateCategory() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Category category1 = new Category(236,"Grocery","cooking accessories","sri",new Date(),"sri",new Date());
		Mockito.when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(category1);
		String url = "/category";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(category1)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.categoryName").value("Grocery"))
				.andExpect(jsonPath("$.description").value("cooking accessories"))
				.andExpect(jsonPath("$.createdBy").value("sri"));
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
