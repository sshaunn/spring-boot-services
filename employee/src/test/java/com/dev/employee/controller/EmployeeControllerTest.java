//package com.dev.employee.controller;
//
//import com.dev.employee.model.Employee;
//import com.dev.employee.repository.EmployeeRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EmployeeController.class)
//class EmployeeControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper mapper;
//
//    @MockBean
//    EmployeeRepository repository;
//
//    Employee e1 = new Employee(1, "Shaun", "Shen", "01 March - 31 March", 120000, 0, 0.1);
//    Employee e2 = new Employee(2, "Shaun1", "Shen2", "01 March - 31 April", 120000, 0, 0.09);
//    Employee e3 = new Employee(3, "Shaun2", "Shen3", "01 March - 31 May", 120000, 0, 0.08);
//
//
//    @Test
//    public void saveAllEmployeesTest() throws Exception {
//        List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3));
//
//        Mockito.when(repository.saveAll(list)).thenReturn(list);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employees/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(list));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk());
//
//
//    }
//}