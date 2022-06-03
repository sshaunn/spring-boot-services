package com.dev.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlip {


    private Employee employee;
    private String fromDate, toDate;
    private BigDecimal grossIncome, incomeTax, superannuation, netIncome;

    public PaySlip(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PaySlip {" +
                "employee=" + employee +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superannuation=" + superannuation +
                ", netIncome=" + netIncome +
                '}';
    }
}
