package com.shoppingcart.productcatalog;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = Application.class , webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ProductCatalogApplication.class})
public class BaseClass {
    protected TestRestTemplate testRestTemplate = new TestRestTemplate();
}

