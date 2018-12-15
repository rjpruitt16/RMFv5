package com.GMorgan.RateMyFriendv5.Entitiy;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class User {

    private final java.util.UUID uuid = UUID.randomUUID();

    private String username;

    private String email;

    private String password;

    private Role role;

    public boolean isPassword(String password) {
        return this.password.equals(hash(password));
    }

    public void setPassword(String passowrd) {
        this.password = hash(passowrd);
    }
    // TODO implement password hashing algorithms
    public String hash(String password) {
        return password;
    }

}

