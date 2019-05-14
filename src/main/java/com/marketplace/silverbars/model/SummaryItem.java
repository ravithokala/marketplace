package com.marketplace.silverbars.model;


public class SummaryItem {

    private final double pricePerKg;

    private final double quantityInKgs;

    public SummaryItem(double pricePerKg, double quantityInKgs) {
        this.pricePerKg = pricePerKg;
        this.quantityInKgs = quantityInKgs;
    }

    public double getQuantityInKgs() {
        return quantityInKgs;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SummaryItem that = (SummaryItem) o;

        if (Double.compare(that.pricePerKg, pricePerKg) != 0) return false;
        return Double.compare(that.quantityInKgs, quantityInKgs) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pricePerKg);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quantityInKgs);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
