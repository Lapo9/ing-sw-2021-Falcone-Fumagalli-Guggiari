package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductionManagerTest {

    @Test
    public void addSupply() {
    }

    @Test
    public void removeSupply() {
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
    public void clearSupplies() {
    }

    @Test
    public void produce() {
    }

    @Test
    public void checkProduction() {
    }
}