package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.MarbleException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarbleContainerTest {

    @Test
    public void getQuantity_empty() {
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(0, mc.getQuantity(MarbleColor.YELLOW, MarbleColor.BLUE, MarbleColor.GREY,
                                               MarbleColor.WHITE, MarbleColor.VIOLET, MarbleColor.RED));
    }

    @Test
    public void getQuantity_oneColor() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        assertEquals(3, mc.getQuantity(MarbleColor.BLUE));
    }

    @Test
    public void getQuantity_sixColors() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        assertEquals(13, mc.getQuantity(MarbleColor.YELLOW, MarbleColor.BLUE, MarbleColor.GREY,
                                                MarbleColor.WHITE, MarbleColor.VIOLET, MarbleColor.RED));
    }

    @Test
    public void getQuantity_sixColorsSeparately() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        int[] expectedObject = {2, 3, 5, 1, 0, 2};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getQuantity_sameColorTwice() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        assertEquals(6, mc.getQuantity(MarbleColor.BLUE, MarbleColor.BLUE));
    }

    @Test
    public void removeMarble_emptyEx() {
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0, 0, 0);
        boolean exc = false;
        try {
            mc.removeMarble(MarbleColor.YELLOW);
        } catch (MarbleException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeMarble_noEx(){
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        try {
            mc.removeMarble(MarbleColor.GREY);
        } catch (MarbleException e) {
            fail();
        }
        assertEquals(4, mc.getQuantity(MarbleColor.GREY));
    }

    @Test
    public void removeMarble_removeTwoNoEx(){
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        try {
            mc.removeMarble(MarbleColor.GREY);
            mc.removeMarble(MarbleColor.WHITE);
        } catch (MarbleException e) {
            fail();
        }
        int[] expectedObject = {4, 0};
        int[] actualObject = {mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void removeMarble_removeOneFromAllNoEx(){
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        try {
            mc.removeMarble(MarbleColor.YELLOW);
            mc.removeMarble(MarbleColor.BLUE);
            mc.removeMarble(MarbleColor.GREY);
            mc.removeMarble(MarbleColor.WHITE);
            mc.removeMarble(MarbleColor.VIOLET);
            mc.removeMarble(MarbleColor.RED);

        } catch (MarbleException e) {
            fail();
        }
        int[] expectedObject = {1, 2, 4, 0, 5, 1};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void colorWhiteMarble_turnBlueNoEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        try {
            mc.colorWhiteMarble(MarbleColor.BLUE);
        } catch (MarbleException e) {fail();}
        int[] expectedObject = {2, 4, 5, 0, 6, 2};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void colorWhiteMarble_turnYellowNoEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        try {
            mc.colorWhiteMarble(MarbleColor.YELLOW);
        } catch (MarbleException e) {fail();}
        int[] expectedObject = {3, 3, 5, 0, 6, 2};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void colorWhiteMarble_turnGreyNoEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        try {
            mc.colorWhiteMarble(MarbleColor.GREY);
        } catch (MarbleException e) {fail();}
        int[] expectedObject = {2, 3, 6, 0, 6, 2};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void colorWhiteMarble_turnVioletNoEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        try {
            mc.colorWhiteMarble(MarbleColor.VIOLET);
        } catch (MarbleException e) {fail();}
        int[] expectedObject = {2, 3, 5, 0, 7, 2};
        int[] actualObject = {mc.getQuantity(MarbleColor.YELLOW),
                              mc.getQuantity(MarbleColor.BLUE),
                              mc.getQuantity(MarbleColor.GREY),
                              mc.getQuantity(MarbleColor.WHITE),
                              mc.getQuantity(MarbleColor.VIOLET),
                              mc.getQuantity(MarbleColor.RED)};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void colorWhiteMarble_noWhiteMarblesEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 0,6, 2);
        boolean exc = false;
        try {
            mc.colorWhiteMarble(MarbleColor.YELLOW);
        } catch (MarbleException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void colorWhiteMarble_turnRedEx() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,6, 2);
        boolean exc = false;
        try {
            mc.colorWhiteMarble(MarbleColor.RED);
        } catch (MarbleException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void clear() {
        MarbleContainer mc = new MarbleContainer(2, 3, 5, 1,0, 2);
        mc.clear();
        assertEquals(0, mc.getQuantity(MarbleColor.YELLOW, MarbleColor.BLUE, MarbleColor.GREY,
                                               MarbleColor.WHITE, MarbleColor.VIOLET, MarbleColor.RED));
    }

    @Test
    public void clear_alreadyEmpty() {
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        mc.clear();
        assertEquals(0, mc.getQuantity(MarbleColor.YELLOW, MarbleColor.BLUE, MarbleColor.GREY,
                                               MarbleColor.WHITE, MarbleColor.VIOLET, MarbleColor.RED));
    }

    @Test
    public void colorToSupply_yellow(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(WarehouseObjectType.COIN, MarbleContainer.colorToSupply(MarbleColor.YELLOW));
    }

    @Test
    public void colorToSupply_blue(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(WarehouseObjectType.SHIELD, MarbleContainer.colorToSupply(MarbleColor.BLUE));
    }

    @Test
    public void colorToSupply_grey(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(WarehouseObjectType.STONE, MarbleContainer.colorToSupply(MarbleColor.GREY));
    }

    @Test
    public void colorToSupply_violet(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(WarehouseObjectType.SERVANT, MarbleContainer.colorToSupply(MarbleColor.VIOLET));
    }

    @Test
    public void colorToSupply_red(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertEquals(WarehouseObjectType.FAITH_MARKER, MarbleContainer.colorToSupply(MarbleColor.RED));
    }

    @Test
    public void colorToSupply_whiteEqualsNull(){
        MarbleContainer mc = new MarbleContainer(0, 0, 0, 0,0, 0);
        assertNull(MarbleContainer.colorToSupply(MarbleColor.WHITE));
    }

    @Test
    public void getStatus(){
        MarbleContainer mc = new MarbleContainer(2, 4, 0, 1, 3, 2);
        ArrayList<Integer> result = new ArrayList<>(mc.getStatus());
        int[] expectedResult = {4, 0, 2, 3, 1, 2};
        int[] actualResult = {result.get(0), result.get(1), result.get(2), result.get(3), result.get(4), result.get(5)};
        assertArrayEquals(expectedResult, actualResult);
    }
}