package com.shoppingcart.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.productcatalog.model.Cart;
import com.shoppingcart.productcatalog.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> deleteByUser(User user);
    
}
