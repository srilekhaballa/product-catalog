package com.shoppingcart.productcatalog.model;

import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private String name;
    private List<String> permissions;
    private String roleName;
    private String userId;
    private Integer roleId;
    private String mailId;
	private String password;
	private String timezone;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(roleName));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return mailId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
