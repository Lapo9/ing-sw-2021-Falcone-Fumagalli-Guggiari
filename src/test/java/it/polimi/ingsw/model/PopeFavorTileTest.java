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
    public void discard_noChanges(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.activate();
        pft.discard();
        assertTrue(pft.isActive());
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

    @Test
    public void getStatus_inactive(){
        PopeFavorTile pft = new PopeFavorTile(2);
        int result = pft.getStatus().get(0);
        assertEquals(0, result);
    }

    @Test
    public void getStatus_active(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.activate();
        int result = pft.getStatus().get(0);
        assertEquals(1, result);
    }

    @Test
    public void getStatus_discard(){
        PopeFavorTile pft = new PopeFavorTile(2);
        pft.discard();
        int result = pft.getStatus().get(0);
        assertEquals(2, result);
    }
}