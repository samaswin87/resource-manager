package com.resource.common.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

//EL Access: https://docs.spring.io/autorepo/docs/spring-security/4.0.0.RC1/reference/html/el-access.html
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
@RequestMapping(value = "/employee")
public class EmployeeController {

	
}
