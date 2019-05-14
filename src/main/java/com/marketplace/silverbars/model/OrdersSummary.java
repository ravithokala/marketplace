package com.marketplace.silverbars.model;


import java.util.List;

public class OrdersSummary {

    private final List<SummaryItem> sellOrders;
    private final List<SummaryItem> buyOrders;

    public OrdersSummary(List<SummaryItem> sellOrders, List<SummaryItem> buyOrders) {
        this.sellOrders = sellOrders;
        this.buyOrders = buyOrders;
    }

    public List<SummaryItem> getSellOrders() {
        return sellOrders;
    }

    public List<SummaryItem> getBuyOrders() {
        return buyOrders;
    }
}