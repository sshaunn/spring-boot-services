package com.dev.employee.service;

import com.dev.employee.exception.DateParseException;
import com.dev.employee.model.Employee;
import com.dev.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) {
        log.info("Save Employee {}: ", employee);
        return repository.save(employee);
    }

    public List<Employee> saveEmployeeList(List<Employee> employees) {
        log.info("Save EmployeeList ");
        try {
            for (Employee employee : employees) {
                String[] payPeriod = employee.getPaymentStartDate().split(" - ");
                String startDate = payPeriod[0];
                String endDate = payPeriod[1];
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM");
                Date d1 = sdf.parse(startDate);
                Date d2 = sdf.parse(endDate);
                int diff = (int) Math.round((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24) / 30);
//                int paymentMonth = diff.intValue();
                employee.setPaymentMonth(diff);
            }
        } catch (Exception e) {
            log.error("Some error: " + e.getMessage());

        }

        return repository.saveAll(employees);
    }

    public Employee findEmployeeById(Long employeeId) {
        log.info("Find employee ");
        return repository.findEmployeeByEmployeeId(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
