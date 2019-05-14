package com.marketplace.silverbars;

import com.marketplace.silverbars.model.OrderType;
import com.marketplace.silverbars.model.OrdersSummary;
import com.marketplace.silverbars.model.SummaryItem;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

public class SimpleLiveBoardTest {

    public SimpleLiveBoard simpleLiveBoard = new SimpleLiveBoard();

    @Test
    public void testZeroOrders() {
        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), hasSize(0));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }

    @Test
    public void testSingleSellOrder() {
        simpleLiveBoard.registerOrder("rthokala", 2.0, 300.0, OrderType.SELL);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), IsIterableContainingInOrder.contains(new SummaryItem(300.0, 2.0)));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }

    @Test
    public void testSortingInSellOrderForMultipleOrderAtDifferentPrices() {
        simpleLiveBoard.registerOrder("rthokala", 3.0, 350.0, OrderType.SELL);
        simpleLiveBoard.registerOrder("rsankar", 4.0, 450.0, OrderType.SELL);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), contains(new SummaryItem(350.0, 3.0), new SummaryItem(450.0, 4.0)));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }

    @Test
    public void testGroupingForSamePriceForSellOrders() {
        simpleLiveBoard.registerOrder("rthokala", 1.2, 250.0, OrderType.SELL);
        simpleLiveBoard.registerOrder("rsankar", 4.3, 250.0, OrderType.SELL);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), contains(new SummaryItem(250.0, 5.5)));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }

    @Test
    public void testCancellingTheSellOrder() {
        simpleLiveBoard.registerOrder("rthokala", 1.20, 520.0, OrderType.SELL);

        assertTrue(simpleLiveBoard.cancelOrder("rthokala", 1.20, 520.0, OrderType.SELL));

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), hasSize(0));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }

    @Test
    public void testSingleBuyOrder() {
        simpleLiveBoard.registerOrder("rthokala", 2.0, 300.0, OrderType.BUY);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getBuyOrders(), contains(new SummaryItem(300.0, 2.0)));
        assertThat(summary.getSellOrders(), hasSize(0));
    }

    @Test
    public void testSortingInBuyOrdersForMultipleOrderAtDifferentPrices() {
        simpleLiveBoard.registerOrder("rthokala", 5.0, 200.0, OrderType.BUY);
        simpleLiveBoard.registerOrder("rsankar", 6.0, 400.0, OrderType.BUY);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getBuyOrders(), contains(new SummaryItem(400.0, 6.0), new SummaryItem(200.0, 5.0)));
        assertThat(summary.getSellOrders(), hasSize(0));
    }

    @Test
    public void testGroupingForSamePriceForBuyOrders() {
        simpleLiveBoard.registerOrder("rthokala", 2.1, 220.0, OrderType.BUY);
        simpleLiveBoard.registerOrder("rsankar", 3.4, 220.0, OrderType.BUY);

        OrdersSummary summary = simpleLiveBoard.getSummary();
        assertThat(summary.getBuyOrders(), contains(new SummaryItem(220, 5.5)));
        assertThat(summary.getSellOrders(), hasSize(0));
    }

    @Test
    public void testCancellingTheBuyOrder() {
        simpleLiveBoard.registerOrder("rthokala", 1.30, 540.0, OrderType.BUY);

        assertTrue(simpleLiveBoard.cancelOrder("rthokala", 1.30, 540.0, OrderType.BUY));

        OrdersSummary summary = simpleLiveBoard.getSummary();
        assertThat(summary.getSellOrders(), hasSize(0));
        assertThat(summary.getBuyOrders(), hasSize(0));
    }


    @Test
    public void testSummaryForBothSellAndBuyOrders() {
        simpleLiveBoard.registerOrder("user1", 1.30, 540.0, OrderType.SELL);
        simpleLiveBoard.registerOrder("user2", 4.60, 678.0, OrderType.BUY);

        OrdersSummary summary = simpleLiveBoard.getSummary();

        assertThat(summary.getSellOrders(), contains(new SummaryItem(540.0, 1.3)));
        assertThat(summary.getBuyOrders(), contains(new SummaryItem(678, 4.6)));
    }

    @Test
    public void testCancellingNonExistingOrder() {
        simpleLiveBoard.registerOrder("user1", 1.30, 540.0, OrderType.SELL);
        assertFalse(simpleLiveBoard.cancelOrder("user2", 1.30, 540.0, OrderType.SELL));

    }

}