package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
 *
 * The status is made this way:
 *
 * coffer (SupplyContainer style)
 * wh1 (SupplyContainer style)
 * wh2 (SupplyContainer style)
 * wh3 (SupplyContainer style)
 * devSpace1card1 (ID)
 * devSpace1card2 (ID)
 * devSpace1card3 (ID)
 * devSpace1in (SupplyContainer style)
 * devSpace1out (SupplyContainer style)
 * devSpace1curr (SupplyContainer style)
 * devSpace2card1 (ID)
 * devSpace2card2 (ID)
 * devSpace2card3 (ID)
 * devSpace2in (SupplyContainer style)
 * devSpace2out (SupplyContainer style)
 * devSpace2curr (SupplyContainer style)
 * devSpace3card3 (ID)
 * devSpace3card2 (ID)
 * devSpace3card1 (ID)
 * devSpace3in (SupplyContainer style)
 * devSpace3out (SupplyContainer style)
 * devSpace3curr (SupplyContainer style)
 * paycheckFromStrongbox (SupplyContainer style)
 * paycheckFromDepots (SupplyContainer style)
 * baseProdInFixed (always 00000)
 * baseProdInMutable1 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * baseProdInMutable2 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * baseProdOutFixed (always 00000)
 * baseProdOutMutable1 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * baseProdCurr (SupplyContainer style)
 * faithTrackPos
 * popeTile1 (0 = inactive, 1 = active, 2 = discarded)
 * popeTile2 (0 = inactive, 1 = active, 2 = discarded)
 * popeTile3 (0 = inactive, 1 = active, 2 = discarded)
 * leader1id (ID)
 * leader1state (0 = inactive, 1 = active, 2 = discarded)
 * leader1inFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader1outFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader1outMutable (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader1curr (SupplyContainer style)
 * leader1depot (SupplyContainer style)
 * leader2id (ID)
 * leader2state (0 = inactive, 1 = active, 2 = discarded)
 * leader2inFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader2outFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader2outMutable (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
 * leader2curr (SupplyContainer style)
 * leader2depot (SupplyContainer style)
 * unassignedMarbles (BLUE, GREY, RED, VIOLET, WHITE, YELLOW)
 * leaderPick1 (same as leader)
 * leaderPick2 (same as leader)
 * leaderPick3 (same as leader)
 * leaderPick4 (same as leader)
 *
 * SupplyContainer style means that there are 5 integers which represents, in this specific order, the number of: COIN, SERVANT, SHIELD, STONE, FAITH_MARKER
 */

public interface HasStatus {

    ArrayList<Integer> getStatus();

}
