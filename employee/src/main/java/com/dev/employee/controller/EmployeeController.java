package com.dev.employee.controller;

import com.dev.employee.model.Employee;
import com.dev.employee.model.PaySlip;
import com.dev.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping
    public ResponseEntity generatePayslips(@RequestBody List<Employee> employees) {
        ResponseEntity res = service.res(employees);
        return res;
    }

}
