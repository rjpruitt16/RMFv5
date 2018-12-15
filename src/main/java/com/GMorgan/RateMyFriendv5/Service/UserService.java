package com.GMorgan.RateMyFriendv5.Service;

import com.GMorgan.RateMyFriendv5.Entitiy.Role;
import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository repository;

    public boolean authenticate(String username, String password) {
        List<User> userList = repository.findByUsername(username);
        return userList.get(0).isPassword(password);

    }

    public boolean signUp(String email, String username, String password) {
        if (userEmailExists(email) || userUsernameExists(username)) return false;
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        repository.save(user);
        return true;
    }

    public boolean userEmailExists(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    public boolean userUsernameExists(String username) {
        return !repository.findByUsername(username).isEmpty();
    }


}
