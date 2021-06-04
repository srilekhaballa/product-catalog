package com.shoppingcart.productcatalog.utils;

import org.springframework.security.core.userdetails.UserDetails;

public class UserContextHolder {
    private static final InheritableThreadLocal<UserDetails> userDetailsInheritableThreadLocal=new InheritableThreadLocal<>();
    public static void setUserDetails(UserDetails userDetails){
        userDetailsInheritableThreadLocal.set(userDetails);

    }
    public static UserDetails getUserDetails(){
        return userDetailsInheritableThreadLocal.get();
    }
    public static void removeUserDetails(){
        userDetailsInheritableThreadLocal.remove();
    }
}
