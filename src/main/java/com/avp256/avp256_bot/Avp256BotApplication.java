package com.avp256.avp256_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Avp256BotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(Avp256BotApplication.class, args);
	}

}
