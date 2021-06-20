package it.polimi.ingsw.model;

import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.MarbleException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Market;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SupplyContainerTest {

    @Test
    public void getQuantity_one() {
        SupplyContainer sc = new SupplyContainer(2, 0, 3, 14, 9);
        int res = sc.getQuantity(WarehouseObjectType.SHIELD);
        assertEquals(14, res);
    }

    @Test
    public void getQuantity_multiple() {
        SupplyContainer sc = new SupplyContainer(2, 0, 3, 14, 9);
        int res = sc.getQuantity(WarehouseObjectType.SHIELD, WarehouseObjectType.COIN, WarehouseObjectType.STONE);
        assertEquals(16, res);
    }

    @Test
    public void getQuantity_all() {
        SupplyContainer sc = new SupplyContainer(6, 2, 0, 1, 9);
        int res = sc.getQuantity();
        assertEquals(18, res);
    }

    @Test
    public void getType_emptyContainer(){
        SupplyContainer sc = new SupplyContainer();
        WarehouseObjectType result = null;
        try {
            result = sc.getType();
        } catch (SupplyException e) {fail();}
        assertEquals(WarehouseObjectType.NO_TYPE, result);
    }

    @Test
    public void getType_noException(){
        SupplyContainer sc = new SupplyContainer(0, 2, 0, 0, 0);
        WarehouseObjectType result = null;
        try {
            result = sc.getType();
        } catch (SupplyException e) {fail();}
        assertEquals(WarehouseObjectType.STONE, result);
    }

    @Test
    public void getType_moreThanOneTypeEx(){
        SupplyContainer sc = new SupplyContainer(0, 2, 0, 1, 0);
        WarehouseObjectType result = null;
        boolean exc = false;
        try {
            result = sc.getType();
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void setAcceptCheck(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()));
        boolean exc = false;
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {
            exc = true;
        }
        sc.setAcceptCheck(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT, new LeadersSpace()));
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void setRemoveCheck(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()), SupplyContainer.AcceptStrategy.specificType(WarehouseObjectType.SHIELD));
        boolean exc = false;
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            sc.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        try {
            sc.removeSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {
            exc = true;
        }
        sc.setRemoveCheck(SupplyContainer.AcceptStrategy.any());
        try {
            sc.removeSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void sum() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(1, 0, 6, 2, 0);

        int[] objectsExpected = {   sc1.getQuantity(WarehouseObjectType.COIN) +         sc2.getQuantity(WarehouseObjectType.COIN),
                                    sc1.getQuantity(WarehouseObjectType.STONE) +        sc2.getQuantity(WarehouseObjectType.STONE),
                                    sc1.getQuantity(WarehouseObjectType.SERVANT) +      sc2.getQuantity(WarehouseObjectType.SERVANT),
                                    sc1.getQuantity(WarehouseObjectType.SHIELD) +       sc2.getQuantity(WarehouseObjectType.SHIELD),
                                    sc1.getQuantity(WarehouseObjectType.FAITH_MARKER) + sc2.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        sc1.sum(sc2);

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                 sc1.getQuantity(WarehouseObjectType.STONE),
                                 sc1.getQuantity(WarehouseObjectType.SERVANT),
                                 sc1.getQuantity(WarehouseObjectType.SHIELD),
                                 sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void equals_true() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean res = sc1.equals(sc2);
        assertTrue(res);
    }

    @Test
    public void equals_false() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 0, 2);
        boolean res = sc1.equals(sc2);
        assertFalse(res);
    }

    @Test
    public void addSupply_cofferTesting() {
        SupplyContainer coffer = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()).and(SupplyContainer.AcceptStrategy.specificType(WarehouseObjectType.FAITH_MARKER).negate()));
        boolean exc = false;
        try {
            coffer.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_oneNoEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        try {
            sc1.addSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 8, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_multipleNoEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.addSupply(WarehouseObjectType.SERVANT);
            sc1.addSupply(WarehouseObjectType.FAITH_MARKER);
            sc1.addSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 9, 2, 1};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_notAllowedEx() {
        SupplyContainer sc1 = new SupplyContainer(SupplyContainer.AcceptStrategy.specificType(WarehouseObjectType.SHIELD));
        boolean exc = false;
        try {
            sc1.addSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_sourceSpecifiedNoEx(){
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0, SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()));
        try {
            sc1.addSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 8, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                sc1.getQuantity(WarehouseObjectType.STONE),
                sc1.getQuantity(WarehouseObjectType.SERVANT),
                sc1.getQuantity(WarehouseObjectType.SHIELD),
                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_sourceSpecifiedWrongSourceEx(){
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0, SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()));
        boolean exc = false;
        try {
            sc1.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (Exception e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_oneNoEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.removeSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 6, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_multipleNoEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.removeSupply(WarehouseObjectType.SERVANT);
            sc1.removeSupply(WarehouseObjectType.COIN);
            sc1.removeSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {2, 7, 5, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_oneEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean exc = false;

        try {
            sc1.removeSupply(WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException se) {exc = true;}

        assertTrue(exc);
    }

    @Test
    public void removeSupply_multipleEx() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean exc = false;

        try {
            sc1.removeSupply(WarehouseObjectType.SHIELD);
            sc1.removeSupply(WarehouseObjectType.SHIELD);
            sc1.removeSupply(WarehouseObjectType.STONE);
        } catch (Exception e) {fail();}

        try {
            sc1.removeSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException se) {exc = true;}

        assertTrue(exc);
    }

    @Test
    public void removeSupply_withDestinationNoEx(){
        SupplyContainer sc = new SupplyContainer(2, 7, 6, 2, 0, SupplyContainer.AcceptStrategy.any(), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT, new LeadersSpace()));
        try {
            sc.removeSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (Exception e) {fail();}
        int[] objectsExpected = {2, 7, 5, 2, 0};
        int[] objectsActual = {  sc.getQuantity(WarehouseObjectType.COIN),
                                 sc.getQuantity(WarehouseObjectType.STONE),
                                 sc.getQuantity(WarehouseObjectType.SERVANT),
                                 sc.getQuantity(WarehouseObjectType.SHIELD),
                                 sc.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_withWrongDestinationEx(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.any(), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT, new LeadersSpace()));
        boolean exc = false;
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            sc.removeSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (Exception e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void additionAllowed_true(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT, new LeadersSpace()));
        assertTrue(sc.additionAllowed(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3));
    }

    @Test
    public void additionAllowed_false(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT, new LeadersSpace()));
        assertFalse(sc.additionAllowed(WarehouseObjectType.STONE, DepotID.COFFER));
    }

    @Test
    public void removalAllowed_true(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.any(), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()));
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        assertTrue(sc.removalAllowed(WarehouseObjectType.SHIELD, DepotID.COFFER));
    }

    @Test
    public void removalAllowed_false(){
        SupplyContainer sc = new SupplyContainer(SupplyContainer.AcceptStrategy.any(), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX, new LeadersSpace()));
        try {
            sc.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        assertFalse(sc.removalAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void clearSupplies() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        SupplyContainer res = sc1.clearSupplies().first;

        int[] objectsResExpected = {3, 7, 7, 2, 0};

        int[] objectsResActual = {  res.getQuantity(WarehouseObjectType.COIN),
                                    res.getQuantity(WarehouseObjectType.STONE),
                                    res.getQuantity(WarehouseObjectType.SERVANT),
                                    res.getQuantity(WarehouseObjectType.SHIELD),
                                    res.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        int[] objectsExpected = {0, 0, 0, 0, 0};

        int[] objectsActual = { sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsResExpected, objectsResActual);
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addMarble_notWhite(){
        SupplyContainer sc1 = new SupplyContainer();
        LeadersSpace ls = new LeadersSpace();
        try {
            sc1.addMarble(MarbleColor.BLUE, ls);
            sc1.addMarble(MarbleColor.YELLOW, ls);
        } catch (Exception e) {fail();}
        int[] objectsExpected = {1, 0, 0, 1, 0};
        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                 sc1.getQuantity(WarehouseObjectType.STONE),
                                 sc1.getQuantity(WarehouseObjectType.SERVANT),
                                 sc1.getQuantity(WarehouseObjectType.SHIELD),
                                 sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addMarble_white() {
        LeadersSpace ldrspc = new LeadersSpace();
        SupplyContainer sc = new SupplyContainer();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(new Warehouse(ldrspc), ldrspc), new SupplyContainer(2, 0, 0, 0, 0), new Developments()));
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            sc.addMarble(MarbleColor.WHITE, ldrspc);
        } catch (SupplyException | MarbleException e) {fail();}
        assertEquals(1, sc.getQuantity(WarehouseObjectType.STONE));
    }

    @Test
    public void getStatus(){
        SupplyContainer sc = new SupplyContainer(2, 3, 4, 0, 1);
        try {
            sc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(sc.getStatus());
        int[] expectedResult = {2, 4, 1, 3, 1};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3),
                              result.get(4)};
        assertArrayEquals(expectedResult, actualResult);
    }
}