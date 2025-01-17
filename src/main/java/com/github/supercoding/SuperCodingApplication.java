package com.github.supercoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
public class SuperCodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperCodingApplication.class, args);
	}

}
