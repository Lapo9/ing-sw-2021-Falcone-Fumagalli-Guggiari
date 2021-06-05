package it.polimi.ingsw.model.match_items;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.development.Paycheck;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.NoSuchCardException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Discount;
import it.polimi.ingsw.model.leaders.leader_abilities.Market;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DevelopmentGridTest {

    @Test
    public void developmentGrid_classConstructor() {
        int[] grid = {33, 37, 41, 45,
                      17, 21, 25, 29,
                       3,  5, 10,  0,
                      34, 38, 42, 46,
                      18, 22, 26, 30,
                       4,  6, 11,  0,
                      35, 39, 43, 47,
                      19, 23, 27, 31,
                       0,  7, 12,  0,
                      36, 40, 44, 48,
                      20, 24, 28, 32,
                       0,  8,  0,  0};
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(grid);

        ArrayList<Integer> status = new ArrayList<>(dvlpgrd.getStatus());
        int[] expectedResult = {33, 37, 41, 45,
                                17, 21, 25, 29,
                                 3,  5, 10,  0,
                                34, 38, 42, 46,
                                18, 22, 26, 30,
                                 4,  6, 11,  0,
                                35, 39, 43, 47,
                                19, 23, 27, 31,
                                 0,  7, 12,  0,
                                36, 40, 44, 48,
                                20, 24, 28, 32,
                                 0,  8,  0,  0};
        int[] actualResult = {status.get(0), status.get(1), status.get(2),status.get(3),
                status.get(4), status.get(5), status.get(6), status.get(7),
                status.get(8), status.get(9), status.get(10), status.get(11),
                status.get(12), status.get(13), status.get(14), status.get(15),
                status.get(16), status.get(17), status.get(18), status.get(19),
                status.get(20), status.get(21), status.get(22), status.get(23),
                status.get(24), status.get(25), status.get(26), status.get(27),
                status.get(28), status.get(29), status.get(30), status.get(31),
                status.get(32), status.get(33), status.get(34), status.get(35),
                status.get(36), status.get(37), status.get(38), status.get(39),
                status.get(40), status.get(41), status.get(42), status.get(43),
                status.get(44), status.get(45), status.get(46), status.get(47)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void buyCard_lvlOneCardNoLeader() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        DevelopmentCard card = null;
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            card = dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        assertEquals(new Integer(5), card.getStatus().get(0));
    }

    @Test
    public void buyCard_lvlTwoCardNoLeader() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        DevelopmentCard card = null;
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        try {
            card = dvlpgrd.buyCard(3, 1, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        assertEquals(new Integer(29), card.getStatus().get(0));
    }

    @Test
    public void buyCard_lvlThreeCardNoLeader() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        DevelopmentCard card = null;
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            pychck.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        try {
            card = dvlpgrd.buyCard(0, 0, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        assertEquals(new Integer(33), card.getStatus().get(0));
    }

    @Test
    public void buyCard_withLeaderDiscount() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        DevelopmentCard card = null;
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Discount(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(new Warehouse(), ldrspc), new SupplyContainer(2, 0, 0, 0, 0), new Developments()));
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            card = dvlpgrd.buyCard(2, 1, pychck, ldrspc);
        } catch (SupplyException e){fail();} catch( NoSuchCardException e){fail();}
        assertEquals(new Integer(25), card.getStatus().get(0));
    }

    @Test
    public void buyCard_emptyPositionEx() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        boolean exc = false;
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException e) {fail();
        } catch (NoSuchCardException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void buyCard_wrongPaycheckEx() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        boolean exc = false;
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(3, 2, pychck, ldrspc);
        } catch (SupplyException e){
            exc = true;
        } catch (NoSuchCardException e){fail();}
        assertTrue(exc);
    }

    @Test
    public void removeCard_noEx() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        try {
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.GREEN);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.GREEN);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.GREEN);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.GREEN);
        } catch (NoSuchCardException e) {fail();}
        boolean exc = false;
        try {
            dvlpgrd.getLevel(0, 2);
        } catch (NoSuchCardException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeCard_emptyColorEx() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        try {
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
        } catch (NoSuchCardException e) {fail();}
        boolean exc = false;
        try {
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.BLUE);
        } catch (NoSuchCardException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void getBoughtCards() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        DevelopmentCard card = null;
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        assertEquals(2, dvlpgrd.getBoughtCards());
    }

    @Test
    public void getLevel_one() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        int lvl = 0;
        try {
            lvl = dvlpgrd.getLevel(1, 2);
        } catch (NoSuchCardException e) {fail();}
        assertEquals(1, lvl);
    }

    @Test
    public void getLevel_two() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        int lvl = 0;
        try {
            lvl = dvlpgrd.getLevel(2, 1);
        } catch (NoSuchCardException e) {fail();}
        assertEquals(2, lvl);
    }

    @Test
    public void getLevel_three() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        int lvl = 0;
        try {
            lvl = dvlpgrd.getLevel(0, 0);
        } catch (NoSuchCardException e) {fail();}
        assertEquals(3, lvl);
    }

    @Test
    public void getLevel_emptyEx() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(false);
        Paycheck pychck = new Paycheck();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        pychck = new Paycheck();
        try {
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            pychck.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpgrd.buyCard(1, 2, pychck, ldrspc);
        } catch (SupplyException | NoSuchCardException e){fail();}
        boolean exc = false;
        try {
            dvlpgrd.getLevel(1, 2);
        } catch (NoSuchCardException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void getStatus() {
        DevelopmentGrid dvlpgrd = new DevelopmentGrid(true);
        try {
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.GREEN);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.YELLOW);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.YELLOW);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.VIOLET);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.VIOLET);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.VIOLET);
            dvlpgrd.removeCard(ActionTilesStack.ActionTile.VIOLET);
        } catch (NoSuchCardException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dvlpgrd.getStatus());
        int[] expectedResult = {33, 37, 41, 45,
                                17, 21, 25, 29,
                                 2,  5, 11,  0,
                                34, 38, 42, 46,
                                18, 22, 26, 30,
                                 3,  6, 12,  0,
                                35, 39, 43, 47,
                                19, 23, 27, 31,
                                 4,  7,  0,  0,
                                36, 40, 44, 48,
                                20, 24, 28, 32,
                                 0,  8,  0,  0};
        int[] actualResult = {status.get(0), status.get(1), status.get(2),status.get(3),
                              status.get(4), status.get(5), status.get(6), status.get(7),
                              status.get(8), status.get(9), status.get(10), status.get(11),
                              status.get(12), status.get(13), status.get(14), status.get(15),
                              status.get(16), status.get(17), status.get(18), status.get(19),
                              status.get(20), status.get(21), status.get(22), status.get(23),
                              status.get(24), status.get(25), status.get(26), status.get(27),
                              status.get(28), status.get(29), status.get(30), status.get(31),
                              status.get(32), status.get(33), status.get(34), status.get(35),
                              status.get(36), status.get(37), status.get(38), status.get(39),
                              status.get(40), status.get(41), status.get(42), status.get(43),
                              status.get(44), status.get(45), status.get(46), status.get(47)};
        assertArrayEquals(expectedResult, actualResult);
    }
}