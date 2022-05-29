package com.dev.income.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String firstName, lastName, paymentStartDate;
    private int annualSalary, paymentMonth;
    private double superRate;

    public Employee(long employeeId, String firstName, String lastName, String paymentStartDate, int annualSalary, int paymentMonth, double superRate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.paymentStartDate = paymentStartDate;
        this.annualSalary = annualSalary;
        this.paymentMonth = paymentMonth;
        this.superRate = superRate;
    }

    @Override
    public String toString() {
        return "Employee: {" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentStartDate='" + paymentStartDate + '\'' +
                ", annualSalary=" + annualSalary +
                ", paymentMonth=" + paymentMonth +
                ", superRate=" + superRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (annualSalary != employee.annualSalary) return false;
        if (paymentMonth != employee.paymentMonth) return false;
        if (Double.compare(employee.superRate, superRate) != 0) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        return paymentStartDate.equals(employee.paymentStartDate);
    }



    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + paymentStartDate.hashCode();
        result = 31 * result + annualSalary;
        result = 31 * result + paymentMonth;
        temp = Double.doubleToLongBits(superRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentStartDate() {
        return paymentStartDate;
    }

    public void setPaymentStartDate(String paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public int getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(int paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public double getSuperRate() {
        return superRate;
    }

    public void setSuperRate(double superRate) {
        this.superRate = superRate;
    }
}

