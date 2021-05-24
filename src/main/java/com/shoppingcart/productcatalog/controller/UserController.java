package com.shoppingcart.productcatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingcart.productcatalog.dto.user.SignInDto;
import com.shoppingcart.productcatalog.dto.user.SignupDto;
import com.shoppingcart.productcatalog.repository.UserRepository;
import com.shoppingcart.productcatalog.service.AuthenticationService;
import com.shoppingcart.productcatalog.service.UserService;
import com.shoppingcart.productcatalog.utils.Helper;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
    AuthenticationService authenticationService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/get")
	public ResponseEntity<?> findAllUser(@RequestParam("token") String token) throws Exception {
		authenticationService.authenticate(token);
		return new  ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> Signup(@RequestBody SignupDto signupDto) throws Exception {
		if(Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
			return new ResponseEntity<String>("User already exists", HttpStatus.BAD_REQUEST);
		}
		return new  ResponseEntity<>(userService.signUp(signupDto), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> Signin(@RequestBody SignInDto signInDto) throws Exception {
		if(userRepository.findByEmail(signInDto.getEmail())==null) {
			return new ResponseEntity<String>("User doesn't exists", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.signIn(signInDto),HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findUserById(@RequestParam("id") Integer id) throws Exception {
		if(userRepository.findById(id)==null) {
			return new ResponseEntity<String>("User doesn't exists", HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getByEmail")
	public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) throws Exception {
		return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
		
	}
}
