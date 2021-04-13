package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

//TODO run all these tests
public class ProductionManagerTest {

    @Test
    public void addSupply_development() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void addSupply_production() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void addSupply_leader() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_development() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_production() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_leader() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.LEADER2_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.LEADER2_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void additionAllowed_development() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertTrue(prdmng.additionAllowed(DepotID.DEVELOPMENT1, WarehouseObjectType.STONE, DepotID.BASE_PRODUCTION));
    }

    @Test
    public void additionAllowed_production() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.baseProduction.swapInput(0, WarehouseObjectType.COIN);
            prdmng.baseProduction.swapInput(1, WarehouseObjectType.SHIELD);
            prdmng.baseProduction.swapOutput(0, WarehouseObjectType.STONE);
        } catch (SupplyException e) {fail();}
        assertTrue(prdmng.additionAllowed(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void additionAllowed_leaderCard() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertTrue(prdmng.additionAllowed(DepotID.LEADER2_PRODUCTION, WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void additionAllowed_wrongIDFalse(){
        ProductionManager prdmng =  new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertFalse(prdmng.additionAllowed(DepotID.COFFER, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION));
    }

    @Test
    public void removalAllowed_development() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertTrue(prdmng.removalAllowed(DepotID.DEVELOPMENT2, WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void removalAllowed_production() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.baseProduction.swapInput(0, WarehouseObjectType.SERVANT);
            prdmng.baseProduction.swapInput(1, WarehouseObjectType.SHIELD);
            prdmng.baseProduction.swapOutput(0, WarehouseObjectType.STONE);
            prdmng.baseProduction.currentSupply.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        assertTrue(prdmng.removalAllowed(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.LEADER1_DEPOT));
    }

    @Test
    public void removalAllowed_leaderCard() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertTrue(prdmng.removalAllowed(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.COIN, DepotID.LEADER2_PRODUCTION));
    }

    @Test
    public void removalAllowed_wrongIDFalse() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertTrue(prdmng.removalAllowed(DepotID.LEADER1_DEPOT, WarehouseObjectType.COIN, DepotID.COFFER));
    }

    @Test
    public void clearSupplies_fromDevelopment() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.DEVELOPMENT3));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {1, 1, 0, 0, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_fromProduction() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.BASE_PRODUCTION));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {0, 0, 2, 0, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_fromLeader() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            prdmng.addSupply(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.BASE_PRODUCTION));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {0, 0, 0, 1, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_fromAll() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.COIN);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
            prdmng.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.STONE, DepotID.COFFER);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.BASE_PRODUCTION));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {1, 1, 2, 1, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce_onlyBaseProduction() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = new SupplyContainer(prdmng.produce(false, false, false, false, false, true));
        int[] expectedResult = {0, 0, 1, 0, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce_moreThanOneProduction(){
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(0, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            //add resources to developments
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = new SupplyContainer(prdmng.produce(true, false, false, true, false, true));
        int[] expectedResult = {0, 0, 1, 0, 0};    //this needs to be changed
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void checkProduction_onlyBaseProduction() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.checkProduction(false, false, false, false, false, true);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void checkProduction_moreThanOneProduction() {
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SERVANT);

        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            //add resources to developments
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.LEADER1_PRODUCTION, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.checkProduction(true, false, false, true, false, true);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void swapBaseProduction(){
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void swapLeaderProduction(){
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        boolean exc = false;
        try {
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void getAllowedDepots(){
        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(1, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<DepotID> result= new ArrayList<DepotID>(prdmng.getAllowedDepots(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD));
        int[] expectedResult = {0, 0, 0, 1, 0, 1};
        int[] actualResult = {result.contains(DepotID.DEVELOPMENT1) ? 1 : 0,
                              result.contains(DepotID.DEVELOPMENT2) ? 1 : 0,
                              result.contains(DepotID.DEVELOPMENT3) ? 1 : 0,
                              result.contains(DepotID.BASE_PRODUCTION) ? 1 : 0,
                              result.contains(DepotID.LEADER1_PRODUCTION) ? 1 : 0,
                              result.contains(DepotID.LEADER2_PRODUCTION) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }
}