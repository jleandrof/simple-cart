package com.example.simpleecommerce.users.models;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserDTO {
    @NotNull(message = "O nome não pode ser nulo")
    private String name;

    @NotNull(message = "Email não pode ser nulo")
    private String email;

    @NotNull(message = "Senha não pode ser nula")
    private String password;

    public User toUser(String encriptedPW, UserRoles role) {
        return new User(name, email, encriptedPW, role);
    }
}
