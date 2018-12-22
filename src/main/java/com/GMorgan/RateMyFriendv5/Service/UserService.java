package com.GMorgan.RateMyFriendv5.Service;

import com.GMorgan.RateMyFriendv5.Entitiy.Role;
import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private UserRepository repository;

    public boolean login(String username, String password) {
        List<User> userList = repository.findByUsername(username);
        boolean isSuccessful = userList.get(0).isPassword(password);
        log.info("Username: {} isSucessful: {}", username, isSuccessful);
        return isSuccessful;
    }

    public boolean signUp(String email, String username, String password) {
        if (userEmailExists(email) || userUsernameExists(username)) return false;
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        repository.save(user);
        log.info("User email: {} username: {}", email, username);
        return true;
    }

    private boolean userEmailExists(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    private boolean userUsernameExists(String username) {
        return !repository.findByUsername(username).isEmpty();
    }
}
