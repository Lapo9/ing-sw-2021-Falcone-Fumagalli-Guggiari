package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketTest {

    @Test
    public void transformWhiteMarble() {
        Market mrkt = new Market(WarehouseObjectType.SERVANT);
        assertEquals(WarehouseObjectType.SERVANT, mrkt.transformWhiteMarble());
    }
}