package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarketplaceTest {

    @Test
    public void obtain_Horizontal() {
        ArrayList<MarbleColor> mcolor = new ArrayList<>();
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.VIOLET);
        mcolor.add(MarbleColor.VIOLET);

        Marketplace market = new Marketplace(mcolor);
        int index = 2;
        MarbleContainer mc = market.obtain(MarketDirection.HORIZONTAL, index);
        int [] expectedObject = {0, 0, 0, 2, 2, 0};
        int [] actualObject = {
                mc.getQuantity(MarbleColor.YELLOW),
                mc.getQuantity(MarbleColor.BLUE),
                mc.getQuantity(MarbleColor.GREY),
                mc.getQuantity(MarbleColor.WHITE),
                mc.getQuantity(MarbleColor.VIOLET),
                mc.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject, actualObject);

        //after shifting
        int index2 = 2;
        MarbleContainer mc2 = market.obtain(MarketDirection.HORIZONTAL, index2);
        int [] expectedObject2 = {0, 0, 0, 1, 2, 1};
        int [] actualObject2 = {
                mc2.getQuantity(MarbleColor.YELLOW),
                mc2.getQuantity(MarbleColor.BLUE),
                mc2.getQuantity(MarbleColor.GREY),
                mc2.getQuantity(MarbleColor.WHITE),
                mc2.getQuantity(MarbleColor.VIOLET),
                mc2.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject2, actualObject2);

        //another one shift
        int index3 = 2;
        MarbleContainer mc3 = market.obtain(MarketDirection.HORIZONTAL, index3);
        int [] expectedObject3 = {0, 0, 0, 1, 2, 1};
        int [] actualObject3 = {
                mc3.getQuantity(MarbleColor.YELLOW),
                mc3.getQuantity(MarbleColor.BLUE),
                mc3.getQuantity(MarbleColor.GREY),
                mc3.getQuantity(MarbleColor.WHITE),
                mc3.getQuantity(MarbleColor.VIOLET),
                mc3.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject3, actualObject3);
    }

    @Test
    public void obtain_Vertical() {
        ArrayList<MarbleColor> mcolor = new ArrayList<>();
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.VIOLET);
        mcolor.add(MarbleColor.VIOLET);

        Marketplace market = new Marketplace(mcolor);
        int index = 3;
        MarbleContainer mc = market.obtain(MarketDirection.VERTICAL, index);
        int [] expectedObject = {0, 1, 0, 1, 1, 0};
        int [] actualObject = {
                mc.getQuantity(MarbleColor.YELLOW),
                mc.getQuantity(MarbleColor.BLUE),
                mc.getQuantity(MarbleColor.GREY),
                mc.getQuantity(MarbleColor.WHITE),
                mc.getQuantity(MarbleColor.VIOLET),
                mc.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject, actualObject);

        //after shifting
        int index2 = 3;
        MarbleContainer mc2 = market.obtain(MarketDirection.VERTICAL, index2);
        int [] expectedObject2 = {0, 0, 0, 1, 1, 1};
        int [] actualObject2 = {
                mc2.getQuantity(MarbleColor.YELLOW),
                mc2.getQuantity(MarbleColor.BLUE),
                mc2.getQuantity(MarbleColor.GREY),
                mc2.getQuantity(MarbleColor.WHITE),
                mc2.getQuantity(MarbleColor.VIOLET),
                mc2.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject2, actualObject2);

        //another one shift
        int index3 = 3;
        MarbleContainer mc3 = market.obtain(MarketDirection.VERTICAL, index3);
        int [] expectedObject3 = {0, 1, 0, 0, 1, 1};
        int [] actualObject3 = {
                mc3.getQuantity(MarbleColor.YELLOW),
                mc3.getQuantity(MarbleColor.BLUE),
                mc3.getQuantity(MarbleColor.GREY),
                mc3.getQuantity(MarbleColor.WHITE),
                mc3.getQuantity(MarbleColor.VIOLET),
                mc3.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject3, actualObject3);


        int index4 = 3;
        MarbleContainer mc4 = market.obtain(MarketDirection.VERTICAL, index4);
        int [] expectedObject4 = {0, 1, 0, 1, 0, 1};
        int [] actualObject4 = {
                mc4.getQuantity(MarbleColor.YELLOW),
                mc4.getQuantity(MarbleColor.BLUE),
                mc4.getQuantity(MarbleColor.GREY),
                mc4.getQuantity(MarbleColor.WHITE),
                mc4.getQuantity(MarbleColor.VIOLET),
                mc4.getQuantity(MarbleColor.RED)
        };
        assertArrayEquals(expectedObject4, actualObject4);
    }

    @Test
    public void getStatus() {
        ArrayList<MarbleColor> mcolor = new ArrayList<>();
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.YELLOW);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.BLUE);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.GREY);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.WHITE);
        mcolor.add(MarbleColor.VIOLET);
        mcolor.add(MarbleColor.VIOLET);
        Marketplace market = new Marketplace(mcolor);

        ArrayList<Integer> status = new ArrayList<>(market.getStatus());
        int[] expectedResult = {5, 5, 0, 0,
                                1, 1, 4, 4,
                                4, 4, 3, 3,
                                2};
        int[] actualResult= {status.get(0), status.get(1), status.get(2), status.get(3),
                             status.get(4), status.get(5), status.get(6), status.get(7),
                             status.get(8), status.get(9), status.get(10), status.get(11),
                             status.get(12)};
        assertArrayEquals(expectedResult, actualResult);
    }
}