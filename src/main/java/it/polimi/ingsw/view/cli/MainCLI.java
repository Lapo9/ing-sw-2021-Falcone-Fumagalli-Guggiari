package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.viewables.*;

import static it.polimi.ingsw.view.cli.ViewableId.TEST;

public class MainCLI {

    public static void main(String[] args) throws ViewException {
        ScreenCLI screen = new ScreenCLI();

        ServerSocket serverSocket = new ServerSocket();

        ViewableFactory factory = new ViewableFactory();

        OfflineInfo offlineInfo = new OfflineInfo();

        ControllerInterpreter controllerInterpreter = new ControllerInterpreter(screen, offlineInfo);
        UserInterpreter userInterpreter = new UserInterpreter(controllerInterpreter, serverSocket, offlineInfo);
        ModelInterpreter modelInterpreter = new ModelInterpreter(factory, controllerInterpreter, offlineInfo);

        screen.attachUserInterpreter(userInterpreter);
        serverSocket.attachInterpreter(controllerInterpreter);
        serverSocket.attachInterpreter(modelInterpreter);


        View welcome = new View();
        SupplyContainer welcomeText = factory.buildSupplyContainer(1, ViewableId.WELCOME_TEXT, "WELCOME");
        welcome.addViewable(welcomeText);

        View dashboard = new View();
        SupplyContainer dashboardViewable = factory.buildSupplyContainer(1, ViewableId.DASHBOARD_VIEWABLE, "YOUR DASHBOARD");
        dashboard.addViewable(dashboardViewable);

        View start = new View();
        SupplyContainer startText = factory.buildSupplyContainer(1, ViewableId.START_TEXT, "START");
        start.addViewable(startText);

        View yourTurn = new View();
        SupplyContainer yourTurnText = factory.buildSupplyContainer(1, ViewableId.YOUR_TURN_TEXT, "YOUR TURN");
        yourTurn.addViewable(yourTurnText);





        View player1 = new View();
        player1.addViewable(factory.buildTestViewable(1, TEST));

        View player2 = new View();
        player2.addViewable(factory.buildTestViewable(2, TEST));

        View player3 = new View();
        player3.addViewable(factory.buildTestViewable(3, TEST));

        View player4 = new View();
        player4.addViewable(factory.buildTestViewable(4, TEST));



        screen.addView("welcome", welcome);
        screen.addView("start", start);
        screen.addView("yourTurn", yourTurn);
        screen.addView("dashboard", dashboard);
        screen.addView("player1", player1);
        screen.addView("player2", player2);
        screen.addView("player3", player3);
        screen.addView("player4", player4);



        //screen.start("welcome");

        //methods to test the viewable objects

        //showMarketplace (screen, factory);
        //showMarbleContainer(screen, factory);
        //showWarehouseTest(screen, factory);
        //showDevelopmentCardTest(screen, factory);
        //showDevelopmentSpaceTest(screen, factory);
        //showDevelopmentGridCardTest(screen, factory);
        //showDevelopmentGridTest(screen, factory);
        showActiveProductions(screen, factory);


        /*new Thread(() -> {
            try {
                Thread.sleep(5000);
                int[] up1 = {0, 1, 3, 0, 1, 0};
                modelInterpreter.update(up1);
                Thread.sleep(10000);
                int[] up2 = {1, 5, 0, 0, 1, 0};
                modelInterpreter.update(up2);
            } catch (InterruptedException ie){}
        }).start();*/
    }

    private static void showMarketplace (ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View MarketPlace = new View();
        MarketPlace MarketPlaceViewable = factory.buildMarketPlace(ViewableId.WAREHOUSE);
        MarketPlace.addViewable(MarketPlaceViewable);

        screenCLI.addView("MarketPlace", MarketPlace);
        screenCLI.start("MarketPlace");
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
        int [] updWarehouse = {1, 0, 0, 0, 0, //first level
                                0, 1, 0, 0, 0, //second level
                                0, 0, 3, 0, 0}; //third level
        warehouseViewable.update(updWarehouse);
        screenCLI.show("warehouse");

        //another update test
        int [] updWarehouse1 = {0, 0, 0, 0, 0,
                                0, 0, 0, 0, 1,
                                0, 2, 0, 0, 0};
        warehouseViewable.update(updWarehouse1);
        screenCLI.show("warehouse");
    }

    private static void showDevelopmentCardTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentCard = new View();
        DevelopmentCard developmentCardViewable = factory.buildDevelopmentCard(1, ViewableId.DEVELOPMENTCARD);
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
                0, //id
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentCardViewable.update(updWarehouse2);
        screenCLI.show("developmentCard");
    }

    private static void showDevelopmentSpaceTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentSpace = new View();
        DevelopmentSpace developmentSpaceViewable = factory.buildDevelopmentSpace(1, ViewableId.DEVELOPMENTSPACE);
        developmentSpace.addViewable(developmentSpaceViewable);

        screenCLI.addView("developmentSpace", developmentSpace);
        screenCLI.start("developmentSpace");

        int [] updDevSpace2 = {
                1, //id1
                2, //id2
                0, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace2);
        screenCLI.show("developmentSpace");

        int [] updDevSpace1 = {
                1, //id1
                0, //id2
                0, //id3
                1, 2, 0, 0, 0, //input
                0, 0, 1, 0, 1, //output
                1, 0, 0, 0, 0 //currentSupply
        };
        developmentSpaceViewable.update(updDevSpace1);
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
    }

    private static void showDevelopmentGridCardTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException{
        View developmentGridCard = new View();
        DevelopmentGridCard developmentGridCardViewable = factory.buildDevelopmentGridCard(1, ViewableId.DEVELOPMENTGRIDCARD);
        developmentGridCard.addViewable(developmentGridCardViewable);

        screenCLI.addView("developmentGridCard", developmentGridCard);
        screenCLI.start("developmentGridCard");

        int [] updateDevGridCard = {1}; //this type of card gets updated only using the id
        developmentGridCardViewable.update(updateDevGridCard);
        screenCLI.show("developmentGridCard");

        int [] updateDevGridCard1 = {0}; //this type of card gets updated only using the id
        developmentGridCardViewable.update(updateDevGridCard1);
        screenCLI.show("developmentGridCard");


    }

    private static void showDevelopmentGridTest(ScreenCLI screenCLI, ViewableFactory factory) throws ViewException {
        View developmentGrid = new View();
        DevelopmentGrid developmentGridViewable = factory.buildDevelopmentGrid(ViewableId.DEVELOPMENTGRID);
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
        ActiveProductions activeProductionsViewable = factory.buildActiveProductions(1, ViewableId.ACTIVEPRODUCTIONS);
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
}
