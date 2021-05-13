package it.polimi.ingsw.model.leader_abilities;

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


    @Override
    public WarehouseObjectType transformWhiteMarble() {
        return transformTo;
    }


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
