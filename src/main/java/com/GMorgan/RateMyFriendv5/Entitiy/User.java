package com.GMorgan.RateMyFriendv5.Entitiy;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class User {

    enum roles {ADMIN, USER};

    private final java.util.UUID uuid = UUID.randomUUID();

    private String username;

    private String email;
    // TODO implement password hashing algorithms
    private String password;
}

