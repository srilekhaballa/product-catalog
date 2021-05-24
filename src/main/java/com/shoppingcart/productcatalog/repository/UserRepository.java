package com.shoppingcart.productcatalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.productcatalog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer id);

	User findByEmail(String email);
    
}
