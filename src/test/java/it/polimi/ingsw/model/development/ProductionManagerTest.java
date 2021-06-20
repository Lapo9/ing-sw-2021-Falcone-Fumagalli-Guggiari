package it.polimi.ingsw.model.development;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.exceptions.DevelopmentException;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.model.DepotID.DEVELOPMENT2;
import static it.polimi.ingsw.model.DepotID.LEADER1;
import static org.junit.Assert.*;

public class ProductionManagerTest {

    @Test
    public void addSupply_development() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.LEADER1);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_development() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_production() {
        MutableProduction prod = new MutableProduction(2, 1);
        ProductionManager prdmng = new ProductionManager(new Developments(), prod , new LeadersSpace());
        try {
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN, DEVELOPMENT2);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> status = prod.getStatus();
        int[] expected = {0, 0, 0, 0, 0};
        int[] actual = {status.get(13), status.get(14),status.get(15),status.get(16),status.get(17)};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeSupply_leader() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        boolean exc = false;
        try {
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.removeSupply(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void additionAllowed_development() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        assertTrue(prdmng.additionAllowed(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION));
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 5));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 2, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        assertTrue(prdmng.additionAllowed(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void additionAllowed_wrongIDFalse(){
        ProductionManager prdmng =  new ProductionManager(new Developments(), new MutableProduction(2, 1), new LeadersSpace());
        assertFalse(prdmng.additionAllowed(DepotID.COFFER, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION));
    }

    @Test
    public void removalAllowed_development() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 5));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 2, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertTrue(prdmng.removalAllowed(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1));
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
        assertTrue(prdmng.removalAllowed(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.LEADER1));
    }

    @Test
    public void removalAllowed_leaderCard() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 5));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 2, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertTrue(prdmng.removalAllowed(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.LEADER2));
    }

    @Test
    public void removalAllowed_wrongIDFalse() {
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(6));
        } catch (Exception e){
            fail();
        }

        ProductionManager prdmng = new ProductionManager(new Developments(), new MutableProduction(2, 1), ldrspc);
        assertFalse(prdmng.removalAllowed(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.COFFER));
    }

    @Test
    public void clearSupplies_fromDevelopment() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.DEVELOPMENT1));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {1, 0, 0, 1, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_fromProduction() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = null;
        try {
            result = new Pair<>(prdmng.clearSupplies(DepotID.LEADER1));
        } catch (NoSuchMethodException e) {fail();}
        SupplyContainer totalResources = new SupplyContainer(result.first.sum(result.second));
        int[] expectedResult = {0, 1, 0, 0, 0};
        int[] actualResult = {totalResources.getQuantity(WarehouseObjectType.COIN),
                              totalResources.getQuantity(WarehouseObjectType.STONE),
                              totalResources.getQuantity(WarehouseObjectType.SHIELD),
                              totalResources.getQuantity(WarehouseObjectType.SERVANT),
                              totalResources.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_fromAll() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(1, 1, 0, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 3));
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 5));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 2, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.COIN);
            prdmng.swapBaseProduction(1, WarehouseObjectType.STONE);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
            prdmng.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.STONE, DepotID.COFFER);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = new Pair<>(prdmng.clearSupplies());
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
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
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = new SupplyContainer(prdmng.produce(false, false, false, true, false, true));
        int[] expectedResult = {1, 0, 1, 0, 1};
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
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
            prdmng.addSupply(DepotID.LEADER1, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            prdmng.checkProduction(false, false, false, true, false, true);
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse(ldrspc);
        Developments dvlpmts = new Developments();
        try {
            dvlpmts.addCardToSpace(0, new DevelopmentCard(24, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Producer(new SupplyContainer(0, 1, 0, 0, 0)), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(wrhs, ldrspc), new SupplyContainer(2, 0, 0, 0, 0), dvlpmts));
        } catch (SupplyException | LeaderException e) {fail();}
        ProductionManager prdmng = new ProductionManager(dvlpmts, new MutableProduction(2, 1), ldrspc);
        try {
            prdmng.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            prdmng.swapBaseProduction(1, WarehouseObjectType.COIN);
            prdmng.swapBaseProduction(2, WarehouseObjectType.SERVANT);
            prdmng.swapLeaderProduction(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<DepotID> result= new ArrayList<DepotID>(prdmng.getAllowedDepots(DepotID.WAREHOUSE3, WarehouseObjectType.COIN));
        int[] expectedResult = {1, 0, 0, 1, 1, 0};
        int[] actualResult = {result.contains(DepotID.DEVELOPMENT1) ? 1 : 0,
                              result.contains(DepotID.DEVELOPMENT2) ? 1 : 0,
                              result.contains(DepotID.DEVELOPMENT3) ? 1 : 0,
                              result.contains(DepotID.BASE_PRODUCTION) ? 1 : 0,
                              result.contains(DepotID.LEADER1) ? 1 : 0,
                              result.contains(DepotID.LEADER2) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }
}