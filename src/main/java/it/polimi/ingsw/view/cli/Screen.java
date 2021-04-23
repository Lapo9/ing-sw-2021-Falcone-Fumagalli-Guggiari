package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;

import java.util.HashMap;
import java.util.Scanner;


/**
 * A Screen is responsible for showing to the user the right information, and for receiving from the user the commands.
 */
public class Screen {

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
     * Constructor
     */
    public Screen() {}


    /**
     * Starts the screen. It is now possible to show and refresh views.
     */
    public void start(String startingViewId) {
        started = true;

        try {
            show(startingViewId);
        } catch (ViewException ve){/*TODO terminate*/}

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
    public void setErrorMessage(String message) {
        errorMessage = message + "\n\n";
    }


    /**
     * Shows the specified view. Cannot be called before the screen is started.
     * @param view ID of the view to show
     */
    public synchronized void show(String view) throws ViewException {
        if(!started){
            throw new IllegalThreadStateException("Cannot show anything until the screen has been started");
        }

        errorMessage = ""; //reset the error message

        activeView = views.get(view); //set active view
        if(activeView == null){
            throw new ViewException(view.toString() + " is not a known view");
        }

        //TODO clear console
        System.out.print(errorMessage + activeView.toString());
    }


    /**
     * Refreshes the current view. Useful when there is an update from the model. Cannot be called before the screen is started.
     */
    public synchronized void refresh() {
        if(!started){
            throw new IllegalThreadStateException("Cannot show anything until the screen has been started");
        }

        //TODO clear console
        System.out.print(errorMessage + activeView.toString());
    }


}
