package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DiscountTest {
    @Test
    public void getDiscount_one() {
        Discount dis = new Discount(WarehouseObjectType.STONE);
        SupplyContainer result = dis.getDiscount();
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 1, 0, 0};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getStatus(){
        Discount dis = new Discount(WarehouseObjectType.SERVANT);
        ArrayList<Integer> result = new ArrayList<>(dis.getStatus());
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