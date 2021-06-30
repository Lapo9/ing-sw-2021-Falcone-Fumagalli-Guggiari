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

}
