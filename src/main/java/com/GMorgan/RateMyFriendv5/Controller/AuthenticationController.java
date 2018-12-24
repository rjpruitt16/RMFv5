package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Service.UserService;
import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import com.GMorgan.RateMyFriendv5.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    Response signUp(@RequestParam("email") String email,
                    @RequestParam("userId") String username,
                    @RequestParam("password") String password) {
        boolean isSuccessful = service.signUp(email, username, password);
        if (isSuccessful) {
            return new Response(true, signUpSuccess);
        }
        return new Response(false, signUpfail);
    }
}
