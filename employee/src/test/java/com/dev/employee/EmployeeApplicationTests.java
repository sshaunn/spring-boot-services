package com.dev.employee;

import com.dev.employee.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeApplicationTests {

	@Autowired
	private EmployeeController controller;


	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}




}
