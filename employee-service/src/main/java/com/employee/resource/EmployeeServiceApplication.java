package com.employee.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

//refer: https://stackoverflow.com/a/38637273/2634405
@ComponentScan(basePackages = {
		"com.employee.resource",
		"com.resource.common",
})
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
}
