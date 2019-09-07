package com.resource.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import com.resource.common.config.HibernateConfiguration;
import com.resource.manager.config.SessionContextListener;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Import(HibernateConfiguration.class)
@EnableZuulProxy
@EnableJdbcHttpSession
@EnableCircuitBreaker
@EnableDiscoveryClient
@ServletComponentScan(basePackageClasses= {SessionContextListener.class})
@ComponentScan(basePackages = {
		"com.resource.manager",
		"com.resource.common",
})
public class ResourceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceWebApplication.class, args);
	}

}
