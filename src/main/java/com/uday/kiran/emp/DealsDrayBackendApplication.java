package com.uday.kiran.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.uday.kiran.emp")
public class DealsDrayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealsDrayBackendApplication.class, args);
	}

}
