package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.ControllerInterpreter;
import it.polimi.ingsw.view.OfflineInfo;
import it.polimi.ingsw.view.ServerSocket;
import it.polimi.ingsw.view.UserInterpreter;
import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;
import it.polimi.ingsw.view.cli.viewables.*;

import java.io.InputStream;
import java.util.Scanner;

import static it.polimi.ingsw.view.cli.ViewableId.*;

/**
 * Class responsible to manage the CLI
 */
public class MainCLI {

    /**
     * Manages the CLI screen of the player
     * @param args array containing the arguments passed from the command line
     * @throws ViewException if can't visualize something
     */
    public static void mainCLI(String[] args) throws ViewException {

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

        LeaderCardSpace leaders = factory.buildLeaderCardSpace(1, LEADER_CARD_SPACE, true);

        Marketplace marketplace = factory.buildMarketplace(MARKETPLACE);
        DevelopmentGrid developmentGrid = factory.buildDevelopmentGrid(DEVELOPMENT_GRID);

        createView("dashboard", screen,
                coffer1,
                warehouse1,
                leaders,
                factory.buildBaseProduction(1, BASE_PRODUCTION),
                factory.buildDevelopmentSpacerGrid(1, DEVELOPMENT_SPACE_GRID),
                factory.buildMarbleContainer(1, UNASSIGNED_MARBLES),
                factory.buildActiveProductions(1, ACTIVE_PRODUCTIONS));

        createView("developmentGrid", screen,
                coffer1,
                warehouse1,
                factory.buildSupplyContainer(1, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(1, PAYCHECK_DEPOTS, "Paycheck depots"),
                leaders,
                developmentGrid);

        createView("marketplace", screen,
                warehouse1,
                leaders,
                marketplace);

        createView("preMatch", screen,
                factory.buildLeaderPick(1, LEADER_PICK),
                developmentGrid,
                marketplace);

        createView("faithTrack", screen,
                factory.buildFaithTrack(FAITH_TRACK));

        createView("welcome", screen,
                new Viewable() {
                    @Override
                    public String toString() {
                        return FancyConsole.BOLD(FancyConsole.YELLOW(
                                "\n" +
                                " __      __  ___   _       ___    ___    __  __   ___   _ \n" +
                                " \\ \\    / / | __| | |     / __|  / _ \\  |  \\/  | | __| | |\n" +
                                "  \\ \\/\\/ /  | _|  | |__  | (__  | (_) | | |\\/| | | _|  |_|\n" +
                                "   \\_/\\_/   |___| |____|  \\___|  \\___/  |_|  |_| |___| (_)\n" +
                                "                                                          \n\n\n Type help to get the commands!"));
                    }
                });

        createView("yourTurn", screen,
                new Viewable() {
                    @Override
                    public String toString() {
                        return FancyConsole.BOLD(FancyConsole.YELLOW(
                                "\n" +
                                "  ___  _    _                             _                     _ \n" +
                                " |_ _|| |_ ( )___  _  _  ___  _  _  _ _  | |_  _  _  _ _  _ _  | |\n" +
                                "  | | |  _||/(_-< | || |/ _ \\| || || '_| |  _|| || || '_|| ' \\ |_|\n" +
                                " |___| \\__|  /__/  \\_, |\\___/ \\_,_||_|    \\__| \\_,_||_|  |_||_|(_)\n" +
                                "                   |__/                                           \n"));
                    }
                },
                factory.buildActionTile(ACTION_TILE));


        createView("wait", screen,
                new Viewable() {
                    @Override
                    public String toString() {
                        return FancyConsole.BOLD(FancyConsole.YELLOW(
                                "\n" +
                                " __    __         _  _      __                _    _                          \n" +
                                "/ / /\\ \\ \\  __ _ (_)| |_   / _|  ___   _ __  | |_ | |__    ___                \n" +
                                "\\ \\/  \\/ / / _` || || __| | |_  / _ \\ | '__| | __|| '_ \\  / _ \\               \n" +
                                " \\  /\\  / | (_| || || |_  |  _|| (_) || |    | |_ | | | ||  __/               \n" +
                                "  \\/  \\/   \\__,_||_| \\__| |_|   \\___/ |_|     \\__||_| |_| \\___|               \n" +
                                "                                                                              \n" +
                                "        _    _                          _                                   _ \n" +
                                "  ___  | |_ | |__    ___  _ __   _ __  | |  __ _  _   _   ___  _ __  ___   / \\\n" +
                                " / _ \\ | __|| '_ \\  / _ \\| '__| | '_ \\ | | / _` || | | | / _ \\| '__|/ __| /  /\n" +
                                "| (_) || |_ | | | ||  __/| |    | |_) || || (_| || |_| ||  __/| |   \\__ \\/\\_/ \n" +
                                " \\___/  \\__||_| |_| \\___||_|    | .__/ |_| \\__,_| \\__, | \\___||_|   |___/\\/   \n" +
                                "                                |_|               |___/                       \n"));
                    }
                });


        createView("opponent1", screen,
                factory.buildSupplyContainer(2, COFFER, "Coffer"),
                factory.buildWarehouse(2, WAREHOUSE),
                factory.buildSupplyContainer(2, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(2, PAYCHECK_DEPOTS, "Paycheck depots"),
                factory.buildLeaderCardSpace(2, LEADER_CARD_SPACE, false),
                factory.buildBaseProduction(2, BASE_PRODUCTION),
                factory.buildDevelopmentSpacerGrid(2, DEVELOPMENT_SPACE_GRID));

        createView("opponent2", screen,
                factory.buildSupplyContainer(3, COFFER, "Coffer"),
                factory.buildWarehouse(3, WAREHOUSE),
                factory.buildSupplyContainer(3, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(3, PAYCHECK_DEPOTS, "Paycheck depots"),
                factory.buildLeaderCardSpace(3, LEADER_CARD_SPACE, false),
                factory.buildBaseProduction(3, BASE_PRODUCTION),
                factory.buildDevelopmentSpacerGrid(3, DEVELOPMENT_SPACE_GRID));

        createView("opponent3", screen,
                factory.buildSupplyContainer(4, COFFER, "Coffer"),
                factory.buildWarehouse(4, WAREHOUSE),
                factory.buildSupplyContainer(4, PAYCHECK_STRONGBOX, "Paycheck strongbox"),
                factory.buildSupplyContainer(4, PAYCHECK_DEPOTS, "Paycheck depots"),
                factory.buildLeaderCardSpace(4, LEADER_CARD_SPACE, false),
                factory.buildBaseProduction(4, BASE_PRODUCTION),
                factory.buildDevelopmentSpacerGrid(4, DEVELOPMENT_SPACE_GRID));

        createView("lobby", screen,
                new Viewable() {
                    @Override
                    public String toString() {
                        return "\n\nType info to get players connected to this match!";
                    }
                });

        createView("help", screen,
                new Viewable() {
                    @Override
                    public String toString() {

                        InputStream rules = getClass().getResourceAsStream("/files/ListOfCommands.txt");
                        Scanner s = new Scanner(rules).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";

                        return FancyConsole.BOLD(FancyConsole.YELLOW(
                                "\n" +
                                "  _     _      _           __                                         _     \n" +
                                " | |   (_) ___| |_   ___  / _|  __  ___  _ __   _ __   __ _  _ _   __| | ___\n" +
                                " | |__ | |(_-<|  _| / _ \\|  _| / _|/ _ \\| '  \\ | '  \\ / _` || ' \\ / _` |(_-<\n" +
                                " |____||_|/__/ \\__| \\___/|_|   \\__|\\___/|_|_|_||_|_|_|\\__,_||_||_|\\__,_|/__/\n" +
                                "                                                                            \n\n\n")) + result;
                    }
                });

        createView("endMatch", screen,
                new Viewable() {
                    @Override
                    public String toString() {
                        return FancyConsole.BOLD(FancyConsole.YELLOW(
                                "\n" +
                                "   ___                                        \n" +
                                "  / __| __ _  _ __   ___   ___ __ __ ___  _ _ \n" +
                                " | (_ |/ _` || '  \\ / -_) / _ \\\\ V // -_)| '_|\n" +
                                "  \\___|\\__,_||_|_|_|\\___| \\___/ \\_/ \\___||_|  \n" +
                                "                                              \n"));
                    }
                });

        screen.start("welcome");

    }


    /**
     * Adds the Viewable(s) to the collection View to show, then adds the View to the screen
     * @param name name of the View to add
     * @param screen where to show the Viewable(s)
     * @param viewables to put in the new View
     */
    private static void createView(String name, ScreenCLI screen, Viewable... viewables){
        View tmp = new View();

        for (Viewable v : viewables){
            tmp.addViewable(v);
        }

        screen.addView(name, tmp);
    }





//methods used to test every viewable
    private static void showMarketplace (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View Marketplace = new View();
        Marketplace marketplaceViewable = factory.buildMarketplace(MARKETPLACE);
        Marketplace.addViewable(marketplaceViewable);

        screenCLI.addView("Marketplace", Marketplace);
        screenCLI.start("Marketplace");
    }

    private static void showMarbleContainer (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View marbleContainer = new View();
        MarbleContainer marbleContainerViewable = factory.buildMarbleContainer(1, ViewableId.WAREHOUSE);
        marbleContainer.addViewable(marbleContainerViewable);

        screenCLI.addView("marbleContainer", marbleContainer);
        screenCLI.start("marbleContainer");

        int [] updMarbleContainer = {0, 2, 2, 0, 0, 0};
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
        int [] updDC = {
                1, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updDC);
        screenCLI.show("developmentCard");

        //another update test
        int [] updDC1 = {
                5, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updDC1);
        screenCLI.show("developmentCard");

        //empty card
        int [] updDC2 = {
                48, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updDC2);
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
                48, 37, 2, 3,
                1, 5, 6, 7,
                8, 9, 48, 11};
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
                0, //COIN
                0, 0, 0, 0, 0,
                0, //FAITH_MARKER
                0, 0, 0, 0, 0 //currentSupply
        };
        baseProductionViewable.update(updateBaseProd);
        screenCLI.show("baseProduction");

        int [] updateBaseProd2 = {
                0, 0, 0, 0, 0,
                1,
                2,
                0, 0, 0, 0, 0,
                4,
                0, 0, 0, 0, 0
        };
        baseProductionViewable.update(updateBaseProd2);
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

    private static void showDevelopmentSpaceGrid(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentSpaceGrid = new View();
        DevelopmentSpaceGrid developmentSpaceViewable = factory.buildDevelopmentSpacerGrid(1, DEVELOPMENT_SPACE_GRID);
        developmentSpaceGrid.addViewable(developmentSpaceViewable);

        screenCLI.addView("DevSpaceGrid", developmentSpaceGrid);
        screenCLI.start("DevSpaceGrid");

        int[] updateDevSpaceGrid = {2,
                                    0,
                                    0,
                                    0, 0, 0, 1, 0,
                                    0, 1, 0, 0, 0,
                                    0, 0, 0, 1, 0,
                                    11,
                                    17,
                                    47,
                                    0, 1, 0, 1, 0,
                                    2, 0, 2, 0, 1,
                                    0, 0, 0, 0, 0,
                                    5,
                                    21,
                                    0,
                                    0, 1, 0, 0, 0,
                                    0, 0, 0, 0, 2,
                                    0, 0, 1, 0, 0};

        developmentSpaceViewable.update(updateDevSpaceGrid);
        screenCLI.show("DevSpaceGrid");
    }

    private static void showFaithTrack (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View FaithTrack = new View();
        FaithTrack faithTrackViewable = factory.buildFaithTrack(FAITH_TRACK);
        FaithTrack.addViewable(faithTrackViewable);

        screenCLI.addView("FaithTrack", FaithTrack);
        screenCLI.start("FaithTrack");

        int [] updateFT = {
            1, 0, 1, 2, 3
        };
        faithTrackViewable.update(updateFT, new OfflineInfo());
        screenCLI.show("FaithTrack");
    }
}
