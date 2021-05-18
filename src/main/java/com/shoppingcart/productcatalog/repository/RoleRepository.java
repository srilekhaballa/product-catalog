package com.shoppingcart.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoppingcart.productcatalog.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
