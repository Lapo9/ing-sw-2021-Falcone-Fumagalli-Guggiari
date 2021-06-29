package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.Screen;
import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;
import it.polimi.ingsw.view.gui.MessageType;

import java.util.HashMap;
import java.util.Scanner;


/**
 * A ScreenCLI is responsible for showing to the user the right information, and for receiving from the user the commands.
 */
public class ScreenCLI implements Screen {

    private HashMap<String, View> views = new HashMap<String, View>();
    private View activeView;
    private UserInterpreter userInterpreter;
    private String errorMessage = "";
    private boolean started = false;

    private Thread reader = new Thread(() -> {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            userInterpreter.execute(command);
        }
    }); //this thread always reads user inputs


    /**
     * Class constructor
     */
    public ScreenCLI() {}


    /**
     * Starts the screen. It is now possible to show and refresh views.
     */
    public void start(String startingViewId) {
        started = true;

        try {
            show(startingViewId);
            refresh();
        } catch (ViewException ve){System.exit(1); /*TODO terminate*/}

        reader.start();
    }


    /**
     * Attaches the command interpreter to the screen. This method can be called only before the screen is started.
     * @param userInterpreter UserInterpreter to attach.
     */
    public void attachUserInterpreter(UserInterpreter userInterpreter) {
        if(started){
            throw new IllegalThreadStateException("Cannot attach new UserInterpreter after the call of start method on this screen");
        }
        this.userInterpreter = userInterpreter;
    }


    /**
     * Adds a view and its ID to the views the screen knows.
     * @param id ID of the new view
     * @param view actual view to add
     */
    public void addView(String id, View view) {
        views.put(id, view);
    }


    /**
     * Sets the error to show on screen.
     * @param message Brief description of the error that occurred. For example "yjasfgdiu is not a recognized command".
     */
    @Override
    public void setMessage(String message, MessageType type) {

        switch (type){
            case MESSAGE:
                this.errorMessage = FancyConsole.GREEN(message) + "\n\n";
                break;
            case ERROR:
                this.errorMessage = FancyConsole.RED(message) + "\n\n";
                break;
            case FATAL:
                this.errorMessage = FancyConsole.BACK_RED(message) + "\n\n";
                break;
            case NEUTRAL:
                this.errorMessage = message + "\n\n";
                break;
        }
    }


    /**
     * Shows the specified view. Cannot be called before the screen is started.
     * @param view ID of the view to show
     * @throws ViewException if we don't have any activeView  to show
     */
    @Override
    public void show(String view) throws ViewException {
        if(!started){
            throw new IllegalThreadStateException("Cannot show anything until the screen has been started");
        }

        errorMessage = ""; //reset the error message

        activeView = views.get(view); //set active view
        if(activeView == null){
            throw new ViewException(view + " is not a known view");
        }
    }


    /**
     * Refreshes the current view. Useful when there is an update from the model. Cannot be called before the screen is started.
     */
    @Override
    public void refresh() {
        if(!started){
            throw new IllegalThreadStateException("Cannot show anything until the screen has been started");
        }

        FancyConsole.clear();
        System.out.print(errorMessage + activeView);
    }


    /**
     * Shows the players and their status as a message.
     * @param players Players' names and their status
     */
    @Override
    public void setPlayers(String players) {
        String[] namesAndStatus = players.split(" ");
        StringBuilder toPrint = new StringBuilder();

        for (int i = 0; i < namesAndStatus.length-1; i+=2){
            toPrint.append(namesAndStatus[i].equals("curr") ? FancyConsole.FRAMED(" " + FancyConsole.GREEN(" " + namesAndStatus[i+1] + " "))
                                                            : namesAndStatus[i].equals("on")
                                                                ? FancyConsole.FRAMED(" " + namesAndStatus[i+1] + " ")
                                                                : FancyConsole.FRAMED(FancyConsole.RED(" " + namesAndStatus[i+1] + " ")));
        }

        toPrint.append("\t\t" + FancyConsole.UNDERLINED(FancyConsole.CYAN(" " + namesAndStatus[namesAndStatus.length-1] + " ")));

        setMessage(toPrint.toString(), MessageType.NEUTRAL);
        refresh();
    }
}
