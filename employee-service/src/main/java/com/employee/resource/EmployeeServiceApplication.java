package com.employee.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import com.resource.common.config.HibernateConfiguration;

//refer: https://stackoverflow.com/a/38637273/2634405
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Import({HibernateConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableJdbcHttpSession
@ComponentScan(basePackages = {
		"com.employee.resource",
		"com.resource.common",
})
@FeignClient(name="employee-service")
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
