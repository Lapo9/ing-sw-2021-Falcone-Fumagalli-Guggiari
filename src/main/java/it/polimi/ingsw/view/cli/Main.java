package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.SupplyContainer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Screen screen = new Screen();
        ServerInterpreter serverInterpreter = new ServerInterpreter(screen);
        UserInterpreter userInterpreter = new UserInterpreter(serverInterpreter);

        screen.attachUserInterpreter(userInterpreter);

        ViewableFactory factory = new ViewableFactory();

        View viewTest1 = new View();
        SupplyContainer cofferTest1 = factory.buildSupplyContainer(1, ViewableId.COFFER, "Coffer1Test");
        viewTest1.addViewable(cofferTest1);

        View viewTest2 = new View();
        SupplyContainer cofferTest2 = factory.buildSupplyContainer(2, ViewableId.COFFER, "Coffer2Test");
        viewTest2.addViewable(cofferTest2);

        screen.addView("ViewTest1", viewTest1);
        screen.addView("ViewTest2", viewTest2);

        ModelInterpreter modelInterpreter = new ModelInterpreter(factory, screen);

        screen.start("ViewTest1");

        Thread fakeModelSocket = new Thread(() -> {
            try {
                Thread.sleep(5000);
                int[] up1 = {0, 1, 3, 0, 1, 0};
                modelInterpreter.update(up1);
                Thread.sleep(10000);
                int[] up2 = {1, 5, 0, 0, 1, 0};
                modelInterpreter.update(up2);
            } catch (InterruptedException ie){}
        });

        fakeModelSocket.start();
    }

}
