package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Service.UserService;
import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService service;

    @GetMapping(Mappings.LOGIN)
    @ResponseBody
    public String login(@RequestParam String usernameOrEmail, @RequestParam String password) {
       boolean isSuccessful = service.login(usernameOrEmail, password);
       if (isSuccessful) {
           return "Login Successful";
       }
       return "Login is unsuccessful";
    }
}
