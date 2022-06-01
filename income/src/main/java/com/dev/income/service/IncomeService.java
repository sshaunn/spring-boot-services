package com.dev.income.service;

import com.dev.income.exception.EmployeeNotFoundException;
import com.dev.income.model.Income;
import com.dev.income.model.TaxTable;
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
        }
        return incomes;
    }

    private int getGrossIncome(Employee employee) {
        return Math.round(employee.getAnnualSalary() / 12 * employee.getPaymentMonth());
    }

    private int getIncomeTax(Employee employee) {
        Double employeeIncome = (double) employee.getAnnualSalary();
        int employeePaymentMonth = employee.getPaymentMonth();
        for (int i = getTaxTable().size() - 1; i >= 0; i--) {
            double threshold = getTaxTable().get(i).getTaxThreshold();
            double taxForEachDollar = getTaxTable().get(i).gettaxForEachDollar();
            double taxInit = getTaxTable().get(i).getTaxInit();

            if (employeeIncome < getTaxTable().get(i).getTaxThreshold()) continue;
            int incomeTax = (int) Math.round((taxInit + (employeeIncome - threshold) * taxForEachDollar) / 12 * employeePaymentMonth);
            return incomeTax;
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

    private List<TaxTable> getTaxTable() {
        List<TaxTable> taxTable = new ArrayList<>();
        taxTable.add(new TaxTable("threshold_1", 0.00, 0.00, 0.00));
        taxTable.add(new TaxTable("threshold_2", 0.00, 0.19, 18200.00));
        taxTable.add(new TaxTable("threshold_3", 3572.00, 0.325, 37000.00));
        taxTable.add(new TaxTable("threshold_4", 19822.00, 0.37, 87000.00));
        taxTable.add(new TaxTable("threshold_5", 54232.00, 0.45, 180000.00));
        return taxTable;
    }

}
