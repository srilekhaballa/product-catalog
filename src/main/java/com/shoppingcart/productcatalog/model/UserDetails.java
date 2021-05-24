package com.shoppingcart.productcatalog.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {
    private String name;
    private List<String> permissions;
    private String roleName;
    private String userId;
    private String roleId;
    private String mailId;
}
