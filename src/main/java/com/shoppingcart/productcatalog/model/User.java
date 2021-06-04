package com.shoppingcart.productcatalog.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user")
@Table(name = "\"user\"")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_on")
    private Date createdOn;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "timezone")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String timezone;
    
    public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
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
//		Date date1 = new Date();
//		ZonedDateTime ofInstant = ZonedDateTime.ofInstant(date1.toInstant(), ZoneId.of(timezone));
//		Instant instant = ofInstant.toInstant();
//		Date date = Date.from(instant);
		this.updatedOn = updatedOn;
	}

	@Column(name = "updated_on")
    private Date updatedOn;
    
        
    @Column(name = "role_id")
    private Integer roleId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",insertable=false, updatable=false)
    private Role role;
    
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public User(Integer userId, String firstName, String lastName, String email, String password, String createdBy,
			Date createdOn, String updatedBy, Date updatedOn,String timezone, Integer roleId, Role role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.timezone=timezone;
		this.roleId = roleId;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ",  roleId=" + roleId +", timezone="+ timezone + ", role="
				+ role + "]";
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
		
//		Date date1 = new Date();
//		ZonedDateTime ofInstant = ZonedDateTime.ofInstant(date1.toInstant(), ZoneId.of(timezone));
//		Instant instant = ofInstant.toInstant();
//		Date date = Date.from(instant);
		this.createdOn = createdOn;
	}


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public User() {
		
	}

	public User(String firstName, String lastName,String email,Integer roleId,String password ,String timezone) {
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.email=email;
    	this.roleId=roleId;
    	this.password=password;
    	this.timezone=timezone;
    }
    
}
