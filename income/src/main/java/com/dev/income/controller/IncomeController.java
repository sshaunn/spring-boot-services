package com.dev.income.controller;

import com.dev.income.exception.EmployeeNotFoundException;
import com.dev.income.model.Income;
import com.dev.income.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculate")
public class IncomeController {

    @Autowired
    private IncomeService service;


    @GetMapping("/")
    public List<Income> calculateAllEmployeesIncome() throws EmployeeNotFoundException {
        return service.calculateAllEmployeesIncome();
    }

}
