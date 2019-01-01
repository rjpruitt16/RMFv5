package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Entitiy.User;
import com.GMorgan.RateMyFriendv5.Service.UserService;
import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import com.GMorgan.RateMyFriendv5.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
class AuthenticationController {

    @Autowired
    private UserService service;

    @Value("${controller.authentation.login.success}")
    private String loginSuccess;
    @Value("${controller.authentation.login.fail}")
    private String loginFail;
    @Value("${controller.authentation.signup.success}")
    private String signUpSuccess;
    @Value("${controller.authentation.signup.fail}")
    private String signUpfail;

    @RequestMapping(Mappings.LOGIN)
    @ResponseBody
        // Example url: http://localhost:8080/login?userId=reggie&password=password123
    Response login(@RequestParam("userId") String usernameOrEmail,
                   @RequestParam("password") String password) {
        boolean isSuccessful = service.login(usernameOrEmail, password);
        if (isSuccessful) {
            return new Response(true, loginSuccess);
        }
        return new Response(false, loginFail);
    }

    @RequestMapping(Mappings.SIGNUP)
    @ResponseBody
        // Example url: http://localhost:8080/signup?email=rahmi@google.com&userId=rahmi&password=password123
        // Example url: http://localhost:8080/signup?email=reggie@google.com&userId=reggie&password=password123
    Response signUp(@RequestParam("email") String email,
                    @RequestParam("userId") String username,
                    @RequestParam("password") String password) {
        boolean isSuccessful = service.signUp(email, username, password);
        if (isSuccessful) {
            return new Response(true, signUpSuccess);
        }
        return new Response(false, signUpfail);
    }

    @RequestMapping(Mappings.CONNECT)
    @ResponseBody
        // Example url: http://localhost:8080/connect?sourceID=1&targetID=2
    Response connect(@RequestParam("sourceID") String sourceUUID,
                     @RequestParam("targetID") String targetUUID) {
        Optional<User> user =
                service.connectUsers(Long.parseLong(sourceUUID), Long.parseLong(targetUUID));
        if (user.isPresent()) {
            new Response(true, "User successfully updated");
        }
        return new Response(false, "Error connecting users");
    }

    @RequestMapping(Mappings.USERS)
    @ResponseBody
        // Example url: http://localhost:8080/users
    Response logAllUsers() {
        service.logAllUsers();
        return new Response(true, "Logging completed");
    }
}
