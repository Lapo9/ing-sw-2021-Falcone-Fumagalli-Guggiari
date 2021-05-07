package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActionTilesStackTest {

    @Test
    public void reinsertAllTest() {
        ActionTilesStack ats = new ActionTilesStack();
        ActionTilesStack.ActionTile at1 = ats.extractTile();
        ActionTilesStack.ActionTile at2 = ats.extractTile();
        ats.reinsertAll();

        int [] expectedObject = {0, 1, 2, 3, 4, 5};
        int [] actualObject = {ActionTilesStack.ActionTile.GREEN.ordinal(),
                ActionTilesStack.ActionTile.BLUE.ordinal(),
                ActionTilesStack.ActionTile.YELLOW.ordinal(),
                ActionTilesStack.ActionTile.VIOLET.ordinal(),
                ActionTilesStack.ActionTile.PLUS_2.ordinal(),
                ActionTilesStack.ActionTile.PLUS_1_SHUFFLE.ordinal()
        };

        assertArrayEquals(expectedObject, actualObject);
    }
}