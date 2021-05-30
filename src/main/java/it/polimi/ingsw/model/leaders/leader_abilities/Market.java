package it.polimi.ingsw.model.leaders.leader_abilities;

import it.polimi.ingsw.model.MarbleColor;
import it.polimi.ingsw.model.WarehouseObjectType;

import java.util.ArrayList;


/**
 *  This implementation of LeaderAbility only supports the getWhiteBall method, to transform a white marble into the supply specified o construction.
 */
public class Market implements LeaderAbility {

    private final WarehouseObjectType transformTo;

    /**
     * Creates a new market leader.
     * @param transformTo Supply type to transform the white ball to
     */
    public Market(WarehouseObjectType transformTo){
        this.transformTo = transformTo;
    }

    /**
     * Third ability: when the player gets white marbles from market, player can choose one of the resources to get
     * @return The resource to get in exchange for white marble
     */
    @Override
    public WarehouseObjectType transformWhiteMarble() {
        return transformTo;
    }

    /**
     * Third ability: returns the color to what the white marble can be transformed
     * @return the color to what the white marble can be transformed
     */
    @Override
    public MarbleColor colorWhiteMarble() throws NoSuchMethodException {
        switch (transformTo) {
            case COIN:
                return MarbleColor.YELLOW;
            case SERVANT:
                return MarbleColor.VIOLET;
            case SHIELD:
                return MarbleColor.BLUE;
            case STONE:
                return MarbleColor.GREY;
            default:
                return MarbleColor.WHITE;
        }
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of Integer
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        //fixed input, fixed output, mutable output, production depot (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER), "warehouse depot" (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER)
        for(int i=0; i<13; ++i){
            status.add(0);
        }

        return status;
    }

}
