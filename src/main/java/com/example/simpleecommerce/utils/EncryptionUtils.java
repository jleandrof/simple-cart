package com.example.simpleecommerce.utils;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptionUtils {

    public String encrypt(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    public boolean check(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}
