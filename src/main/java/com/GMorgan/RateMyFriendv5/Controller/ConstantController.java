package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstantController {

    @RequestMapping(Mappings.PING)
    public String ping() {
        return "Working properly";
    }
}
