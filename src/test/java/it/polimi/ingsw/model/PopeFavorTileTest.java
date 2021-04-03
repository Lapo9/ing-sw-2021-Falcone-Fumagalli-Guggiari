package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PopeFavorTileTest {

    @Test
    public void activate_true(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.activate();
        assertTrue(pft.isActive());
    }

    @Test
    public void activate_false(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.discard();
        pft.activate();
        assertFalse(pft.isActive());
    }

    @Test
    public void getWinPoint_points(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.activate();
        assertEquals(pft.getWinPoints(), 2);
    }

    @Test
    public void getWinPoint_zero(){
        PopeFavorTile pft = new PopeFavorTile(2);
        assertEquals(pft.getWinPoints(), 0);
    }
}