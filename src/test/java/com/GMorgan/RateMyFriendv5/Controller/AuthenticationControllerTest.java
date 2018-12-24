package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Service.UserService;
import com.GMorgan.RateMyFriendv5.Utils.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    AuthenticationController controller = new AuthenticationController();

    @Value("${controller.authentation.login.success}")
    private String loginSuccess;
    @Value("${controller.authentation.login.fail}")
    private String loginFail;
    @Value("${controller.authentation.signup.success}")
    private String signUpSuccess;
    @Value("${controller.authentation.signup.fail}")
    private String signUpfail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {
        // Test login success
        when(service.login(anyString(), anyString())).thenReturn(true);
        Response response = controller.login(anyString(), anyString());
        assertEquals(response.getMessage(), loginSuccess);

        // Test login is unsuccessful
        when(service.login(anyString(), anyString())).thenReturn(false);
        response = controller.login(anyString(), anyString());
        assertEquals(response.getMessage(), loginFail);
    }

    @Test
    public void signUpTest() {
        // Test login success
        when(service.signUp(anyString(), anyString(), anyString())).thenReturn(true);
        Response response = controller.signUp(anyString(), anyString(), anyString());
        assertEquals(response.getMessage(), signUpSuccess);

        // Test login is unsuccessful
        when(service.signUp(anyString(), anyString(), anyString())).thenReturn(false);
        response = controller.signUp(anyString(), anyString(), anyString());
        assertEquals(response.getMessage(), signUpfail);
    }
}