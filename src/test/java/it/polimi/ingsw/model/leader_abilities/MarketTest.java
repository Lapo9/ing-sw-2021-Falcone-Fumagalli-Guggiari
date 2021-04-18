package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarketTest {

    @Test
    public void transformWhiteMarble() {
        Market mrkt = new Market(WarehouseObjectType.SERVANT);
        assertEquals(WarehouseObjectType.SERVANT, mrkt.transformWhiteMarble());
    }

    @Test
    public void getStatus(){
        Market mrkt = new Market(WarehouseObjectType.SHIELD);
        ArrayList<Integer> result = new ArrayList<>(mrkt.getStatus());
        int[] expectedResult = {0,
                                0,
                                0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                              result.get(8), result.get(9), result.get(10), result.get(11), result.get(12)};
        assertArrayEquals(expectedResult, actualResult);
    }
}