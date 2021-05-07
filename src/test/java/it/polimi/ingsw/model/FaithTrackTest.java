package it.polimi.ingsw.model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FaithTrackTest {

    @Test
    public void goAhead_noVaticanReport(){
        FaithTrack fTrack = new FaithTrack();
        assertFalse(fTrack.goAhead(3));
    }

    @Test
    public void goAhead_callVaticanReport(){
        FaithTrack fTrack = new FaithTrack();
        assertTrue(fTrack.goAhead(9));
    }

    @Test
    public void goAheadDontTrigger_true() {
        FaithTrack fTrack = new FaithTrack();
        for(int i = 0; i<7; i++)
            fTrack.goAheadDontTrigger();
        assertTrue(fTrack.goAheadDontTrigger());
    }

    @Test
    public void goAheadDontTrigger_false() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        for(int i = 0; i<7; i++) {
            fTrack1.goAheadDontTrigger();
            fTrack2.goAheadDontTrigger();
        }
        fTrack2.goAheadDontTrigger();
        assertTrue(fTrack1.goAheadDontTrigger());
    }

    @Test
    public void vaticanReport_triggeredByPlayer() {
        FaithTrack fTrack = new FaithTrack();
        fTrack.goAhead(8);
        assertEquals(4, fTrack.getWinPoints());
    }

    @Test
    public void vaticanReport_triggeredByAnotherPlayerDiscardTile() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(8);
        fTrack2.vaticanReport();     //this method is called by the Dashboard when player 1 reaches the eighth tile
        fTrack2.goAhead(12);
        assertEquals(6, fTrack2.getWinPoints());
    }

    @Test
    public void vaticanReport_triggeredByAnotherPlayerActivateTile() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(5);     //player 1 reaches vatican report section 1
        fTrack2.goAhead(8);
        fTrack1.vaticanReport();     //this method is called by the Dashboard when player 2 reaches the eighth tile
        assertEquals(3, fTrack1.getWinPoints());
    }

    @Test
    public void vaticanReport_secondSectionTriggeredByAnotherPlayerActivateTile() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(5);
        fTrack2.goAhead(8);     //player 2 triggers the first vatican report
        fTrack1.vaticanReport();     //this method is called by the Dashboard when player 2 reaches the eighth tile
        fTrack1.goAhead(9);
        fTrack2.goAhead(8);     //player 2 triggers the second vatican report
        fTrack1.vaticanReport();     //this method is called by the Dashboard when player 2 reaches the sixteenth tile
        assertEquals(11, fTrack1.getWinPoints());
    }

    @Test
    public void getWinPoints_noPopeFavorTiles() {
        FaithTrack fTrack = new FaithTrack();
        fTrack.goAhead(4);
        assertEquals(1, fTrack.getWinPoints());
    }

    @Test
    public void getWinPoints_withDiscardedPopeFavorTile() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(8);
        fTrack2.vaticanReport();     //this method is called by the Dashboard when player 1 reaches the eighth tile
        fTrack2.goAhead(10);
        assertEquals(4, fTrack2.getWinPoints());
    }

    @Test
    public void getWinPoints_withOnePopeFavorTile() {
        FaithTrack fTrack = new FaithTrack();
        fTrack.goAhead(10);
        assertEquals(6, fTrack.getWinPoints());
    }

    @Test
    public void getWinPoints_withTwoPopeFavorTiles() {
        FaithTrack fTrack = new FaithTrack();
        fTrack.goAhead(17);
        assertEquals(14, fTrack.getWinPoints());
    }

    @Test
    public void getWinPoints_withAnActiveAndADiscardedPopeFavorTile() {
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(8);
        fTrack2.vaticanReport();     //this method is called by the Dashboard when player 1 reaches the eighth tile
        fTrack2.goAhead(16);
        assertEquals(12, fTrack2.getWinPoints());
    }

    @Test
    public void getPosition(){
        FaithTrack fTrack = new FaithTrack();
        fTrack.goAhead(10);
        assertEquals(10, fTrack.getPosition());
    }

    @Test
    public void getStatus(){
        FaithTrack fTrack1 = new FaithTrack();
        FaithTrack fTrack2 = new FaithTrack();
        fTrack1.goAhead(11);
        fTrack2.vaticanReport();
        fTrack2.goAhead(17);
        fTrack1.vaticanReport();
        ArrayList<Integer> result = new ArrayList<>(fTrack1.getStatus());
        int[] expectedResult = {11, 1, 2, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3)};
        assertArrayEquals(expectedResult, actualResult);
    }
}