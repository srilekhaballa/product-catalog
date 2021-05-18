package com.shoppingcart.productcatalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.shoppingcart.productcatalog.repository.UserRepository;
import com.shoppingcart.productcatalog.service.UserService;

public class UserServiceTest extends BaseClass {
	
    private static final Logger logger = LogManager.getLogger(UserServiceTest.class);
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    public void signUpTest() {
    	
    }
    
}
