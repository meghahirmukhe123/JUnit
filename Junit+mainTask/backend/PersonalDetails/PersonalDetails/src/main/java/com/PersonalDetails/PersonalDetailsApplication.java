package com.PersonalDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PersonalDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDetailsApplication.class, args);
	}

}
