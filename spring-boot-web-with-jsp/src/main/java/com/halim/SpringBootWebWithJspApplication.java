package com.halim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootWebWithJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebWithJspApplication.class, args);
	}

}
