package com.dev.income.model;

public class TaxTable {

    private String id;
    private Double taxInit, taxForEachDollar, taxThreshold;

    public TaxTable() {
    }

    public TaxTable(String id, Double taxInit, Double taxForEachDollar, Double taxThreshold) {
        this.id = id;
        this.taxInit = taxInit;
        this.taxForEachDollar = taxForEachDollar;
        this.taxThreshold = taxThreshold;
    }

    @Override
    public String toString() {
        return "TaxTable{" +
                "id='" + id + '\'' +
                ", taxInit=" + taxInit +
                ", taxForEachDollar=" + taxForEachDollar +
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

    public Double gettaxForEachDollar() {
        return taxForEachDollar;
    }

    public void settaxForEachDollar(Double taxForEachDollar) {
        this.taxForEachDollar = taxForEachDollar;
    }

    public Double getTaxThreshold() {
        return taxThreshold;
    }

    public void setTaxThreshold(Double taxThreshold) {
        this.taxThreshold = taxThreshold;
    }
}
