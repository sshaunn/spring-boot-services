package com.dev.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    private String firstName, lastName, paymentStartDate;
    private int annualSalary, paymentMonth;
    private double superRate;

    public int getPaymentMonth() {
        String[] payPeriod = this.paymentStartDate.split(" - ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM");
        MonthDay startDate = MonthDay.parse(payPeriod[0], dtf);
        MonthDay endDate = MonthDay.parse(payPeriod[1], dtf);
        int paymentMonth = endDate.getMonth().compareTo(startDate.getMonth()) + 1;
        return paymentMonth;
    }

    @JsonIgnore
    public String[] getStartAndEndDate() {
        return this.paymentStartDate.split(" - ");
    }

    @Override
    public String toString() {
        return "Employee: {" +
                "firstName='" + firstName +
                ", lastName='" + lastName + '\'' +
                ", paymentStartDate='" + paymentStartDate + '\'' +
                ", annualSalary=" + annualSalary +
                ", paymentMonth=" + paymentMonth +
                ", superRate=" + superRate +
                '}';
    }
}
