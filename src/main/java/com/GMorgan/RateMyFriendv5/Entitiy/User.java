package com.GMorgan.RateMyFriendv5.Entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String username;
    private Role role;

    @ManyToMany
    private List<User> usersFollowing = new ArrayList<>();

    private String hash(String password) {
        return password;
    }

    public boolean isPassword(String password) {
        return this.password.equals(hash(password));
    }

    public void setPassword(String password) {
        this.password = hash(password);
    }

    public boolean follow(User user) {
        usersFollowing.add(user);
        return true;
    }
}

