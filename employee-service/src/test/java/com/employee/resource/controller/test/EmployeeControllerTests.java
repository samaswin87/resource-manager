package com.employee.resource.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.resource.service.EmployeeService;
import com.employee.resource.service.IEmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {EmployeeService.class})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class EmployeeControllerTests {

	@Autowired
	private IEmployeeService employeeService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
	}

	@Test
	public void createEmployee() {
		System.out.println("Calling");
	}

}
