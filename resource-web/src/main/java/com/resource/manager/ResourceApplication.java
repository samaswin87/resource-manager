package com.resource.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Profile("!development")
@EnableZuulProxy
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan("com.resource.common.model")
@ComponentScan({ 
	"com.employee.resource.controller",
	"com.resource.manager",
	"com.employee.resource",
	"com.resource.common",
	"com.company.resource"
})
@EnableJpaRepositories({ 
	"com.resource.manager.*",
	"com.employee.resource.*",
	"com.resource.common.*",
	"com.company.resource.*"
})
@EnableCaching
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}
