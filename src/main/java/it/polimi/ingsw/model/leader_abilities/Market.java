package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.LeaderAbility;
import it.polimi.ingsw.model.WarehouseObjectType;


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
}
