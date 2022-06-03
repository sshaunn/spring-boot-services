package com.dev.employee.service;

import com.dev.employee.model.Employee;
import com.dev.employee.model.PaySlip;
import com.dev.employee.model.TaxCalculator;
import com.netflix.servo.util.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {


    public ResponseEntity<List<PaySlip>> res(List<Employee> employees) {
        List<PaySlip> paySlips = employees.stream()
                .map(employee -> generatePayslip(employee))
                .collect(Collectors.toList());

        return ResponseEntity.ok(paySlips);
    }

    public PaySlip generatePayslip(Employee employee) {
        if (employee.getAnnualSalary() < 0
                || employee.getSuperRate() < 0) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Annual Salary or Super Rate must be positive...");

        BigDecimal grossIncome = BigDecimal.valueOf(employee.getAnnualSalary() / 12)
                .multiply(BigDecimal.valueOf(employee.getPaymentMonth()));

        BigDecimal superannuation = grossIncome
                .multiply(BigDecimal.valueOf(employee.getSuperRate()))
                .setScale(0, RoundingMode.HALF_UP);

        Optional<BigDecimal> incomeTaxOptional = TaxCalculator
                .calculateTax(BigDecimal.valueOf(employee.getAnnualSalary()));

        BigDecimal incomeTax = incomeTaxOptional.get()
                .multiply(BigDecimal.valueOf(employee.getPaymentMonth()));

        BigDecimal netIncome = grossIncome.subtract(incomeTax);

        PaySlip payslip = new PaySlip(employee, employee.getStartAndEndDate()[0],
                employee.getStartAndEndDate()[1], grossIncome, incomeTax, superannuation, netIncome);
        return payslip;
    }
}
