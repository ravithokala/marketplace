package com.marketplace.silverbars;

import com.marketplace.silverbars.model.OrderType;
import com.marketplace.silverbars.model.OrdersSummary;

public interface LiveBoard {

    /**
     * Registers the order
     *
     * @param userId
     * @param quantityInKgs
     * @param pricePerKg
     * @param orderType
     */
    void registerOrder(String userId, double quantityInKgs, double pricePerKg, OrderType orderType);

    /**
     * @param userId
     * @param quantityInKgs
     * @param pricePerKg
     * @param orderType
     * @returns true if existing order id successfully cancelled, false if order doesn't exist
     */
    boolean cancelOrder(String userId, double quantityInKgs, double pricePerKg, OrderType orderType);

    /**
     * @returns both sell and buy summary
     */
    OrdersSummary getSummary();
}
