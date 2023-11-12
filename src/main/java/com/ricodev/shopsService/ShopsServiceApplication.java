package com.ricodev.shopsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ShopsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopsServiceApplication.class, args);
	}

}
