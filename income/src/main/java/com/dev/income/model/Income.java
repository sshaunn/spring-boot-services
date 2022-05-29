package com.dev.income.model;

import com.dev.income.orm.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Income {

    private String fromDate, toDate;
    private int grossIncome, incomeTax, superAnnuation, netIncome;
    private Employee employee;

    public Income(String fromDate, String toDate, int grossIncome, int incomeTax, int superAnnuation, int netIncome, Employee employee) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.superAnnuation = superAnnuation;
        this.netIncome = netIncome;
        this.employee = employee;
    }

    public Income(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (grossIncome != income.grossIncome) return false;
        if (incomeTax != income.incomeTax) return false;
        if (superAnnuation != income.superAnnuation) return false;
        if (netIncome != income.netIncome) return false;
        if (!fromDate.equals(income.fromDate)) return false;
        if (!toDate.equals(income.toDate)) return false;
        return employee.equals(income.employee);
    }

    @Override
    public int hashCode() {
        int result = fromDate.hashCode();
        result = 31 * result + toDate.hashCode();
        result = 31 * result + grossIncome;
        result = 31 * result + incomeTax;
        result = 31 * result + superAnnuation;
        result = 31 * result + netIncome;
        result = 31 * result + employee.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Income{" +
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superAnnuation=" + superAnnuation +
                ", netIncome=" + netIncome +
                ", employee=" + employee +
                '}';
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public int getSuperAnnuation() {
        return superAnnuation;
    }

    public void setSuperAnnuation(int superAnnuation) {
        this.superAnnuation = superAnnuation;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
