package com.dev.employee.serviceTest;

import com.dev.employee.model.Employee;
import com.dev.employee.model.PaySlip;
import com.dev.employee.model.TaxCalculator;
import com.dev.employee.service.EmployeeService;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Test
    public void calculateTaxTest() {
        BigDecimal taxIncome18200Less = TaxCalculator.calculateTax(BigDecimal.valueOf(18200)).get();
        assertTrue(taxIncome18200Less.compareTo(BigDecimal.valueOf(0)) == 0);

        BigDecimal taxIncome18201 = TaxCalculator.calculateTax(BigDecimal.valueOf(18201)).get();
        assertTrue(taxIncome18201.compareTo(BigDecimal.valueOf(0)) == 0);

        BigDecimal taxIncome37000 = TaxCalculator.calculateTax(BigDecimal.valueOf(37000)).get();
        assertTrue(taxIncome37000.compareTo(BigDecimal.valueOf(37000 - 18200)
                .multiply(BigDecimal.valueOf(0.19)).divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome37001 = TaxCalculator.calculateTax(BigDecimal.valueOf(37001)).get();
        assertTrue(taxIncome37001.compareTo(BigDecimal.valueOf(37001 - 37000)
                .multiply(BigDecimal.valueOf(0.325))
                .add(BigDecimal.valueOf(3572))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome87000 = TaxCalculator.calculateTax(BigDecimal.valueOf(87000)).get();
        assertTrue(taxIncome87000.compareTo(BigDecimal.valueOf(87000 - 37000)
                .multiply(BigDecimal.valueOf(0.325))
                .add(BigDecimal.valueOf(3572))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome87001 = TaxCalculator.calculateTax(BigDecimal.valueOf(87001)).get();
        assertTrue(taxIncome87001.compareTo(BigDecimal.valueOf(87001 - 87000)
                .multiply(BigDecimal.valueOf(0.37))
                .add(BigDecimal.valueOf(19822))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome180000 = TaxCalculator.calculateTax(BigDecimal.valueOf(180000)).get();
        assertTrue(taxIncome180000.compareTo(BigDecimal.valueOf(180000 - 87000)
                .multiply(BigDecimal.valueOf(0.37))
                .add(BigDecimal.valueOf(19822))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome180001 = TaxCalculator.calculateTax(BigDecimal.valueOf(180001)).get();
        assertTrue(taxIncome180001.compareTo(BigDecimal.valueOf(180001 - 180000)
                .multiply(BigDecimal.valueOf(0.45))
                .add(BigDecimal.valueOf(54232))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);

        BigDecimal taxIncome200000 = TaxCalculator.calculateTax(BigDecimal.valueOf(200000)).get();
        assertTrue(taxIncome200000.compareTo(BigDecimal.valueOf(200000 - 180000)
                .multiply(BigDecimal.valueOf(0.45))
                .add(BigDecimal.valueOf(54232))
                .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    public void generatePayslipTest() {
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("David", "Rudd","01 February - 28 February", 60050, 1, 0.09),
                new Employee("Ryan", "Chen","01 February - 28 February", 120000, 1, 0.1)
        ));
        EmployeeService es = new EmployeeService();

        List<PaySlip> payslips = employees
                .stream()
                .map(e -> es.generatePayslip(e))
                .collect(Collectors.toList());
        assertTrue(Integer.valueOf(2).equals(Integer.valueOf(payslips.size())));

        for (PaySlip paySlip: payslips) {
            if (paySlip.getEmployee().getFirstName().equals("David")) {
                assertTrue(paySlip.getFromDate().equals("01 February"));
                assertEquals(paySlip.getToDate(), "28 February");
                assertEquals(paySlip.getGrossIncome(), BigDecimal.valueOf(5004));
                assertEquals(paySlip.getIncomeTax(), BigDecimal.valueOf(922));
                assertEquals(paySlip.getSuperannuation(), BigDecimal.valueOf(450));
                assertEquals(paySlip.getNetIncome(), BigDecimal.valueOf(4082));
            } else if (paySlip.getEmployee().getFirstName().equals("Ryan")){
                assertTrue(paySlip.getFromDate().equals("01 February"));
                assertEquals(paySlip.getToDate(), "28 February");
                assertEquals(paySlip.getGrossIncome(), BigDecimal.valueOf(10000));
                assertEquals(paySlip.getIncomeTax(), BigDecimal.valueOf(2669));
                assertEquals(paySlip.getSuperannuation(), BigDecimal.valueOf(1000));
                assertEquals(paySlip.getNetIncome(), BigDecimal.valueOf(7331));
            }
        };

    }

}
