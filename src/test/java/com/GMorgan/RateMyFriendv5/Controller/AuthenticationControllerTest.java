package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    AuthenticationController controller = new AuthenticationController();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {
        // Test login success
        when(service.login(anyString(), anyString())).thenReturn(true);
        String returnMesasge = controller.login(anyString(), anyString());
        assertEquals(returnMesasge, "Login successful");

        // Test login is unsuccessful
        when(service.login(anyString(), anyString())).thenReturn(false);
        returnMesasge = controller.login(anyString(), anyString());
        assertEquals(returnMesasge, "Login is unsuccessful");
    }

    @Test
    public void signUpTest() {
        // Test login success
        when(service.signUp(anyString(), anyString(), anyString())).thenReturn(true);
        String returnMesasge = controller.signUp(anyString(), anyString(), anyString());
        assertEquals(returnMesasge, "Signup successful");

        // Test login is unsuccessful
        when(service.signUp(anyString(), anyString(), anyString())).thenReturn(false);
        returnMesasge = controller.signUp(anyString(), anyString(), anyString());
        assertEquals(returnMesasge, "Signup is unsuccessful");
    }
}