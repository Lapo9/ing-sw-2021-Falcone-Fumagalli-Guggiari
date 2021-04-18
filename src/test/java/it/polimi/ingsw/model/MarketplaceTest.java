package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarketplaceTest {

    @Test
    public void obtain() {
        Marketplace market = new Marketplace(true);
        int index = 1;
        MarbleContainer mc = market.obtain(MarketDirection.HORIZONTAL, index);
        int [] expectedObject = {0, 2, 0, 0, 2, 0};
        int [] actualObject = {
                mc.getQuantity(MarbleColor.YELLOW),
                mc.getQuantity(MarbleColor.BLUE),
                mc.getQuantity(MarbleColor.WHITE),
                mc.getQuantity(MarbleColor.GREY),
                mc.getQuantity(MarbleColor.VIOLET),
                mc.getQuantity(MarbleColor.RED)
        };

        assertArrayEquals(expectedObject, actualObject);
    }
}