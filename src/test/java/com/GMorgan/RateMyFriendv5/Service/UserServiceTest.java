package com.GMorgan.RateMyFriendv5.Service;

import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service = new UserService();

    private List<User> users;
    private User user;

    private String email = "rahmiaG@google.com";
    private String username = "rahmiIsAOG";
    private String password = "sorryHackers";

    @Before
    public void setupMockDB() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        users = new ArrayList<>();
        users.add(user);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login() {
        when(repository.findByUsername(anyString())).thenReturn(users);

        assertTrue(service.login(email, password));
        assertFalse(service.login(email, "notPassword"));

    }

    @Test
    public void signUp() {
        // Test signUp success
        when(repository.findByEmail(anyString())).thenReturn(new ArrayList<>());
        when(repository.findByUsername(anyString())).thenReturn(new ArrayList<>());
        when(repository.save(anyObject())).thenReturn(user);
        assertTrue(service.signUp(email, username, password));

        // Test signUp when email is email is already in use
        when(repository.findByEmail(anyString())).thenReturn(users);
        when(repository.findByUsername(anyString())).thenReturn(new ArrayList<>());
        when(repository.save(anyObject())).thenReturn(user);
        assertFalse(service.signUp(email, username, password));

        // Test signUp when username is email is already in use
        when(repository.findByEmail(anyString())).thenReturn(new ArrayList<>());
        when(repository.findByUsername(anyString())).thenReturn(users);
        when(repository.save(anyObject())).thenReturn(user);
        assertFalse(service.signUp(email, username, password));
    }
}