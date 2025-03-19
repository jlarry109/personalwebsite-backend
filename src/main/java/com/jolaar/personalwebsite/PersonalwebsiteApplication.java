package com.jolaar.personalwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.jolaar.personalwebsite"})
public class PersonalwebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalwebsiteApplication.class, args);
	}
}
