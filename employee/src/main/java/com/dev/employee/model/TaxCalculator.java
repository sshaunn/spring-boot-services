package com.dev.employee.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaxCalculator {

    public static List<TaxThreshold> taxThresholds = new ArrayList<>(Arrays.asList(
            new TaxThreshold(BigDecimal.valueOf(180000), BigDecimal.valueOf(54232), BigDecimal.valueOf(0.45)),
            new TaxThreshold(BigDecimal.valueOf(87000), BigDecimal.valueOf(19822), BigDecimal.valueOf(0.37)),
            new TaxThreshold(BigDecimal.valueOf(37000), BigDecimal.valueOf(3572), BigDecimal.valueOf(0.325)),
            new TaxThreshold(BigDecimal.valueOf(18200), BigDecimal.valueOf(0), BigDecimal.valueOf(0.19)),
            new TaxThreshold(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0))
    ));

    public static Optional<BigDecimal> calculateTax(BigDecimal annualSalary) {
        Optional<BigDecimal> tax = taxThresholds.stream()
                .map(taxThreshold -> taxThreshold.calculateTax(annualSalary))
                .filter(t -> t != null)
                .findFirst();
        return tax;
    }

}
