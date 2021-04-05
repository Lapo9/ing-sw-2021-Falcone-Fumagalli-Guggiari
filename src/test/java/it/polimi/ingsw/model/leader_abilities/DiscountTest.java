package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

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
    public void getDiscount_twoDifferent() {
        Discount dis = new Discount(WarehouseObjectType.COIN, WarehouseObjectType.SHIELD);
        SupplyContainer result = dis.getDiscount();
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {1, 0, 0, 1};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getDiscount_twoEquals() {
        Discount dis = new Discount(WarehouseObjectType.SERVANT, WarehouseObjectType.SERVANT);
        SupplyContainer result = dis.getDiscount();
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 0, 2, 0};
        assertArrayEquals(expectedObject, actualObject);
    }
}