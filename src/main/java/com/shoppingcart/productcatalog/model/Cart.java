package com.shoppingcart.productcatalog.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoppingcart.productcatalog.dto.cart.AddCartDto;

@Entity
@Table(name = "shop_cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cartId;
	
	@Column(name = "created_by")
	private String createdBy;
    
    @Column(name = "created_on")
    private Date createdOn;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "updated_on")
    private Date updatedOn;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "user_id")
    private Integer userId;
    
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

	@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private User user;

    public Integer getCartId() {
		return cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart() {
		
	}

	public Cart(AddCartDto addToCartDto) {
		this.cartId=addToCartDto.getId();
		this.productId=addToCartDto.getProductId();
		this.quantity=addToCartDto.getQuantity();
		this.userId=addToCartDto.getUserId();
	}
	
}
