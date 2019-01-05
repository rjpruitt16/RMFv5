package com.GMorgan.RateMyFriendv5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.GMorgan.RateMyFriendv5.Service",
		"com.GMorgan.RateMyFriendv5.Controller",
		"com.GMorgan.RateMyFriendv5.Config"})
public class RateMyFriendv5Application {
	public static void main(String[] args) {
		SpringApplication.run(RateMyFriendv5Application.class, args);
	}
}

