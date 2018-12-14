package com.GMorgan.RateMyFriendv5.Controller;

import com.GMorgan.RateMyFriendv5.Utils.Mappings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ConstantsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${controller.constant.ping.message}")
    public String pingMessage;

    @Test
    public void pingTest() throws Exception {
        this.mockMvc.perform(get(Mappings.PING)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(pingMessage)));
    }
}
