package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.viewables.*;

import static it.polimi.ingsw.view.cli.ViewableId.*;

public class MainCLI {

    public static void main(String[] args) throws ViewException {
        ScreenCLI screen = new ScreenCLI();

        ServerSocket serverSocket = new ServerSocket();

        OfflineInfo offlineInfo = new OfflineInfo();

        ViewableFactory factory = new ViewableFactory(offlineInfo);
        offlineInfo.attachFactory(factory);

        ControllerInterpreter controllerInterpreter = new ControllerInterpreter(screen, offlineInfo);
        UserInterpreter userInterpreter = new UserInterpreter(controllerInterpreter, serverSocket, offlineInfo);
        ModelInterpreterCLI modelInterpreter = new ModelInterpreterCLI(factory, controllerInterpreter, offlineInfo);

        screen.attachUserInterpreter(userInterpreter);
        serverSocket.attachInterpreter(controllerInterpreter);
        serverSocket.attachInterpreter(modelInterpreter);



        //create viewables that are shared among different views
        SupplyContainer coffer1 = factory.buildSupplyContainer(1, COFFER, "Coffer"); //dashboard, development grid
        Warehouse warehouse1 = factory.buildWarehouse(1, WAREHOUSE); //dashboard, development grid, marketplace

        LeaderCardSpace leaders = factory.buildLeaderCardSpace(1, LEADER_CARD_SPACE);

        createView("dashboard", screen,
                coffer1,
                warehouse1,
                leaders,
                factory.buildBaseProduction(1, BASE_PRODUCTION),
                factory.buildDevelopmentSpace(1, DEVELOPMENT_SPACE1),
                factory.buildDevelopmentSpace(1, DEVELOPMENT_SPACE2),
                factory.buildDevelopmentSpace(1, DEVELOPMENT_SPACE3),
                factory.buildMarbleContainer(1, UNASSIGNED_MARBLES),
                factory.buildActiveProductions(1, ACTIVE_PRODUCTIONS));

        createView("developmentGrid", screen,
                coffer1,
                warehouse1,
                factory.buildSupplyContainer(1, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(1, PAYCHECK_DEPOTS, "Paycheck depots"),
                leaders,
                factory.buildDevelopmentGrid(DEVELOPMENT_GRID));

        createView("marketplace", screen,
                warehouse1,
                leaders,
                factory.buildMarketplace(MARKETPLACE));

        createView("preMatch", screen,
                factory.buildLeaderPick(1, LEADER_PICK));

        createView("faithTrack", screen,
                factory.buildFaithTrack(FAITH_TRACK));

        createView("welcome", screen);

        createView("yourTurn", screen);


        createView("opponent1", screen,
                factory.buildSupplyContainer(2, COFFER, "Coffer"),
                factory.buildWarehouse(2, WAREHOUSE),
                factory.buildSupplyContainer(2, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(2, PAYCHECK_DEPOTS, "Paycheck depots"),
                //factory.buildLeaderCard(2, LEADER1),
                //factory.buildLeaderCard(2, LEADER2),
                factory.buildBaseProduction(2, BASE_PRODUCTION),
                factory.buildDevelopmentSpace(2, DEVELOPMENT_SPACE1),
                factory.buildDevelopmentSpace(2, DEVELOPMENT_SPACE2),
                factory.buildDevelopmentSpace(2, DEVELOPMENT_SPACE3));

        createView("opponent2", screen,
                factory.buildSupplyContainer(3, COFFER, "Coffer"),
                factory.buildWarehouse(3, WAREHOUSE),
                factory.buildSupplyContainer(3, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(3, PAYCHECK_DEPOTS, "Paycheck depots"),
                //factory.buildLeaderCard(3, LEADER1),
                //factory.buildLeaderCard(3, LEADER2),
                factory.buildBaseProduction(3, BASE_PRODUCTION),
                factory.buildDevelopmentSpace(3, DEVELOPMENT_SPACE1),
                factory.buildDevelopmentSpace(3, DEVELOPMENT_SPACE2),
                factory.buildDevelopmentSpace(3, DEVELOPMENT_SPACE3));

        createView("opponent3", screen,
                factory.buildSupplyContainer(4, COFFER, "Coffer"),
                factory.buildWarehouse(4, WAREHOUSE),
                factory.buildSupplyContainer(4, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(4, PAYCHECK_DEPOTS, "Paycheck depots"),
                //factory.buildLeaderCard(4, LEADER1),
                //factory.buildLeaderCard(4, LEADER2),
                factory.buildBaseProduction(4, BASE_PRODUCTION),
                factory.buildDevelopmentSpace(4, DEVELOPMENT_SPACE1),
                factory.buildDevelopmentSpace(4, DEVELOPMENT_SPACE2),
                factory.buildDevelopmentSpace(4, DEVELOPMENT_SPACE3));

        createView("lobby", screen);

        screen.start("welcome");


        //showLeaderSpace(screen, factory);
    }



    private static void createView(String name, ScreenCLI screen, Viewable... viewables){
        View tmp = new View();

        for (Viewable v : viewables){
            tmp.addViewable(v);
        }

        screen.addView(name, tmp);
    }






    private static void showMarketplace (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View Marketplace = new View();
        Marketplace MarketplaceViewable = factory.buildMarketplace(MARKETPLACE);
        Marketplace.addViewable(MarketplaceViewable);

        screenCLI.addView("Marketplace", Marketplace);
        screenCLI.start("Marketplace");
    }

    private static void showMarbleContainer (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View marbleContainer = new View();
        MarbleContainer marbleContainerViewable = factory.buildMarbleContainer(1, ViewableId.WAREHOUSE);
        marbleContainer.addViewable(marbleContainerViewable);

        screenCLI.addView("marbleContainer", marbleContainer);
        screenCLI.start("marbleContainer");

        int [] updMarbleContainer = {2, 1, 0, 1, 0, 0};
        marbleContainerViewable.update(updMarbleContainer);
        screenCLI.show("marbleContainer");
    }

    private static void showWarehouseTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View warehouse = new View();
        Warehouse warehouseViewable = factory.buildWarehouse(1, ViewableId.WAREHOUSE);
        warehouse.addViewable(warehouseViewable);

        screenCLI.addView("warehouse", warehouse);
        screenCLI.start("warehouse");

        //updating the warehouse
        int [] updWarehouse = {2, 0, 0, 0, 0, //bot
                                0, 2, 0, 0, 0, //mid
                                0, 0, 1, 0, 0}; //top
        warehouseViewable.update(updWarehouse);
        screenCLI.show("warehouse");

        //another update test
        int [] updWarehouse1 = {0, 0, 3, 0, 0,
                                0, 0, 0, 0, 1,
                                0, 0, 0, 0, 0};
        warehouseViewable.update(updWarehouse1);
        screenCLI.show("warehouse");

        int [] updWarehouse2 = {0, 0, 1, 0, 0,
                                1, 0, 0, 0, 0,
                                0, 0, 0, 0, 1};
        warehouseViewable.update(updWarehouse2);
        screenCLI.show("warehouse");
    }

    private static void showDevelopmentCardTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentCard = new View();
        DevelopmentCard developmentCardViewable = factory.buildDevelopmentCard(1, ViewableId.DEVELOPMENT_CARD);
        developmentCard.addViewable(developmentCardViewable);

        screenCLI.addView("developmentCard", developmentCard);
        screenCLI.start("developmentCard");

        //updating the developmentCard
        int [] updWarehouse = {
                1, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updWarehouse);
        screenCLI.show("developmentCard");

        //another update test
        int [] updWarehouse1 = {
                5, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updWarehouse1);
        screenCLI.show("developmentCard");

        //empty card
        int [] updWarehouse2 = {
                48, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updWarehouse2);
        screenCLI.show("developmentCard");
    }

    private static void showDevelopmentSpaceTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentSpace = new View();
        DevelopmentSpace developmentSpaceViewable = factory.buildDevelopmentSpace(1, ViewableId.DEVELOPMENT_SPACE1);
        developmentSpace.addViewable(developmentSpaceViewable);

        screenCLI.addView("developmentSpace", developmentSpace);
        screenCLI.start("developmentSpace");

        int [] updDevSpace1 = {
                1, //id1
                48, //id2
                0, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace1);
        screenCLI.show("developmentSpace");

        int [] updDevSpace2 = {
                3, //id1
                0, //id2
                0, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace2);
        screenCLI.show("developmentSpace");

        int [] updDevSpace3 = {
                1, //id1
                2, //id2
                3, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace3);
        screenCLI.show("developmentSpace");

        int [] updDevSpace4 = {
                0, //id1
                0, //id2
                0, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace4);
        screenCLI.show("developmentSpace");

        int [] updDevSpace5 = {
                46, //id1
                5, //id2
                48, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace5);
        screenCLI.show("developmentSpace");
    }

    private static void showDevelopmentGridCardTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View developmentGridCard = new View();
        DevelopmentGridCard developmentGridCardViewable = factory.buildDevelopmentGridCard(1, ViewableId.DEVELOPMENT_CARD);
        developmentGridCard.addViewable(developmentGridCardViewable);

        screenCLI.addView("developmentGridCard", developmentGridCard);
        screenCLI.start("developmentGridCard");

        int [] updateDevGridCard = {1}; //this type of card gets updated only using the id
        developmentGridCardViewable.update(updateDevGridCard);
        screenCLI.show("developmentGridCard");

        int [] updateDevGridCard1 = {48}; //this type of card gets updated only using the id
        developmentGridCardViewable.update(updateDevGridCard1);
        screenCLI.show("developmentGridCard");


    }

