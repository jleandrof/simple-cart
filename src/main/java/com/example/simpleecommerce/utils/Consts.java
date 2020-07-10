package com.example.simpleecommerce.utils;

import java.util.Optional;

public class Consts {
    public static Long AUTH_TOKEN_EXPIRATION = Long.valueOf(getEnv("AUTH_TOKEN_EXPIRATION").orElse("1"));

    public static Optional<String> getEnv(String name) {
        return Optional.ofNullable(System.getenv(name));
    }
}
