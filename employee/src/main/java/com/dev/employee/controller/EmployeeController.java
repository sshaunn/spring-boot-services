package com.dev.employee.controller;

import com.dev.employee.exception.DateParseException;
import com.dev.employee.model.Employee;
import com.dev.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping("/")
    public List<Employee> saveEmployeeList(@RequestBody List<Employee> employees) throws DateParseException{
        log.info("Save employeeList ");
        return service.saveEmployeeList(employees);
    }

    @DeleteMapping("/delete")
    public void deleteAllEmployee() {
        service.deleteAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee findEmployeeByEmployeeId(@PathVariable("id") Long employeeId) {
        log.info("Find employee from controller findbyid ");
        return service.findEmployeeById(employeeId);
    }

    @GetMapping("/")
    public List<Employee> getAllEmployee() {
        return service.getAllEmployees();
    }
}
