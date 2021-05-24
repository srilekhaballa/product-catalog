package com.shoppingcart.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoppingcart.productcatalog.model.AuthenticationToken;
import com.shoppingcart.productcatalog.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, String> {

	AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
	
}
