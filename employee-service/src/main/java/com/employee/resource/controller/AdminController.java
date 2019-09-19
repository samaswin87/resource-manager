package com.employee.resource.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

//refer https://stackoverflow.com/a/36170687/2634405
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {

}
