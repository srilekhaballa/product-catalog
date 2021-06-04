package com.shoppingcart.productcatalog.service;

import static com.shoppingcart.productcatalog.config.MessageStrings.USER_CREATED;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.productcatalog.config.MessageStrings;
import com.shoppingcart.productcatalog.dto.ResponseDto;
import com.shoppingcart.productcatalog.dto.user.SignInDto;
import com.shoppingcart.productcatalog.dto.user.SignupDto;
import com.shoppingcart.productcatalog.exceptions.AuthenticationFailException;
import com.shoppingcart.productcatalog.exceptions.CustomException;
import com.shoppingcart.productcatalog.exceptions.ProductNotExistException;
import com.shoppingcart.productcatalog.exceptions.UserNotExistException;
import com.shoppingcart.productcatalog.model.User;
import com.shoppingcart.productcatalog.repository.UserRepository;
import com.shoppingcart.productcatalog.utils.Helper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ss:aa")
    Date date = new Date();
	
	public ResponseDto signUp(SignupDto signupDto) throws CustomException, NoSuchAlgorithmException {
		if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
			throw new CustomException("User already exists");
		}
		String encryptedPassword = passwordEncoder.encode(signupDto.getPassword());
		
		User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
				signupDto.getRoleId(), encryptedPassword, signupDto.getTimezone());

		User createdUser;
		ZonedDateTime ofInstant = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(signupDto.getTimezone()));
		Instant instant = ofInstant.toInstant();
		Date date1 = Date.from(instant);
		logger.info(" "+instant);
		logger.info(" "+date1);
		try {
			user.setCreatedBy("sri");
			user.setCreatedOn(date1);
			user.setUpdatedBy("sri");
			user.setUpdatedOn(date1);
			createdUser = userRepository.save(user);
			return new ResponseDto(HttpStatus.CREATED, USER_CREATED);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	public String signIn(SignInDto signInDto) throws CustomException, NoSuchAlgorithmException {
		User user = userRepository.findByEmail(signInDto.getEmail());
				if (!user.getPassword().equals(passwordEncoder.encode(signInDto.getPassword()))) {
					throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
				}
		 return "logged in successfully"; 
	}

	/*
	 * public ResponseDto createUser(UserCreateDto userCreateDto) throws
	 * CustomException, AuthenticationFailException { User creatingUser = new
	 * User(); if (canCrudUser(userCreateDto.getRoleId())) { throw new
	 * AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED); } String
	 * encryptedPassword = userCreateDto.getPassword(); try { encryptedPassword =
	 * hashPassword(userCreateDto.getPassword()); } catch (NoSuchAlgorithmException
	 * e) { e.printStackTrace(); logger.error("hashing password failed {}",
	 * e.getMessage()); }
	 * 
	 * User user = new User(userCreateDto.getFirstName(),
	 * userCreateDto.getLastName(), userCreateDto.getEmail(),
	 * userCreateDto.getRoleId(), encryptedPassword,userCreateDto.); User
	 * createdUser; try { user.setCreatedBy("sri"); user.setCreatedOn(date);
	 * user.setUpdatedBy("sri"); user.setUpdatedOn(date); createdUser =
	 * userRepository.save(user); return new ResponseDto(HttpStatus.CREATED,
	 * USER_CREATED); } catch (Exception e) { throw new
	 * CustomException(e.getMessage()); }
	 * 
	 * }
	 */
	
	public User getUserById(Integer userId) throws UserNotExistException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new ProductNotExistException("User id is invalid " + userId);
        return optionalUser.get();
    }
	
	public User findByEmail(String email) throws UserNotExistException {
        User user = userRepository.findByEmail(email);
        logger.info(email);
        return user;
	}
	
	boolean canCrudUser(Integer roleId) {
		if (roleId == 3 || roleId == 2) {
			return true;
		}
		return false;
	}

	boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
		Integer roleId = userUpdating.getRoleId();
		if (roleId == 3 || roleId == 2) {
			return true;
		}
		if (roleId == 1 && userUpdating.getUserId() == userIdBeingUpdated) {
			return true;
		}
		return false;
	}
	
	public List<User> findAll() {
        return userRepository.findAll();
    }
	
}
