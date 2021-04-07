package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;

import java.util.HashMap;

/**
 * Manages all of those depots that can contain resources both from the strongbox and the depots, namely production depots, leader production depots and paycheck
 */
public class ProductionDepotsManager {

    HashMap<DepotID.SourceType, SupplyContainer> containers = new HashMap<>(); //place where we store a copy of each resource in real depots


    public ProductionDepotsManager() {
        containers.put(DepotID.SourceType.DEPOT, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));
        containers.put(DepotID.SourceType.STRONGBOX, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX)));
    }


    public void notifyAddition(WarehouseObjectType wot, DepotID from) {
        //if the resource comes from another production depot, the total resources balance remains the same
        if (from.getSource() != DepotID.SourceType.ANY) {
            try {
                containers.get(from.getSource()).addSupply(wot, from);
            } catch (SupplyException se) {/*TODO terminate program*/}
        }
    }


    public boolean canDelete(WarehouseObjectType wot, DepotID to) {
        //if the resource comes from another production depot, the total resources balance remains the same
        if (to.getSource() != DepotID.SourceType.ANY) {
            try {
                containers.get(to.getSource()).removeSupply(wot);
            } catch (SupplyException se) {
                return false;
            }
        }
        return true;
    }


    public void notifyDeletion(SupplyContainer deleted) {

        //try to remove from strongbox, if not possible remove from depot

        for (int i = 0; i < deleted.getQuantity(WarehouseObjectType.COIN); ++i) {
            try {
                containers.get(DepotID.SourceType.STRONGBOX).removeSupply(WarehouseObjectType.COIN);
            } catch (SupplyException se) {
                try {
                    containers.get(DepotID.SourceType.DEPOT).removeSupply(WarehouseObjectType.COIN);
                } catch (SupplyException se2) {//TODO terminate program*/}
                }
            }
        }

        for (int i = 0; i < deleted.getQuantity(WarehouseObjectType.SERVANT); ++i) {
            try {
                containers.get(DepotID.SourceType.STRONGBOX).removeSupply(WarehouseObjectType.SERVANT);
            } catch (SupplyException se) {
                try {
                    containers.get(DepotID.SourceType.DEPOT).removeSupply(WarehouseObjectType.SERVANT);
                } catch (SupplyException se2) {//TODO terminate program*/}
                }
            }
        }

        for (int i = 0; i < deleted.getQuantity(WarehouseObjectType.SHIELD); ++i) {
            try {
                containers.get(DepotID.SourceType.STRONGBOX).removeSupply(WarehouseObjectType.SHIELD);
            } catch (SupplyException se) {
                try {
                    containers.get(DepotID.SourceType.DEPOT).removeSupply(WarehouseObjectType.SHIELD);
                } catch (SupplyException se2) {//TODO terminate program*/}
                }
            }
        }

        for (int i = 0; i < deleted.getQuantity(WarehouseObjectType.STONE); ++i) {
            try {
                containers.get(DepotID.SourceType.STRONGBOX).removeSupply(WarehouseObjectType.STONE);
            } catch (SupplyException se) {
                try {
                    containers.get(DepotID.SourceType.DEPOT).removeSupply(WarehouseObjectType.STONE);
                } catch (SupplyException se2) {//TODO terminate program*/}
                }
            }
        }

    }
}
