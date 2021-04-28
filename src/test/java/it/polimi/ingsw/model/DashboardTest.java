package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.leader_abilities.Depot;
import it.polimi.ingsw.model.leader_abilities.Discount;
import it.polimi.ingsw.model.leader_abilities.Market;
import it.polimi.ingsw.model.leader_abilities.Producer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

//check all getStatusIndex before run
//remember to add leaderCard before doing anything che sennò non funziona nulla
public class DashboardTest {

    @Test
    public void swapWarehouseRows_noEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.swapWarehouseRows(2, 3);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                0, 0, 2, 0, 0,
                                2, 0, 0, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void swapWarehouseRows_swapForbiddenEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();
        boolean exc = false;
        try {
            dshbrd.swapWarehouseRows(2, 1);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void buySupplies_horizontal() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        Pair<Integer, Boolean> result = new Pair<>(dshbrd.discardSupplies());
        assertEquals(new Integer(4), result.first);
    }

    @Test
    public void buySupplies_vertical() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        Pair<Integer, Boolean> result = new Pair<>(dshbrd.discardSupplies());
        assertEquals(new Integer(2), result.first);  //we don't count white marble
    }

    @Test
    public void assignMarble_noEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                2, 0, 0, 0, 0,
                                0, 0, 2, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void assignMarble_withMarketAbilityNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl one yellow development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(2, 2, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy lvl one blue development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl one yellow development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.PAYCHECK, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(2, 2, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate leader card
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.swapWarehouseRows(1, 2);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.WHITE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                0, 0, 2, 0, 0,
                                0, 2, 0, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void assignMarble_noMoreMarblesEx(){
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException e) {
            exc = true;
        } catch (MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void assignMarble_destinationFullEx(){
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
        } catch (SupplyException e) {
            exc = true;
        } catch (MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void assignMarble_wrongDestinationEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.COFFER, MarbleColor.YELLOW);
        } catch (MarbleException e) {
            exc = true;
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void assignMarble_noDepotAbilityEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        cards.add(new CardsRequirement(1, 2, CardCategory.GREEN));
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(16, new SupplyContainer(), cards, new Producer(new SupplyContainer(1, 0, 0, 0, 0)), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.LEADER1_DEPOT, MarbleColor.YELLOW);
        } catch (LeaderException e) {
            exc = true;
        } catch (SupplyException | MarbleException | NoSuchMethodException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void assignMarble_noDepotAbilityActiveYetEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.LEADER1_DEPOT, MarbleColor.GREY);
        } catch (LeaderException e) {
            exc = true;
        } catch (SupplyException | MarbleException | NoSuchMethodException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void discardSupplies_noVaticanReport() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        Pair<Integer, Boolean> result = new Pair<>(dshbrd.discardSupplies());
        int[] expectedResult = {2, 0};
        int[] actualResult = {result.first, result.second? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void discardSupplies_vaticanReport() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
         for(int i = 0; i < 7; i++)
            dshbrd.goAhead();
         dshbrd.discardSupplies();
         dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        Pair<Integer, Boolean> result = new Pair<>(dshbrd.discardSupplies());
        assertTrue(result.second);
    }

    @Test
    public void moveSupply_fromWarehouseToBaseProductionNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,   //Warehouse 1
                                1, 0, 0, 0, 0,   //Warehouse 2
                                0, 0, 2, 0, 0,   //Warehouse 3
                                1, 0, 0, 0, 0};  //BaseProduction
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19),
                              status.get(97), status.get(98), status.get(99), status.get(100), status.get(101)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void moveSupply_fromWarehouseToPaycheckNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                1, 0, 0, 0, 0,
                                0, 0, 1, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void moveSupply_fromWarehouseToDevelopmentCardProductionNoEX() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.DEVELOPMENT2, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 1, 0, 0};
        int[] actualResult = {status.get(51), status.get(52), status.get(53), status.get(54), status.get(55)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void moveSupply_fromWarehouseToCofferEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.COFFER, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void moveSupply_wrongResourceTypeEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {
            fail();
        }
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {
            fail();
        }
        boolean exc = false;
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            exc= true;
        } catch ( NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void moveSupply() {
        //test all the possible movements
        //remember to test move to Paycheck
    }

    @Test
    public void produce_baseProduction() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 1, 0, 0, 0};
        int[] actualResult = {status.get(0), status.get(1), status.get(2), status.get(3), status.get(4)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce_develompentCard() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.DEVELOPMENT2, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, true, false, false, false, false);
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        assertEquals(new Integer(1), status.get(102));
    }

    @Test
    public void produce_leader() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 2, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(14, new SupplyContainer(), cards2, new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //play leadercard
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a leaderProduction
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.LEADER2_PRODUCTION, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, true, false);

        assertEquals(new Integer(4), dshbrd.getStatus().get(102));
    }

    @Test
    public void checkProduction_baseProductionNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.checkProduction(false, false, false, false, false, true);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void checkProduction_developmentCardNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.DEVELOPMENT2, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.checkProduction(false, true, false, false, false, false);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void checkProduction_leader() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 2, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(14, new SupplyContainer(), cards2, new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //play leadercard
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //check a leaderProduction
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.LEADER2_PRODUCTION, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.checkProduction(false, false, false, false, true, false);
        } catch (SupplyException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void checkProduction() {
        //Test a development production, a leader production and more than one production
    }

    @Test
    public void buyDevelopment_lvlOneNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        assertEquals(new Integer(5), status.get(38));
    }

    @Test
    public void buyDevelopment_lvlTwoNoEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 21};
        int[] actualResult = {status.get(38), status.get(39)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void buyDevelopment_wrongPlaceEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.buyDevelopment(1, 1, 0);
        } catch (SupplyException | NoSuchCardException e) {fail();
        } catch (DevelopmentException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void buyDevelopment_wrongSuppliesEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException e){
            exc = true;
        } catch  (DevelopmentException | NoSuchCardException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void buyableDevelopmentLevels_allDevelopmentsEmpty() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<Integer> list = new ArrayList<>(dshbrd.buyableDevelopmentLevels());
        int[] expectedResult = {1, 1, 1};
        int[] actualResult = {list.get(0), list.get(1), list.get(2)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void buyableDevelopmentLevel_withOneDevelopmentCard() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        ArrayList<Integer> list = new ArrayList<>(dshbrd.buyableDevelopmentLevels());
        int[] expectedResult = {1, 2, 1};
        int[] actualResult = {list.get(0), list.get(1), list.get(2)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void buyableDevelopmentLevel_withLvlTwoDevelopmentCard() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        ArrayList<Integer> list = new ArrayList<>(dshbrd.buyableDevelopmentLevels());
        int[] expectedResult = {1, 3, 1};
        int[] actualResult = {list.get(0), list.get(1), list.get(2)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void addLeader() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 0,
                                9, 0};
        int[] actualResult = {status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void discardLeader_noVaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        boolean vr = false;
        try {
            vr = dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {1, 0,
                                5, 2,
                                9, 0};
        int[] actualResult = {status.get(102), vr? 1: 0,
                              status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void discardLeader_vaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        for(int i = 0; i < 7; i++)
            dshbrd.goAhead();
        boolean vr = false;
        try {
            vr = dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {8, 1,
                                5, 2,
                                9, 0};
        int[] actualResult = {status.get(102), vr? 1: 0,
                              status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void playLeader_levelOneCardNeeded() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(2, new SupplyContainer(), cards2, new Discount(WarehouseObjectType.STONE), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 2, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 0,
                                2, 1};
        int[] actualResult = {status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void playLeader_leveTwoDevelopmentCardNeeded() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 2, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(14, new SupplyContainer(), cards2, new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //play leadercard
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 0,
                                14, 1};
        int[] actualResult = {status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void playLeader_resourcesNeeded() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true));
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 2, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(14, new SupplyContainer(), cards2, new Producer(new SupplyContainer(0, 0, 1, 0, 0)), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 1,
                                14, 0};
        int[] actualResult = {status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void goAhead_noVaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        boolean status = dshbrd.goAhead();
        int[] expectedResult = {1, 0};
        int[] actualResult = {dshbrd.getStatus().get(102), status ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void goAhead_vaticanReportTriggered() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        boolean result = false;
        for(int i = 0; i <= 8; i++)
            result |= dshbrd.goAhead();
        assertTrue(result);
    }

    @Test
    public void vaticanReport_discardPopeFavorTile() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        dshbrd.goAhead();
        dshbrd.vaticanReport();
        int[] expectedResult = {2, 0, 0};
        ArrayList<Integer> result = new ArrayList<>(dshbrd.getStatus());
        int[] actualResult = {result.get(103), result.get(104), result.get(105)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void vaticanReport_activatePopeFavorTile() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        for(int i = 0; i <= 8; i++)
            dshbrd.goAhead();
        int[] expectedResult = {1, 0, 0};
        ArrayList<Integer> result = new ArrayList<>(dshbrd.getStatus());
        int[] actualResult = {result.get(103), result.get(104), result.get(105)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void swapBaseProduction_noEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        ArrayList<CardsRequirement> cards = null;
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(9, new SupplyContainer(), cards2, new Market(WarehouseObjectType.SERVANT), 5));
        } catch (LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SERVANT);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {3, 1, 0};
        int[] actualResult = {status.get(89), status.get(90), status.get(96)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void swapBaseProduction_faithMarkerInputEx() {
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.swapBaseProduction(1, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapBaseProduction_faithMarketOutputEx (){
        ArrayList<MarbleColor> mrblclrs = new ArrayList<>();
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.YELLOW);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.BLUE);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.GREY);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.WHITE);
        mrblclrs.add(MarbleColor.VIOLET);
        mrblclrs.add(MarbleColor.VIOLET);
        Marketplace mrkt = new Marketplace(mrblclrs);
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid());
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.swapBaseProduction(2, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapLeaderProduction() {
    }

    @Test
    public void clearDepot() {
    }

    @Test
    public void clearProductions() {
    }

    @Test
    public void clearPaycheck() {
    }

    @Test
    public void hasInkwell_true() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid());
        assertTrue(dshbrd.hasInkwell());
    }

    @Test
    public void hasInkwell_false() {
        Dashboard dshbrd = new Dashboard(false, new Marketplace(), new DevelopmentGrid());
        assertFalse(dshbrd.hasInkwell());
    }

    @Test
    public void isMatchEnded() {
    }

    @Test
    public void getAllowedDepots() {
    }

    @Test
    public void extractActionTile() {
    }

    @Test
    public void getWinPoints() {
    }
}