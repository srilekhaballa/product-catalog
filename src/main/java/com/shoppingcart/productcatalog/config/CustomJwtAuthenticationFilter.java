package com.shoppingcart.productcatalog.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shoppingcart.productcatalog.exceptions.UnAuthorizedException;
import com.shoppingcart.productcatalog.utils.JwtTokenUtil;
import com.shoppingcart.productcatalog.utils.UserContextHolder;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
	private static final List<String> EXCLUDED_URLS= Collections.unmodifiableList(List.of("/login","/user/get","/swagger-ui.html","/user/register","/user/search","/user/getByEmail","/product/get","/product/add","/product/update/{productID}","/product/export/pdf","/product/export/excel","/category/get","/category/add","/category/update/{categoryID}","/cart/add"));
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            UserDetails userDetails = jwtTokenUtil.getUserDetails(authorization.substring(7));
            UserContextHolder.setUserDetails(userDetails);
            filterChain.doFilter(request,response);
        }else throw new UnAuthorizedException("Authorization token is not present");
    }

	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getServletPath();
        return EXCLUDED_URLS.stream().anyMatch(url -> StringUtils.containsIgnoreCase(requestURI,url));
    }

}
