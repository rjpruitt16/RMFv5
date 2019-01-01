package com.GMorgan.RateMyFriendv5.Service;

import com.GMorgan.RateMyFriendv5.Entitiy.Role;
import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Configurable
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean login(String username, String password) {
        // TODO make custom exceptions and return a Response instead of boolean
        List<User> userList = repository.findByUsername(username);
        boolean isSuccessful = userList.get(0).isPassword(password);
        log.info("Username: {} isSucessful: {}", username, isSuccessful);
        return isSuccessful;
    }

    public boolean signUp(String email, String username, String password) {
        // TODO make custom exceptions and return a Response instead of boolean
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

    public void logAllUsers() {
        log.info("log All Users called");
        repository.findAll().forEach(user ->
                log.info(user.toString())
        );
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    // Connects the user in the first parameter to the user in the
    // second parameter by using there ids.
    public Optional<User> connectUsers(Long one, Long two) {
        Optional<User> optionalUser = repository.findById(one);
        Optional<User> optionalUserB = repository.findById(two);
        User user;
        User userB;
        if (optionalUser.isPresent() && optionalUserB.isPresent()) {
            user = optionalUser.get();
            userB = optionalUserB.get();
        } else {
            return Optional.empty();
        }
        user.follow(userB);
        log.info("Updated user: " + user.toString());
        repository.save(user);
        return Optional.of(user);
    }


    private boolean userEmailExists(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    private boolean userUsernameExists(String username) {
        return !repository.findByUsername(username).isEmpty();
    }


}
