package com.portfolio.clack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.portfolio.clack"})
public class ClackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClackApplication.class, args);
	}

}
