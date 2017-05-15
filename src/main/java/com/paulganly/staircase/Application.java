package com.paulganly.staircase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.paulganly"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}