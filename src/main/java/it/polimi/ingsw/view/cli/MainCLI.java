package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.SupplyContainer;
import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

public class MainCLI {

    public static void main(String[] args) {
        Screen screen = new Screen();

        ServerSocket serverSocket = new ServerSocket();

        ViewableFactory factory = new ViewableFactory();

        ControllerInterpreter controllerInterpreter = new ControllerInterpreter(screen);
        UserInterpreter userInterpreter = new UserInterpreter(controllerInterpreter, serverSocket);
        ModelInterpreter modelInterpreter = new ModelInterpreter(factory, controllerInterpreter);

        screen.attachUserInterpreter(userInterpreter);
        serverSocket.attachInterpreter(controllerInterpreter);
        serverSocket.attachInterpreter(modelInterpreter);


        View welcome = new View();
        SupplyContainer welcomeText = factory.buildSupplyContainer(1, ViewableId.WELCOME_TEXT, "WELCOME");
        welcome.addViewable(welcomeText);

        View start = new View();
        SupplyContainer startText = factory.buildSupplyContainer(1, ViewableId.START_TEXT, "START");
        start.addViewable(startText);

        View yourTurn = new View();
        SupplyContainer yourTurnText = factory.buildSupplyContainer(1, ViewableId.YOUR_TURN_TEXT, "YOUR TURN");
        yourTurn.addViewable(yourTurnText);

        screen.addView("home", welcome);
        screen.addView("start", start);
        screen.addView("yourTurn", yourTurn);

        screen.start("home");



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
