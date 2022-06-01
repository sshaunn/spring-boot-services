package com.dev.income.model;

public class TaxTable {

    private String id;
    private Double taxInit, taxForEach, taxThreshold;

    public TaxTable() {
    }

    public TaxTable(String id, Double taxInit, Double taxForEach, Double taxThreshold) {
        this.id = id;
        this.taxInit = taxInit;
        this.taxForEach = taxForEach;
        this.taxThreshold = taxThreshold;
    }

    @Override
    public String toString() {
        return "TaxTable{" +
                "id='" + id + '\'' +
                ", taxInit=" + taxInit +
                ", taxForEach=" + taxForEach +
                ", taxThreshold=" + taxThreshold +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTaxInit() {
        return taxInit;
    }

    public void setTaxInit(Double taxInit) {
        this.taxInit = taxInit;
    }

    public Double getTaxForEach() {
        return taxForEach;
    }

    public void setTaxForEach(Double taxForEach) {
        this.taxForEach = taxForEach;
    }

    public Double getTaxThreshold() {
        return taxThreshold;
    }

    public void setTaxThreshold(Double taxThreshold) {
        this.taxThreshold = taxThreshold;
    }
}
