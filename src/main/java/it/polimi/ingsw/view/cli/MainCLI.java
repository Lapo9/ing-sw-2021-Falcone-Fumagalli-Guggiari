package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.DevelopmentCard;
import it.polimi.ingsw.view.cli.viewables.SupplyContainer;
import it.polimi.ingsw.view.cli.viewables.TestViewable;
import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

import static it.polimi.ingsw.view.cli.ViewableId.TEST;

public class MainCLI {

    public static void main(String[] args) {
        Screen screen = new Screen();

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

        screen.start("welcome");



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

}
