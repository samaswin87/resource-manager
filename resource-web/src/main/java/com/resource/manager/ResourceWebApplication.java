package com.resource.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ResourceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceWebApplication.class, args);
	}

}
