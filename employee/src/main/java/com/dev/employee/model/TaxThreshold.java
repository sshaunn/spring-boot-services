package com.dev.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxThreshold {

    private BigDecimal taxThreshold, fixedTax, variableTax;

    public BigDecimal calculateTax(BigDecimal annualSalary) {
        BigDecimal tax = null;
        if (annualSalary.compareTo(taxThreshold) > 0) {
            tax = ((annualSalary.subtract(taxThreshold)).multiply(variableTax))
                    .add(fixedTax)
                    .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
        }
        return tax;
    }
}
