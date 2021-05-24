package com.shoppingcart.productcatalog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.productcatalog.controller.UserController;
import com.shoppingcart.productcatalog.dto.ResponseDto;
import com.shoppingcart.productcatalog.dto.user.UserCreateDto;
import com.shoppingcart.productcatalog.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	@Order(1)
	public void signUpTest() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserCreateDto user2 = new UserCreateDto("shashidhar rao","peritari","shashidharraoperitari@gmail.com",1,1,"shashidharraoperitari001");
		ResponseDto response = userService.createUser(user2);
		Mockito.when(userService.createUser(Mockito.any(UserCreateDto.class))).thenReturn(response);
		String url = "/user";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(user2)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("shashidhar rao"))
				.andExpect(jsonPath("$.lastName").value("peritari"))
				.andExpect(jsonPath("$.email").value("shashidharraoperitari@gmail.com"));
	}
	
	@Test
	@Order(2)
	public void testGetUserById() throws Exception {
		Mockito.when(userService.getUserById(240));		
		mockMvc.perform(get("/api/category"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName").value("shashidhar rao"));
	}
}