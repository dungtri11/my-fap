package com.portal.fap;

import com.portal.fap.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FapApplication {
	public static void main(String[] args) {
		SpringApplication.run(FapApplication.class, args);
	}
}
