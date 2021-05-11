package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.viewables.SupplyContainer;
import it.polimi.ingsw.view.cli.viewables.ViewableFactory;
import it.polimi.ingsw.view.cli.viewables.Warehouse;

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

        View warehouse = new View();
        Warehouse warehouseViewable = factory.buildWarehouse(1, ViewableId.WAREHOUSE);
        warehouse.addViewable(warehouseViewable);



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
        screen.addView("warehouse",warehouse);


        screen.start("welcome");
        //screen.show("warehouse");

        showWarehouseTest(warehouse, warehouseViewable);

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

    private static void showWarehouseTest(View warehouse, Warehouse warehouseViewable) throws ViewException {
        ScreenCLI screen = new ScreenCLI();
        screen.addView("warehouse", warehouse);
        screen.start("warehouse");

        int [] updWarehouse = {1, 0, 0, 0, 0,
                                0, 2, 0, 0, 0,
                                0, 0, 3, 0, 0};
        warehouseViewable.update(updWarehouse);
        screen.show("warehouse");

        int [] updWarehouse1 = {0, 0, 1, 0, 0,
                                0, 0, 0, 0, 1,
                                0, 2, 0, 0, 0};
        warehouseViewable.update(updWarehouse1);
        screen.show("warehouse");
    }

}
