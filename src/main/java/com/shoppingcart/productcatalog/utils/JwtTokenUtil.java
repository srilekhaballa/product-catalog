package com.shoppingcart.productcatalog.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.shoppingcart.productcatalog.model.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String USER_ID = "userId";
    public static final String PERMISSIONS = "permissions";
    public static final String ROLE_ID = "roleId";
    public static final String ROLE_NAME = "roleName";
    public static final String MAIL_ID = "mailId";
    public static final String TIME_ZONE = "timeZone";
    
    private static final Logger logger= LogManager.getLogger(JwtTokenUtil.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationDateInMs}")
    private int tokenValidity;

    public CustomUserDetails  getUserDetails(String token) {
        try {
        	return getUserDetails(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
        } catch (ExpiredJwtException e) {
            logger.info(" Token expired ");
        
        } catch(Exception e){
            logger.info(" Some other exception in JWT parsing ");
        }
		return null;
    }

	private CustomUserDetails getUserDetails(Claims claims) {
    	CustomUserDetails userDetails=new CustomUserDetails();
        userDetails.setUserId((String) claims.get(USER_ID));
        userDetails.setPermissions((List<String>) claims.get(PERMISSIONS));
        userDetails.setRoleId((Integer) claims.get(ROLE_ID));
        userDetails.setRoleName((String) claims.get(ROLE_NAME));
        userDetails.setMailId((String) claims.get(MAIL_ID));
        userDetails.setName(claims.getSubject());
        userDetails.setTimezone((String)claims.get(TIME_ZONE));
        return userDetails;
    }

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails= (CustomUserDetails) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",userDetails.getUserId());
        claims.put("timezone", userDetails.getTimezone());
        claims.put("permissions",userDetails.getPermissions());
        claims.put("roleId",userDetails.getRoleId());
        claims.put("roleName",userDetails.getRoleName());
        claims.put("mailId",userDetails.getMailId());
        logger.info(claims);
        return TOKEN_PREFIX +Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(tokenValidity) * 100000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    
}
