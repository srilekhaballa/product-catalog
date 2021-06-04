package com.shoppingcart.productcatalog.utils;

import com.shoppingcart.productcatalog.model.CustomUserDetails;
import com.shoppingcart.productcatalog.model.User;
import com.shoppingcart.productcatalog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        System.out.println(user);
        CustomUserDetails customUserDetails =new CustomUserDetails();
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setRoleId((Integer)user.getRoleId());
        customUserDetails.setRoleName(user.getRole().getRoleName());
        customUserDetails.setMailId(user.getEmail());
        customUserDetails.setTimezone(user.getTimezone());
        System.out.println("loadUserByUsername(username)");
        return customUserDetails;
    }
}