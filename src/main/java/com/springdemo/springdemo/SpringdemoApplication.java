package com.springdemo.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@Import()
public class SpringdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringdemoApplication.class, args);

	}

}
