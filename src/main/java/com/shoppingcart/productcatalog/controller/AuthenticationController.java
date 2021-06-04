package com.shoppingcart.productcatalog.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingcart.productcatalog.model.AuthenticationRequest;
import com.shoppingcart.productcatalog.model.AuthenticationResponse;
import com.shoppingcart.productcatalog.service.UserService;
import com.shoppingcart.productcatalog.utils.CustomUserDetailsService;
import com.shoppingcart.productcatalog.utils.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
    UserService userService;
    
	@PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)  {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String token = jwtTokenUtil.generateToken(authentication);
        log.info(token);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
