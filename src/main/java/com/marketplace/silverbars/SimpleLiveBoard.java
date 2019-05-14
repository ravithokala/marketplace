package com.marketplace.silverbars;


import com.marketplace.silverbars.model.OrderType;
import com.marketplace.silverbars.model.SummaryItem;
import com.marketplace.silverbars.model.Order;
import com.marketplace.silverbars.model.OrdersSummary;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class SimpleLiveBoard implements LiveBoard {

    private List<Order> allOrders = new LinkedList<>();

    @Override
    public void registerOrder(String userId, double quantityInKgs, double pricePerKg, OrderType orderType) {
        allOrders.add(new Order(userId, quantityInKgs, pricePerKg, orderType));
    }

    @Override
    public boolean cancelOrder(String userId, double quantityInKgs, double pricePerKg, OrderType orderType) {
        return allOrders.remove(new Order(userId, quantityInKgs, pricePerKg, orderType));
    }

    @Override
    public OrdersSummary getSummary() {
        List<SummaryItem> sellOrders = getOrderTypeSummary(OrderType.SELL, (o1, o2) -> Double.compare(o1.getPricePerKg(), o2.getPricePerKg()));
        List<SummaryItem> buyOrders = getOrderTypeSummary(OrderType.BUY, (o1, o2) -> Double.compare(o2.getPricePerKg(), o1.getPricePerKg()));

        return new OrdersSummary(sellOrders, buyOrders);
    }

    private List<SummaryItem> getOrderTypeSummary(OrderType orderType, Comparator<SummaryItem> comparator) {
        return allOrders.stream()
                .filter(order -> order.getOrderType() == orderType)
                .collect(groupingBy(Order::getPricePerKg, Collectors.mapping(Order::getQuantityInKgs, Collectors.summingDouble(Double::doubleValue))))
                .entrySet()
                .stream()
                .map(e -> new SummaryItem(e.getKey(), e.getValue()))
                .sorted(comparator)
                .collect(toList());
    }
}
