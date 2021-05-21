package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Discount;
import it.polimi.ingsw.model.leaders.leader_abilities.Market;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.model.leaders.CardsRequirement;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.MarketDirection;
import it.polimi.ingsw.model.match_items.Marketplace;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DashboardTest {

    @Test
    public void dashboard_classConstructor() {
        int[] status = {0,             //inkwell
                        1, 2, 0, 3, 0, //coffer
                        0, 1, 0, 0, 0, //wh1
                        0, 0, 0, 0, 0, //wh2
                        0, 0, 0, 3, 0, //wh3
                        2,             //dev 1 card 1 id
                        0,             //dev 1 card 2 id
                        0,             //dev 1 card 3 id
                        0, 0, 0, 1, 0, //dev 1 in
                        0, 1, 0, 0, 0, //dev 1 out
                        0, 0, 0, 1, 0, //dev 1 curr
                        0,             //dev 2 card 1 id
                        0,             //dev 2 card 2 id
                        0,             //dev 2 card 3 id
                        0, 0, 0, 0, 0, //dev 2 in
                        0, 0, 0, 0, 0, //dev 2 out
                        0, 0, 0, 0, 0, //dev 2 curr
                        5,             //dev 3 card 1 id
                        21,            //dev 3 card 2 id
                        0,             //dev 3 card 3 id
                        0, 1, 0, 0, 0, //dev 3 in
                        0, 0, 0, 0, 2, //dev 3 out
                        0, 0, 1, 0, 0, //dev 3 curr
                        0, 2, 0, 1, 0, //paycheck strongbox
                        1, 1, 3, 0, 0, //paycheck depots
                        0, 0, 0, 0, 0, //base in fix
                        1,             //base in mutable 1
                        2,             //base in mutable 2
                        0, 0, 0, 0, 0, //base out fix
                        3,             //base our mutable
                        0, 0, 1, 0, 0, //base current
                        13,             //faith track position
                        2,             //pope tile 1
                        1,             //pope tile 2
                        0,             //pope tile 3
                        11,             //leader 1 id
                        1,             //leader 1 state
                        3,             //leader 1 in fix
                        4,             //leader 1 out fix
                        2,             //leader 1 out mutable
                        0, 0, 1, 0, 0, //leader 1 current
                        0, 0, 0, 0, 0, //leader 1 depot
                        7,             //leader 2 id
                        1,             //leader 2 state
                        0,             //leader 2 in fix
                        0,             //leader 2 out fix
                        0,             //leader 2 out mutable
                        0, 0, 0, 0, 0, //leader 2 current
                        0, 0, 2, 0, 0, //leader 2 depot
                        1, 0, 1, 2, 0, 0,
                        2,
                        0,
                        0,
                        0,
                        0,
                        0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0,
                        11,            
                        1,             
                        3,             
                        4,             
                        2,             
                        0, 0, 1, 0, 0, 
                        0, 0, 0, 0, 0, 
                        7,             
                        1,             
                        0,             
                        0,             
                        0,             
                        0, 0, 0, 0, 0, 
                        0, 0, 2, 0, 0, 
                        15,
                        0,
                        0,
                        0,
                        0,
                        0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0};
        Dashboard dshbrd = null;
        try {
            dshbrd = new Dashboard(status, "test");
        } catch (SupplyException | DevelopmentException | LeaderException | NoSuchMethodException e) {fail();}
        ArrayList<Integer> realStatus = dshbrd.getStatus();
        int[] actualResult = {realStatus.get(0), realStatus.get(1), realStatus.get(2), realStatus.get(3), realStatus.get(4),
                              realStatus.get(5), realStatus.get(6), realStatus.get(7), realStatus.get(8), realStatus.get(9),
                              realStatus.get(10), realStatus.get(11), realStatus.get(12), realStatus.get(13), realStatus.get(14),
                              realStatus.get(15), realStatus.get(16), realStatus.get(17), realStatus.get(18), realStatus.get(19),
                              realStatus.get(20),
                              realStatus.get(21),
                              realStatus.get(22),
                              realStatus.get(23), realStatus.get(24),realStatus.get(25), realStatus.get(26), realStatus.get(27),
                              realStatus.get(28), realStatus.get(29), realStatus.get(30), realStatus.get(31), realStatus.get(32),
                              realStatus.get(33), realStatus.get(34), realStatus.get(35), realStatus.get(36), realStatus.get(37),
                              realStatus.get(38),
                              realStatus.get(39),
                              realStatus.get(40),
                              realStatus.get(41), realStatus.get(42),realStatus.get(43), realStatus.get(44), realStatus.get(45),
                              realStatus.get(46), realStatus.get(47), realStatus.get(48), realStatus.get(49), realStatus.get(50),
                              realStatus.get(51), realStatus.get(52), realStatus.get(53), realStatus.get(54), realStatus.get(55),
                              realStatus.get(56),
                              realStatus.get(57),
                              realStatus.get(58),
                              realStatus.get(59), realStatus.get(60),realStatus.get(61), realStatus.get(62), realStatus.get(63),
                              realStatus.get(64), realStatus.get(65), realStatus.get(66), realStatus.get(67), realStatus.get(68),
                              realStatus.get(69), realStatus.get(70), realStatus.get(71), realStatus.get(72), realStatus.get(73),
                              realStatus.get(74), realStatus.get(75), realStatus.get(76), realStatus.get(77), realStatus.get(78),
                              realStatus.get(79), realStatus.get(80), realStatus.get(81), realStatus.get(82), realStatus.get(83),
                              realStatus.get(84), realStatus.get(85), realStatus.get(86), realStatus.get(87), realStatus.get(88),
                              realStatus.get(89),
                              realStatus.get(90),
                              realStatus.get(91), realStatus.get(92), realStatus.get(93), realStatus.get(94), realStatus.get(95),
                              realStatus.get(96),
                              realStatus.get(97), realStatus.get(98), realStatus.get(99), realStatus.get(100), realStatus.get(101),
                              realStatus.get(102),
                              realStatus.get(103),
                              realStatus.get(104),
                              realStatus.get(105),
                              realStatus.get(106),
                              realStatus.get(107),
                              realStatus.get(108),
                              realStatus.get(109),
                              realStatus.get(110),
                              realStatus.get(111), realStatus.get(112), realStatus.get(113), realStatus.get(114), realStatus.get(115),
                              realStatus.get(116), realStatus.get(117), realStatus.get(118), realStatus.get(119), realStatus.get(120),
                              realStatus.get(121),
                              realStatus.get(122),
                              realStatus.get(123),
                              realStatus.get(124),
                              realStatus.get(125),
                              realStatus.get(126), realStatus.get(127), realStatus.get(128), realStatus.get(129), realStatus.get(130),
                              realStatus.get(131), realStatus.get(132), realStatus.get(133), realStatus.get(134), realStatus.get(135),
                              realStatus.get(136), realStatus.get(137), realStatus.get(138), realStatus.get(139), realStatus.get(140), realStatus.get(141),
                              realStatus.get(142),
                              realStatus.get(143),
                              realStatus.get(144),
                              realStatus.get(145),
                              realStatus.get(146),
                              realStatus.get(147), realStatus.get(148), realStatus.get(149), realStatus.get(150), realStatus.get(151),
                              realStatus.get(152), realStatus.get(153), realStatus.get(154), realStatus.get(155), realStatus.get(156),
                              realStatus.get(157),
                              realStatus.get(158),
                              realStatus.get(159),
                              realStatus.get(160),
                              realStatus.get(161),
                              realStatus.get(162), realStatus.get(163), realStatus.get(164), realStatus.get(165), realStatus.get(166),
                              realStatus.get(167), realStatus.get(168), realStatus.get(169), realStatus.get(170), realStatus.get(171),
                              realStatus.get(172),
                              realStatus.get(173),
                              realStatus.get(174),
                              realStatus.get(175),
                              realStatus.get(176),
                              realStatus.get(177), realStatus.get(178), realStatus.get(179), realStatus.get(180), realStatus.get(181),
                              realStatus.get(182), realStatus.get(183), realStatus.get(184), realStatus.get(185), realStatus.get(186),
                              realStatus.get(187),
                              realStatus.get(188),
                              realStatus.get(189),
                              realStatus.get(190),
                              realStatus.get(191),
                              realStatus.get(192), realStatus.get(193), realStatus.get(194), realStatus.get(195), realStatus.get(196),
                              realStatus.get(197), realStatus.get(198), realStatus.get(199), realStatus.get(200), realStatus.get(201)};
        int[] expectedResult = {1, 2, 0, 3, 0, //coffer
                                0, 1, 0, 0, 0, //wh1
                                0, 0, 0, 0, 0, //wh2
                                0, 0, 0, 3, 0, //wh3
                                2,             //dev 1 card 1 id
                                0,             //dev 1 card 2 id
                                0,             //dev 1 card 3 id
                                0, 0, 0, 1, 0, //dev 1 in
                                0, 1, 0, 0, 0, //dev 1 out
                                0, 0, 0, 1, 0, //dev 1 curr
                                0,             //dev 2 card 1 id
                                0,             //dev 2 card 2 id
                                0,             //dev 2 card 3 id
                                0, 0, 0, 0, 0, //dev 2 in
                                0, 0, 0, 0, 0, //dev 2 out
                                0, 0, 0, 0, 0, //dev 2 curr
                                5,             //dev 3 card 1 id
                                21,            //dev 3 card 2 id
                                0,             //dev 3 card 3 id
                                0, 1, 0, 0, 0, //dev 3 in
                                0, 0, 0, 0, 2, //dev 3 out
                                0, 0, 1, 0, 0, //dev 3 curr
                                0, 2, 0, 1, 0, //paycheck strongbox
                                1, 1, 3, 0, 0, //paycheck depots
                                0, 0, 0, 0, 0, //base in fix
                                1,             //base in mutable 1
                                2,             //base in mutable 2
                                0, 0, 0, 0, 0, //base out fix
                                3,             //base our mutable
                                0, 0, 1, 0, 0, //base current
                                13,             //faith track position
                                2,             //pope tile 1
                                1,             //pope tile 2
                                0,             //pope tile 3
                                11,             //leader 1 id
                                1,             //leader 1 state
                                3,             //leader 1 in fix
                                4,             //leader 1 out fix
                                2,             //leader 1 out mutable
                                0, 0, 1, 0, 0, //leader 1 current
                                0, 0, 0, 0, 0, //leader 1 depot
                                7,             //leader 2 id
                                1,             //leader 2 state
                                0,             //leader 2 in fix
                                0,             //leader 2 out fix
                                0,             //leader 2 out mutable
                                0, 0, 0, 0, 0, //leader 2 current
                                0, 0, 2, 0, 0, //leader 2 depot
                                1, 0, 1, 2, 0, 0,
                                2,
                                0,
                                0,
                                0,
                                0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                11,
                                1,
                                3,
                                4,
                                2,
                                0, 0, 1, 0, 0,
                                0, 0, 0, 0, 0,
                                7,
                                1,
                                0,
                                0,
                                0,
                                0, 0, 0, 0, 0,
                                0, 0, 2, 0, 0,
                                15,
                                0,
                                0,
                                0,
                                0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0};
        assertArrayEquals(expectedResult, actualResult);
    }

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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
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
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl one yellow development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.PAYCHECK, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.WHITE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                0, 2, 0, 0, 0,
                                0, 0, 2, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void assignMarble_toALeaderDepotNoEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(11));
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {2, 0, 0, 0, 0,      //coffer
                                0, 0, 0, 0, 0,      //Warehouse 1
                                0, 0, 1, 0, 0,      //Warehouse 2
                                3, 0, 0, 0, 0,      //Warehouse 3
                                0, 0, 0, 1, 0};     //LeaderDepot
        int[] actualResult = {status.get(0), status.get(1), status.get(2), status.get(3), status.get(4),
                              status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19),
                              status.get(116), status.get(117), status.get(118), status.get(119), status.get(120)};
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.YELLOW);
        } catch (MarbleException e) {
            exc = true;
        } catch (SupplyException | LeaderException | NoSuchMethodException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        boolean exc = false;
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.GREY);
        } catch (LeaderException e) {
            exc = true;
        } catch (SupplyException | MarbleException | NoSuchMethodException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void transformWhiteMarble_twoDifferentLeaderNoEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(2));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
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
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl one yellow development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.PAYCHECK, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(2, 2, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate leader card
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two violet development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.swapWarehouseRows(2, 3);
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 1);
        try {
            dshbrd.swapWarehouseRows(1, 2);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate leader card
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies and transform white marble into two different colors
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.transformWhiteMarble(MarbleColor.BLUE);
            dshbrd.transformWhiteMarble(MarbleColor.VIOLET);
        } catch (MarbleException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 1, 0, 0, 0,
                                0, 0, 2, 0, 0,
                                1, 0, 0, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void transformWhiteMarble_noMoreWhiteMarblesEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        cards.add(new CardsRequirement(2, 1, CardCategory.GREEN));
        cards.add(new CardsRequirement(1, 1, CardCategory.VIOLET));
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(10, new SupplyContainer(), cards, new Market(WarehouseObjectType.SHIELD), 3));
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.swapWarehouseRows(1, 3);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two violet development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 1);
        try {
            dshbrd.swapWarehouseRows(1, 3);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate leader card
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies and transform white marble into two different colors
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.transformWhiteMarble(MarbleColor.BLUE);
            dshbrd.transformWhiteMarble(MarbleColor.VIOLET);
        } catch (MarbleException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.transformWhiteMarble(MarbleColor.BLUE);
        } catch (MarbleException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void transformWhiteMarble_noLeaderActiveEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(7));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.transformWhiteMarble(MarbleColor.BLUE);
        } catch (MarbleException e) {
            exc = true;
        }
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
    public void moveSupply_fromWarehouseToLeaderDepotNoEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //move supply
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.LEADER1, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {2, 0, 0, 0, 0,      //coffer
                                0, 0, 0, 0, 0,      //Warehouse 1
                                0, 0, 0, 1, 0,      //Warehouse 2
                                3, 0, 0, 0, 0,      //Warehouse 3
                                0, 0, 0, 1, 0};     //LeaderDepot
        int[] actualResult = {status.get(0), status.get(1), status.get(2), status.get(3), status.get(4),
                              status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19),
                              status.get(116), status.get(117), status.get(118), status.get(119), status.get(120)};
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
    public void produce_developmentCard() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.LEADER2, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, true, false);

        assertEquals(new Integer(4), dshbrd.getStatus().get(102));
    }

    @Test
    public void produce_moreThanOneProduction() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
        dshbrd.buySupplies(MarketDirection.VERTICAL, 3);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate production
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.DEVELOPMENT2, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.LEADER2, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, true, false, false, true, true);

        ArrayList<Integer> result = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {1, 0, 0, 1, 0, 5};
        int[] actualResult = {result.get(0), result.get(1), result.get(2), result.get(3), result.get(4), result.get(102)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce_vaticanReportTriggered() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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

        for(int i = 0; i<7; i++)
            dshbrd.goAhead();

        //produce and activate a vatican report
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.DEVELOPMENT2, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(dshbrd.produce(false, true, false, false, false, false));
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.LEADER2, WarehouseObjectType.SERVANT);
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
    public void checkProduction_wrongSuppliesEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //move supplies to activate a production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}

        boolean exc = false;
        try {
            dshbrd.checkProduction(false, false, false, false, false, true);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
    public void buyDevelopment_lvlThreeNoEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
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

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.swapWarehouseRows(1, 2);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
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

        //buy a lvl 3 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 0, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {1, 21, 45};
        int[] actualResult = {status.get(38), status.get(39), status.get(40)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void buyDevelopment_withLeaderAbilityDiscountActive() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(5, new SupplyContainer(5, 0, 0, 0, 0), cards, new Depot(WarehouseObjectType.STONE), 3));
            dshbrd.addLeader(new LeaderCard(2, new SupplyContainer(), cards2, new Discount(WarehouseObjectType.STONE), 5));
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 2, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //activate leader ability
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card with a discount
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(2, 2, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {1, 5, 9};
        int[] actualResult = {status.get(20), status.get(38), status.get(56)};
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
    public void buyableDevelopmentLevel_withLvlThreeDevelopmentCard() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
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

        //buy a lvl 2 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.swapWarehouseRows(1, 2);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
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

        //buy a lvl 3 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 0, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        ArrayList<Integer> list = new ArrayList<>(dshbrd.buyableDevelopmentLevels());
        int[] expectedResult = {1, 4, 1};
        int[] actualResult = {list.get(0), list.get(1), list.get(2)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void discardLeader_noVaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        boolean vr = false;
        try {
            vr = dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {1, 0,
                                1, 2,
                                3, 0};
        int[] actualResult = {status.get(102), vr? 1: 0,
                              status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void discardLeader_vaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        for(int i = 0; i < 7; i++)
            dshbrd.goAhead();
        boolean vr = false;
        try {
            vr = dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {8, 1,
                                1, 2,
                                3, 0};
        int[] actualResult = {status.get(102), vr? 1: 0,
                              status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void discardLeader_alreadyDiscardedEX() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        try {
            dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.discardLeader(0);
        } catch (LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void discardLeader_alreadyActivatedEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        boolean exc = false;
        try {
            dshbrd.discardLeader(0);
        } catch (LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void playLeader_levelOneDevelopmentCardsNeeded() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(15));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
        } catch (LeaderException e) {fail();}

        //buy supplies from market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy development card
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
                                15, 1};
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {5, 1,
                                1, 0};
        int[] actualResult = {status.get(106), status.get(107),
                              status.get(121), status.get(122)};
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    public void playLeader_alreadyActiveEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        boolean exc = false;
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException e) {fail();
        } catch (LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void playLeader_alreadyDiscardedEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //discard leader
        try {
            dshbrd.discardLeader(0);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        boolean exc = false;
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException e) {fail();
        } catch (LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void playLeader_notEnoughResourcesEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        boolean exc = false;
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException e) {
            exc = true;
        } catch (LeaderException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void goAhead_noVaticanReport() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        boolean status = dshbrd.goAhead();
        int[] expectedResult = {1, 0};
        int[] actualResult = {dshbrd.getStatus().get(102), status ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void goAhead_vaticanReportTriggered() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        boolean result = false;
        for(int i = 0; i < 8; i++)
            result |= dshbrd.goAhead();
        assertTrue(result);
    }

    @Test
    public void goAheadDontTrigger() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        for(int i = 0; i<7; i++)
            dshbrd.goAheadDontTrigger();
        boolean result = dshbrd.goAheadDontTrigger();
        int[] expectedResult = {8, 1};
        int[] actualResult = {dshbrd.getStatus().get(102), result ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void vaticanReport_discardPopeFavorTile() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
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
    public void swapBaseProduction_noTypeInputEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.swapBaseProduction(1, WarehouseObjectType.NO_TYPE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapBaseProduction_noTypeOutputEx (){
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.swapBaseProduction(2, WarehouseObjectType.NO_TYPE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapLeaderProduction_noEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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

        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        assertEquals(new Integer(4), status.get(125));
    }

    @Test
    public void swapLeaderProduction_noTypeOutputEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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

        boolean exc = false;
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.NO_TYPE);
        } catch (SupplyException e){
          exc = true;
        } catch (NoSuchMethodException | LeaderException e) {fail();}
        assertTrue(exc);
    }

     @Test
     public void swapLeaderProduction_leaderNotActiveEx() {
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
         Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
         ArrayList<LeaderCard> leaderCards = new ArrayList<>();
         leaderCards.add(new LeaderCard(5));
         leaderCards.add(new LeaderCard(1));
         leaderCards.add(new LeaderCard(3));
         leaderCards.add(new LeaderCard(8));
         dshbrd.fillLeadersPicksWithList(leaderCards);
         try {
             dshbrd.pickLeader(1);
             dshbrd.pickLeader(2);
         } catch (LeaderException e) {fail();}

         boolean exc = false;
         try {
             dshbrd.swapLeaderProduction(1, WarehouseObjectType.NO_TYPE);
         } catch (LeaderException e){
             exc = true;
         } catch (NoSuchMethodException | SupplyException e) {fail();}
         assertTrue(exc);
     }

    @Test
    public void swapLeaderProduction_leaderDiscardedEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(10));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        try {
            dshbrd.discardLeader(1);
        } catch (LeaderException e) {fail();}

        boolean exc = false;
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.NO_TYPE);
        } catch (LeaderException e){
            exc = true;
        } catch (NoSuchMethodException | SupplyException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void swapLeaderProduction_wrongAbilityEx() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(15));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(0);
            dshbrd.pickLeader(1);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy a development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(1, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy a development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 2, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //play leader card
        try {
            dshbrd.playLeader(1);
        } catch (SupplyException | LeaderException e) {fail();}

        boolean exc = false;
        try {
            dshbrd.swapLeaderProduction(1, WarehouseObjectType.NO_TYPE);
        } catch (NoSuchMethodException e){
            exc = true;
        } catch (LeaderException | SupplyException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void clearDepot_clearBaseProduction() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //move resources to base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}

        try {
            dshbrd.clearDepot(DepotID.BASE_PRODUCTION);
        } catch (NoSuchMethodException e) {fail();}

        ArrayList<Integer> status = new ArrayList<>(dshbrd.getStatus());
        int[] expectedResult = {0, 0, 0, 0, 0,
                                1, 0, 0, 0, 0,
                                0, 0, 2, 0, 0};
        int[] actualResult = {status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                              status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                              status.get(15), status.get(16), status.get(17), status.get(18), status.get(19)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void hasInkwell_true() {
        Dashboard dshbrd = new Dashboard(true, new Marketplace(), new DevelopmentGrid(), "test");
        assertTrue(dshbrd.hasInkwell());
    }

    @Test
    public void hasInkwell_false() {
        Dashboard dshbrd = new Dashboard(false, new Marketplace(), new DevelopmentGrid(), "test");
        assertFalse(dshbrd.hasInkwell());
    }

    @Test
    public void isMatchEnded_faithTrack() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        for(int i = 0; i < 24; i++)
            dshbrd.goAhead();
        assertTrue(dshbrd.isMatchEnded());
    }

    @Test
    public void isMatchEnded_sevenDevelopmentCards() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        cards.add(new CardsRequirement(2, 1, CardCategory.GREEN));
        cards.add(new CardsRequirement(1, 1, CardCategory.VIOLET));
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(10, new SupplyContainer(), cards, new Market(WarehouseObjectType.SHIELD), 3));
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.swapWarehouseRows(1, 3);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two violet development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 1);
        try {
            dshbrd.swapWarehouseRows(1, 3);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.WHITE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.WHITE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 3);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy a lvl three purple development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 0, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        assertTrue(dshbrd.isMatchEnded());
    }

    @Test
    public void isMatchEnded_false() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        assertFalse(dshbrd.isMatchEnded());
    }

    @Test
    public void getAllowedDepots_fromWarehouse() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<DepotID> list = new ArrayList<>(dshbrd.getAllowedDepots(DepotID.WAREHOUSE2, WarehouseObjectType.STONE));
        int[] expectedResult = {1,          //base production
                                0, 1, 0,    //warehouse
                                0, 1, 0,    //development card
                                1, 1,       //leader cards
                                1,          //paycheck
                                0};         //coffer
        int[] actualResult = {list.contains(DepotID.BASE_PRODUCTION) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE1) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE2) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE3) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT1) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT2) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT3) ? 1 : 0,
                              list.contains(DepotID.LEADER1) ? 1 : 0,
                              list.contains(DepotID.LEADER2) ? 1 : 0,
                              list.contains(DepotID.PAYCHECK) ? 1 : 0,
                              list.contains(DepotID.COFFER) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllowedDepots_fromCoffer() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<DepotID> list = new ArrayList<>(dshbrd.getAllowedDepots(DepotID.COFFER, WarehouseObjectType.COIN));
        int[] expectedResult = {1,          //base production
                                0, 0, 0,    //warehouse
                                0, 1, 0,    //development card
                                0, 1,       //leader card depot
                                1,          //paycheck
                                1};         //coffer
        int[] actualResult = {list.contains(DepotID.BASE_PRODUCTION) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE1) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE2) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE3) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT1) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT2) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT3) ? 1 : 0,
                              list.contains(DepotID.LEADER1) ? 1 : 0,
                              list.contains(DepotID.LEADER2) ? 1 : 0,
                              list.contains(DepotID.PAYCHECK) ? 1 : 0,
                              list.contains(DepotID.COFFER) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllowedDepots_fromLeaderDepot() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(true), "test");
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
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

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

        //play leadercard
        try {
            dshbrd.playLeader(0);
        } catch (SupplyException | LeaderException e) {fail();}

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 2, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy a developmentCard
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.COIN);
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.LEADER1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        ArrayList<DepotID> list = new ArrayList<>(dshbrd.getAllowedDepots(DepotID.LEADER1, WarehouseObjectType.STONE));
        int[] expectedResult = {1,          //base production
                                0, 1, 0,    //warehouse
                                0, 1, 0,    //development card
                                1, 1,       //leader cards
                                1,          //paycheck
                                0};         //coffer
        int[] actualResult = {list.contains(DepotID.BASE_PRODUCTION) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE1) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE2) ? 1 : 0,
                              list.contains(DepotID.WAREHOUSE3) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT1) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT2) ? 1 : 0,
                              list.contains(DepotID.DEVELOPMENT3) ? 1 : 0,
                              list.contains(DepotID.LEADER1) ? 1 : 0,
                              list.contains(DepotID.LEADER2) ? 1 : 0,
                              list.contains(DepotID.PAYCHECK) ? 1 : 0,
                              list.contains(DepotID.COFFER) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void extractActionTileWithIndex_checkRemoval() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //remove four yellow development cards
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);
        dshbrd.extractActionTileWithIndex(0);

        //buy supplies from the market
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy a lvl 1 development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        boolean exc = false;
        try {
            dshbrd.buyDevelopment(2, 2, 1);
        } catch (SupplyException | DevelopmentException e) {fail();
        } catch (NoSuchCardException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void extractActionTileWithIndex_removeAllTheCardsOfOneColor() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        //remove all lvl 1 yellow development cards
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);

        //remove all lvl 2 yellow development cards
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);

        //remove all lvl 3 yellow development cards
        dshbrd.extractActionTileWithIndex(0);
        dshbrd.extractActionTileWithIndex(4);
        dshbrd.extractActionTileWithIndex(0);

        //try to remove two more yellow cards
        dshbrd.extractActionTileWithIndex(4);
        boolean result = dshbrd.extractActionTileWithIndex(0);

        assertTrue(result);
    }

    @Test
    public void extractActionTileWithIndex_blackCrossEndsFaithTrack() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(5));
        leaderCards.add(new LeaderCard(1));
        leaderCards.add(new LeaderCard(3));
        leaderCards.add(new LeaderCard(8));
        dshbrd.fillLeadersPicksWithList(leaderCards);
        try {
            dshbrd.pickLeader(1);
            dshbrd.pickLeader(2);
        } catch (LeaderException e) {fail();}

        for(int i = 0; i<11; i++){
            dshbrd.extractActionTileWithIndex(4);
            dshbrd.extractActionTileWithIndex(4);
        }
        dshbrd.extractActionTileWithIndex(4);
        boolean result = dshbrd.extractActionTileWithIndex(4);

        assertTrue(result);
    }

    @Test
    public void getWinPoints() {
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
        Dashboard dshbrd = new Dashboard(true, mrkt, new DevelopmentGrid(), "test");
        ArrayList<CardsRequirement> cards = new ArrayList<>();
        cards.add(new CardsRequirement(2, 1, CardCategory.GREEN));
        cards.add(new CardsRequirement(1, 1, CardCategory.VIOLET));
        ArrayList<CardsRequirement> cards2 = new ArrayList<>();
        cards2.add(new CardsRequirement(2, 1, CardCategory.YELLOW));
        cards2.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        try {
            dshbrd.addLeader(new LeaderCard(10, new SupplyContainer(), cards, new Market(WarehouseObjectType.SHIELD), 3));
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
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.swapWarehouseRows(1, 3);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.BLUE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 0);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy lvl two violet development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 1, 1);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.YELLOW);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 1);
        try {
            dshbrd.swapWarehouseRows(1, 3);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //buy lvl two green development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(0, 1, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 0);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.WHITE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.WHITE);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 3);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.discardSupplies();

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.STONE);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.STONE);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy supplies
        dshbrd.buySupplies(MarketDirection.VERTICAL, 2);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.YELLOW);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //activate a base production
        try {
            dshbrd.swapBaseProduction(0, WarehouseObjectType.COIN);
            dshbrd.swapBaseProduction(1, WarehouseObjectType.SHIELD);
            dshbrd.swapBaseProduction(2, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE1, DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN);
            dshbrd.moveSupply(DepotID.WAREHOUSE2, DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        dshbrd.produce(false, false, false, false, false, true);

        //buy a lvl three purple development card
        try {
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.WAREHOUSE3, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
            dshbrd.moveSupply(DepotID.COFFER, DepotID.PAYCHECK, WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        try {
            dshbrd.buyDevelopment(3, 0, 2);
        } catch (SupplyException | DevelopmentException | NoSuchCardException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        //buy supplies
        dshbrd.buySupplies(MarketDirection.HORIZONTAL, 1);
        try {
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
            dshbrd.assignMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dshbrd.assignMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | MarbleException | NoSuchMethodException | LeaderException e) {fail();}

        dshbrd.goAhead();
        dshbrd.goAhead();
        dshbrd.goAhead();

        int[] expectedResult = {39, 6};
        int[] actualResult = {dshbrd.getWinPoints().first, dshbrd.getWinPoints().second};
        assertArrayEquals(expectedResult, actualResult);
    }
}