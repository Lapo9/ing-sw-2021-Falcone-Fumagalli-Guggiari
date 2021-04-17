package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

public class ResourceChecker {

    private DepotsManager depotsManager;
    private SupplyContainer coffer;
    private Developments developments;


    public ResourceChecker(DepotsManager depotsManager, SupplyContainer coffer, Developments developments){
        this.depotsManager = depotsManager;
        this.coffer = coffer;
        this.developments = developments;
    }


    /**
     * Checks if the player has enough resources and development cards to match the requirements
     * @param supplyReq Requirement based on the total supplies the player owns
     * @param developmentReqs Requirement based on the development cards of the player
     * @return True if the player matches the requirements
     */
    public boolean check(SupplyContainer supplyReq, ArrayList<CardsRequirement> developmentReqs) {
        //check supply requirements
        SupplyContainer allSupplies = new SupplyContainer();
        allSupplies.sum(coffer);

        //add all of the supplies in the depots (warehouse + leaders)
        for (WarehouseObjectType wot : WarehouseObjectType.values()) {
            int qty = depotsManager.getResourceCount(wot);
            for (int i=0; i<qty; ++i){
                try {
                    allSupplies.addSupply(wot);
                } catch (SupplyException se){/*TODO terminate program*/}
            }
        }

        //check that the requirement is satisfied
        for (WarehouseObjectType wot : WarehouseObjectType.values()) {
            if(supplyReq.getQuantity(wot) > allSupplies.getQuantity(wot)){
                return false;
            }
        }



        //check cards requirement
        //for each requirement
        for(CardsRequirement req : developmentReqs) {
            ArrayList<Pair<CardCategory, Integer>> allCards = developments.getCardsTypes();

            //keep only the cards with a level higher than the requested one
            allCards.stream().filter(card -> card.second >= req.minLevel());

            //keep only the cards of the specified category
            if(req.reqCard() != null) {
                allCards.stream().filter(card -> card.first == req.reqCard());
            }

            //check if the remaining cards are more than the required number
            if(allCards.size() < req.getNumber()){
                return false;
            }
        }

        //if all the previous tests has been passed, then requirements are met
        return true;
    }




}
