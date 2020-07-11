package com.example.simpleecommerce.users.models;

import com.example.simpleecommerce.shoppingcarts.models.ShoppingCart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private final String uuid = UUID.randomUUID().toString();

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "varchar(128)")
    private String password;

    private UserRoles role;

    @OneToOne
    private ShoppingCart shoppingCart;

    public User(String name, String email, String password, UserRoles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserInfo toUserInfo() {
        return new UserInfo(id,null, email, name, role.name());
    }

    public UserInfo toUserInfo(String token) {
        return new UserInfo(id, token, email, name, role.name());
    }
}
