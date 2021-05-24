package com.shoppingcart.productcatalog.dto.user;

public class UserCreateDto {

	private String firstName;
	private String lastName;
	private String email;
	private Integer roleId;
	private Integer cartId;
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public UserCreateDto(String firstName, String lastName, String email, Integer roleId, Integer cartId,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
		this.cartId = cartId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCreateDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", roleId="
				+ roleId + ", cartId=" + cartId + ", password=" + password + "]";
	}

	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRole(Integer roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
