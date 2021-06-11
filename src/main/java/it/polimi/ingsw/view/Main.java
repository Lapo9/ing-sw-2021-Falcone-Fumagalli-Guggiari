package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.MainController;
import it.polimi.ingsw.view.cli.MainCLI;
import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;
import it.polimi.ingsw.view.gui.DummyMain;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ViewException {
        boolean choiceMade = false;
        int choiceNumber = 0;

        while (!choiceMade) {
            System.out.print(FancyConsole.YELLOW(FancyConsole.BOLD("\n" +
                    "                     _                                            \n" +
                    "  /\\/\\    __ _  ___ | |_   ___  _ __  ___                         \n" +
                    " /    \\  / _` |/ __|| __| / _ \\| '__|/ __|                        \n" +
                    "/ /\\/\\ \\| (_| |\\__ \\| |_ |  __/| |   \\__ \\                        \n" +
                    "\\/    \\/ \\__,_||___/ \\__| \\___||_|   |___/                        \n" +
                    "                 __                                               \n" +
                    "          ___   / _|                                              \n" +
                    "         / _ \\ | |_                                               \n" +
                    "        | (_) ||  _|                                              \n" +
                    "   __    \\___/ |_|          _                                     \n" +
                    "  /__\\   ___  _ __    __ _ (_) ___  ___   __ _  _ __    ___   ___ \n" +
                    " / \\//  / _ \\| '_ \\  / _` || |/ __|/ __| / _` || '_ \\  / __| / _ \\\n" +
                    "/ _  \\ |  __/| | | || (_| || |\\__ \\\\__ \\| (_| || | | || (__ |  __/\n" +
                    "\\/ \\_/  \\___||_| |_| \\__,_||_||___/|___/ \\__,_||_| |_| \\___| \\___|\n" +
                    "                                                                  \n")));
            System.out.print("\n\n" + FancyConsole.FRAMED(" 1: Server\t\n 2: GUI\t\n 3: CLI\t\t") + FancyConsole.UNDERLINED(FancyConsole.BOLD("\n\nYour choice: ")));

            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();

            try {
                 choiceNumber = Integer.parseInt(choice);
                if (choiceNumber == 1 || choiceNumber == 2 || choiceNumber == 3){
                    choiceMade = true;
                }
            } catch (Exception e) {
                FancyConsole.clear();
            }
        }

        switch (choiceNumber){
            case 1:
                FancyConsole.clear();
                System.out.print("Choose a registered port number. And invalid input will result in default port number (14009): ");
                Scanner in = new Scanner(System.in);
                String portNumber = in.nextLine();
                FancyConsole.clear();
                MainController.mainServer(new String[]{portNumber});
                break;
            case 2:
                FancyConsole.clear();
                DummyMain.dummyMainGUI(args);
                break;
            case 3:
                FancyConsole.clear();
                MainCLI.mainCLI(args);
                break;
        }
    }

}
