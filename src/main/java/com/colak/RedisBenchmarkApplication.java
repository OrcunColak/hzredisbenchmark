package com.colak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisBenchmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisBenchmarkApplication.class, args);
	}

}
