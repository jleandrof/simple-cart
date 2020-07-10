package com.example.simpleecommerce.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfo {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    private String email;
    private String name;
    private String role;
}
