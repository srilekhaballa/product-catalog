package com.shoppingcart.productcatalog;

import static com.shoppingcart.productcatalog.config.MessageStrings.USER_CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.shoppingcart.productcatalog.dto.ResponseDto;
import com.shoppingcart.productcatalog.dto.user.SignupDto;
import com.shoppingcart.productcatalog.dto.user.UserCreateDto;
import com.shoppingcart.productcatalog.model.User;
import com.shoppingcart.productcatalog.repository.UserRepository;
import com.shoppingcart.productcatalog.service.UserService;

public class UserServiceTest extends BaseClass {
	
    private static final Logger logger = LogManager.getLogger(UserServiceTest.class);
    
    @InjectMocks
    UserService userService;
    
    @Mock
    UserRepository userRepository;

    @Test
    @Order(1)
    public void signUpTest() {
    	User user1 = new User("shashidhar rao","peritari","shashidharraoperitari@gmail.com",1,"shashidharraoperitari001");
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
		//Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(true);
		SignupDto user2 = new SignupDto("john","pranoy","john@gmail.com","password",1,1);
		ResponseDto response = userService.signUp(user2);
		System.out.println(response.getMessage());
		assertThat(response.getMessage()).isEqualTo(USER_CREATED);
    }
    
    @Test
    @Order(2)
    public void signInTest() {
    	
    }
    
    @Test
    @Order(3)
    public void createUserTest() {
    	User user1 = new User("shashidhar rao","peritari","shashidharraoperitari@gmail.com",1,"shashidharraoperitari001");
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
//		Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(true);
		UserCreateDto user2 = new UserCreateDto("shashidhar rao","peritari","shashidharraoperitari@gmail.com",1,1,"shashidharraoperitari001");
		ResponseDto response = userService.createUser(user2);
		System.out.println(response.getMessage());
		assertThat(response.getMessage()).isEqualTo(USER_CREATED);
    }
    
    @Test
    @Order(4)
    public void testGetUserById() {
		User response = userService.getUserById(240);
		assertThat(response.getEmail()).isEqualTo("shashidharraoperitari@gmail.com");
    }
}
