package com.marketplace.silverbars.model;


public class Order {

    private final String userId;

    private final double quantityInKgs;

    private final double pricePerKg;

    private final OrderType orderType;

    public Order(String userId, double quantityInKgs,
                 double pricePerKg, OrderType orderType) {
        this.userId = userId;
        this.quantityInKgs = quantityInKgs;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public double getQuantityInKgs() {
        return quantityInKgs;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.quantityInKgs, quantityInKgs) != 0) return false;
        if (Double.compare(order.pricePerKg, pricePerKg) != 0) return false;
        if (userId != null ? !userId.equals(order.userId) : order.userId != null) return false;
        return orderType == order.orderType;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId != null ? userId.hashCode() : 0;
        temp = Double.doubleToLongBits(quantityInKgs);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerKg);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        return result;
    }
}
