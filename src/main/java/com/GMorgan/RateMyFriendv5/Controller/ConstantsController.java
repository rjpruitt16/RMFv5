package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstantsController {

    @Value("${controller.constant.ping.message}")
    private String pingMessage;

    @RequestMapping(Mappings.PING)
    public String ping() {
        return pingMessage;
    }
}
