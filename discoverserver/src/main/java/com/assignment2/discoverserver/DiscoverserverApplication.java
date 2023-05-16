package com.assignment2.discoverserver;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverserverApplication.class, args);
	}

}
