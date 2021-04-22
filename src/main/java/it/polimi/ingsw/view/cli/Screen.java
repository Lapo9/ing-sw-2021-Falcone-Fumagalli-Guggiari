package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;

import java.util.HashMap;
import java.util.Scanner;


/**
 * A Screen is responsible for showing to the user the right information, and for receiving from the user the commands.
 */
public class Screen {

    private HashMap<ViewId, View> views = new HashMap<ViewId, View>();
    private View activeView;
    private CommandInterpreter userInterpreter = new CommandInterpreter();
    private String errorMessage = "";

    private Thread reader = new Thread(() -> {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            userInterpreter.execute(command);
        }
    }); //this thread always reads user inputs


    /**
     * Constructor
     * @param id ID of the welcome screen
     * @param startingView Actual welcome screen
     */
    public Screen(ViewId id, View startingView) {
        addView(id, startingView);
        try {
            show(id);
        } catch (ViewException ve){/*TODO terminate*/}

        reader.start();
    }


    /**
     * Adds a view and its ID to the views the screen knows.
     * @param id
     * @param view
     */
    public void addView(ViewId id, View view) {
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
     * Shows the specified view.
     * @param view ID of the view to show
     */
    public synchronized void show(ViewId view) throws ViewException {
        activeView = views.get(view); //set active view
        if(activeView == null){
            throw new ViewException(view.toString() + " is not a known view");
        }

        //TODO clear console
        System.out.print(errorMessage + activeView.toString());

        errorMessage = ""; //reset the error message
    }


    /**
     * Refreshes the current view. Useful when there is an update from the model.
     */
    public synchronized void update() {
        activeView = views.get(view); //set active view
        //TODO clear console
        System.out.print(errorMessage + activeView.toString());
    }


}
