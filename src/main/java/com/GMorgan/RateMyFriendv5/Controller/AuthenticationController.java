package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Service.UserService;
import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthenticationController {

    @Autowired
    private UserService service;

    @RequestMapping(Mappings.LOGIN)
    @ResponseBody
    String login(@RequestParam("userId") String usernameOrEmail,
                 @RequestParam("password") String password) {
        boolean isSuccessful = service.login(usernameOrEmail, password);
        if (isSuccessful) {
            return "Login successful";
        }
        return "Login is unsuccessful";
    }

    @RequestMapping(Mappings.SIGNUP)
    @ResponseBody
    String signUp(@RequestParam("email") String email,
                  @RequestParam("userId") String username,
                  @RequestParam("password") String password) {
        boolean isSuccessful = service.signUp(email, username, password);
        if (isSuccessful) {
            return "Signup successful";
        }
        return "Signup is unsuccessful";
    }
}
