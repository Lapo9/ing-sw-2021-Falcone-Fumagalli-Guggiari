package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.SupplyContainer;

public class Main {

    public static void main(String[] args) {
        Screen screen = new Screen();

        ControllerSocket controllerSocket = new ControllerSocket();
        ModelSocket modelSocket = new ModelSocket();

        ViewableFactory factory = new ViewableFactory();

        ControllerInterpreter controllerInterpreter = new ControllerInterpreter(screen);
        UserInterpreter userInterpreter = new UserInterpreter(controllerInterpreter, controllerSocket);
        ModelInterpreter modelInterpreter = new ModelInterpreter(factory, screen);

        screen.attachUserInterpreter(userInterpreter);
        controllerSocket.attachInterpreter(controllerInterpreter);
        modelSocket.attachInterpreter(modelInterpreter);


        View viewTest1 = new View();
        SupplyContainer cofferTest1 = factory.buildSupplyContainer(1, ViewableId.COFFER, "Coffer1Test");
        viewTest1.addViewable(cofferTest1);

        View viewTest2 = new View();
        SupplyContainer cofferTest2 = factory.buildSupplyContainer(2, ViewableId.COFFER, "Coffer2Test");
        viewTest2.addViewable(cofferTest2);

        screen.addView("ViewTest1", viewTest1);
        screen.addView("ViewTest2", viewTest2);

        screen.start("ViewTest1");



        new Thread(() -> {
            try {
                Thread.sleep(5000);
                int[] up1 = {0, 1, 3, 0, 1, 0};
                modelInterpreter.update(up1);
                Thread.sleep(10000);
                int[] up2 = {1, 5, 0, 0, 1, 0};
                modelInterpreter.update(up2);
            } catch (InterruptedException ie){}
        }).start();
    }

}
