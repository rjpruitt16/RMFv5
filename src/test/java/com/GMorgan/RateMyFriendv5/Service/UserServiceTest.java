package com.GMorgan.RateMyFriendv5.Service;

import com.GMorgan.RateMyFriendv5.Entitiy.Role;
import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    @Autowired
    private UserService service;

    private List<User> users;

    private User user;
    private String email = "rahmiaG@google.com";
    private String username = "rahmiIsAOG";
    private String password = "sorryHackers";

    private User userB;
    private String emailB = "Iamnotrahmi@ramco.com";
    private String usernameB = "NotRahmi";



    @Before
    public void setupMockDB() {
        user = new User(1L, email, username, password, Role.USER, new ArrayList<>());
        userB = new User(2L, emailB, usernameB, password, Role.USER, new ArrayList<>());

        users = new ArrayList<>();
        users.add(user);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {
        when(repository.findByUsername(anyString())).thenReturn(users);

        assertTrue(service.login(email, password));
        assertFalse(service.login(email, "notPassword"));

    }

    @Test
    public void signUpTest() {
        // Test signUp success
        when(repository.findByEmail(anyString())).thenReturn(new ArrayList<>());
        when(repository.findByUsername(anyString())).thenReturn(new ArrayList<>());
        when(repository.save(anyObject())).thenReturn(user);
        assertTrue(service.signUp(email, username, password));

        // Test signUp when email is already in use
        when(repository.findByEmail(anyString())).thenReturn(users);
        when(repository.findByUsername(anyString())).thenReturn(new ArrayList<>());
        when(repository.save(anyObject())).thenReturn(user);
        assertFalse(service.signUp(email, username, password));

        // Test signUp when username is already in use
        when(repository.findByEmail(anyString())).thenReturn(new ArrayList<>());
        when(repository.findByUsername(anyString())).thenReturn(users);
        when(repository.save(anyObject())).thenReturn(user);
        assertFalse(service.signUp(email, username, password));
    }

    @Test
    public void connectTest() {
        Optional<User> optionalUser = Optional.of(user);
        Optional<User> optionalUserB = Optional.of(userB);

        // Test successful connection
        when(repository.findById(anyLong()))
                .thenReturn(optionalUser)
                .thenReturn(optionalUserB);

        Optional<User> answer = service.connectUsers(1L, 2L);
        assertTrue(answer.isPresent());
        assertTrue(answer.get().getUsersFollowing().contains(optionalUserB.get()));

        // Test invalid user connect
        when(repository.findById(anyLong()))
                .thenReturn(optionalUser)
                .thenReturn(Optional.empty());

        Assert.assertEquals(Optional.empty(), service.connectUsers(1L, 3L));
    }
}