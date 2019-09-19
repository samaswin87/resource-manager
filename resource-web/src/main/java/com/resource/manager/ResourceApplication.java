package com.resource.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import com.resource.manager.config.SessionContextListener;

@EnableZuulProxy
@EnableJdbcHttpSession
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ServletComponentScan(basePackageClasses = { SessionContextListener.class })
@EntityScan("com.resource.common.model")
@ComponentScan({ "com.employee.resource.controller", "com.resource.manager", "com.employee.resource", "com.resource.common"})
@EnableJpaRepositories({ "com.resource.manager.*", "com.employee.resource.*", "com.resource.common.*"})
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}
