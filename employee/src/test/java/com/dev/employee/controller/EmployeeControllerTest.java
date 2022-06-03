package com.dev.employee.controller;

import com.dev.employee.model.Employee;
import com.dev.employee.model.PaySlip;
import com.dev.employee.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }
    @Test
    void shouldReturnListofEmployeePayslips() throws Exception{
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("David", "Rudd","01 February - 28 February", 60050, 1, 0.09),
                new Employee("Ryan", "Chen","01 February - 28 February", 120000, 1, 0.1)
        ));
        List<PaySlip> payslips = employees
                .stream()
                .map(e -> service.generatePayslip(e))
                .collect(Collectors.toList());
        String json = new Gson().toJson(new ArrayList<>(payslips));
        System.out.println(json);

        when(service.res(employees))
                .thenReturn(ResponseEntity.ok(payslips));
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"firstname\": \"David\", \"lastname\": \"Rudd\", \"annualSalary\": \"60050\"," +
                                "\"paymentStartDate\": \"01 February - 28 February\", \"paymentMonth\": \"1\", " +
                                "\"superRate\": \"0.09\"}, {\"firstname\": \"Ryan\", \"lastname\": \"Chen\", " +
                                "\"annualSalary\": \"120000\", \"paymentStartDate\": \"01 February - 28 February\", " +
                                "\"paymentMonth\": \"1\", \"superRate\": \"0.1\"}]"))
                        .andReturn();
        MockHttpServletResponse res = result.getResponse();
        assertEquals(HttpStatus.OK.value(), res.getStatus());

    }

}