    private static void showDevelopmentGridTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentGrid = new View();
        DevelopmentGrid developmentGridViewable = factory.buildDevelopmentGrid(ViewableId.DEVELOPMENT_GRID);
        developmentGrid.addViewable(developmentGridViewable);

        screenCLI.addView("developmentGrid", developmentGrid);
        screenCLI.start("developmentGrid");

        int [] updateDevGrid = {
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11};
        developmentGridViewable.update(updateDevGrid);
        screenCLI.show("developmentGrid");

    }

    private static void showActiveProductions(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View activeProductions = new View();
        ActiveProductions activeProductionsViewable = factory.buildActiveProductions(1, ViewableId.ACTIVE_PRODUCTIONS);
        activeProductions.addViewable(activeProductionsViewable);

        screenCLI.addView("activeProductions", activeProductions);
        screenCLI.start("activeProductions");


        int [] updateActiveProd = {
                0, //dev 1
                1, //dev 2
                1, //dev 3
                2, //leader 1
                1, //leader 2
                0}; //base prod
        activeProductionsViewable.update(updateActiveProd);
        screenCLI.show("activeProductions");
    }

    private static void showBaseProductionTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View baseProduction = new View();
        BaseProduction baseProductionViewable = factory.buildBaseProduction(1, ViewableId.BASE_PRODUCTION);
        baseProduction.addViewable(baseProductionViewable);

        screenCLI.addView("baseProduction", baseProduction);
        screenCLI.start("baseProduction");


        int [] updateBaseProd = {
                0, 0, 0, 0, 0,
                0, //COIN
                1, //SERVANT
                0, 0, 0, 0, 0,
                1, //FAITH_MARKER
                1, 0, 0, 0, 0
        };
        baseProductionViewable.update(updateBaseProd);
        screenCLI.show("baseProduction");

    }

    private static void showLeaderSpace(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View leaderSpace = new View();
        LeaderCardSpace leaderCardSpaceViewable = factory.buildLeaderCardSpace(1, LEADER_CARD_SPACE, false);
        leaderSpace.addViewable(leaderCardSpaceViewable);

        screenCLI.addView("leaderCardSpace", leaderSpace);
        screenCLI.start("leaderCardSpace");

        int[] updateLeaderSpace = {11,             //leader 1 id
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
                                   0, 0, 2, 0, 0}; //leader 2 depot}

        leaderCardSpaceViewable.update(updateLeaderSpace);
        screenCLI.show("leaderCardSpace");
    }

    private static void showLeaderPick(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View leaderPick = new View();
        LeaderPick leaderPickViewable = factory.buildLeaderPick(1, LEADER_PICK);
        leaderPick.addViewable(leaderPickViewable);

        screenCLI.addView("leaderPick", leaderPick);
        screenCLI.start("leaderPick");

        int[] updateLeaderPick = {2,
                                  0,
                                  0,
                                  0,
                                  0,
                                  0, 0, 0, 0, 0,
                                  0, 0, 0, 0, 0,
                                  11,
                                  0,
                                  0,
                                  0,
                                  0,
                                  0, 0, 0, 0, 0,
                                  0, 0, 0, 0, 0,
                                  7,
                                  0,
                                  0,
                                  0,
                                  0,
                                  0, 0, 0, 0, 0,
                                  0, 0, 0, 0, 0,
                                  15,
                                  0,
                                  0,
                                  0,
                                  0,
                                  0, 0, 0, 0, 0,
                                  0, 0, 0, 0, 0};

        leaderPickViewable.update(updateLeaderPick);
        screenCLI.show("leaderPick");
    }

}
