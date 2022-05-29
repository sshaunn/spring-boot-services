package com.dev.income.service;

import com.dev.income.exception.EmployeeNotFoundException;
import com.dev.income.model.Income;
import com.dev.income.orm.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IncomeService {

//    @Autowired
//    private IncomeRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    public List<Income> calculateAllEmployeesIncome() throws EmployeeNotFoundException {
        List<Income> incomes = new ArrayList<>();
        List<Employee> employees =
                restTemplate.exchange("http://EMPLOYEE-SERVICE/employees/",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                        }).getBody();

        for (Employee employee: employees) {
            incomes.add(new Income(getPayPeriod(employee)[0],
                    getPayPeriod(employee)[1],
                    getGrossIncome(employee),
                    getIncomeTax(employee),
                    getSuper(employee),
                    getNetIncome(employee),
                    employee));
//            System.out.println(getGrossIncome(employee));
        }
        return incomes;
    }

    private int getGrossIncome(Employee employee) {
        return Math.round(employee.getAnnualSalary() / 12 * employee.getPaymentMonth());
    }

    private int getIncomeTax(Employee employee) {
        int taxLevel1 = 18200;
        if (employee.getAnnualSalary() <= taxLevel1) return 0;
        int taxLevel2 = 37000;
        if (employee.getAnnualSalary() > taxLevel1 && employee.getAnnualSalary() <= taxLevel2) {
            return (int) (Math.round((employee.getPaymentMonth() - taxLevel1) * 0.19) / 12 * employee.getPaymentMonth());
        }
        int taxLevel3 = 87000;
        if (employee.getAnnualSalary() > taxLevel2 && employee.getAnnualSalary() <= taxLevel3) {
            return (int) Math.round((3572 + (employee.getAnnualSalary() - taxLevel2) * 0.325) / 12 * employee.getPaymentMonth());
        }
        int taxLevel4 = 180000;
        if (employee.getAnnualSalary() > taxLevel3 && employee.getAnnualSalary() <= taxLevel4) {
            return (int) Math.round((19822 + (employee.getAnnualSalary() - taxLevel3) * 0.37) / 12 * employee.getPaymentMonth());
        }
        if (employee.getAnnualSalary() > taxLevel4) {
            return (int) Math.round((54323 + (employee.getAnnualSalary() - taxLevel4) * 0.45) / 12 * employee.getPaymentMonth());
        }
        return 0;
    }

    private int getNetIncome(Employee employee) {
        return getGrossIncome(employee) - getIncomeTax(employee);
    }

    private int getSuper(Employee employee) {
        return (int) Math.round(getGrossIncome(employee) / employee.getPaymentMonth() * employee.getSuperRate());
    }

    private String[] getPayPeriod(Employee employee) {
        return employee.getPaymentStartDate().split(" - ");
    }

}
