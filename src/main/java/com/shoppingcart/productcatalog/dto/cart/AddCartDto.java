package com.shoppingcart.productcatalog.dto.cart;

public class AddCartDto {
	private Integer id;
	private Integer productId;
	private Integer quantity;
	private Integer userId;
	
	public AddCartDto() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public AddCartDto(Integer id, Integer productId, Integer quantity, Integer userId) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddCartDto [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", userId=" + userId
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
