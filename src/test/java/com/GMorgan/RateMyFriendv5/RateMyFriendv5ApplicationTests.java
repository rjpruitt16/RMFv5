package com.GMorgan.RateMyFriendv5;

import com.GMorgan.RateMyFriendv5.Controller.ConstantsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateMyFriendv5ApplicationTests {

	@Autowired
	private ConstantsController controller;

	@Test
	public void contextLoadsTest() {
		assertThat(controller).isNotNull();
	}

}

