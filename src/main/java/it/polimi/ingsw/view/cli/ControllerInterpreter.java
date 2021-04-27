package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;


/**
 * The class is responsible for the interpretation of commands related to what to show on the screen. These commands are mostly issued by the server.
 */
public class ControllerInterpreter {

    private Screen screen;


    /**
     * Creates the interpreter and attaches the screen to it.
     * @param screen Place where to show the commands received.
     */
    public ControllerInterpreter(Screen screen) {
        this.screen = screen;
    }



    /**
     * Executes the command specified by the caller (the server most of the cases).
     * A server can only tells the CLI what view to show, the error message to show and to refresh the screen.
     * Any other command results in the end of the program.
     * @param command Command of the server
     */
    public synchronized void execute(String command){
        String[] tokens = command.split(" ");

        if(tokens[0].equals("show")){
            try {
                screen.show(tokens[1]);
            } catch (ViewException ve){/*TODO terminate*/}
        }
        else if(tokens[0].equals("error")){
            StringBuilder errorMessage = new StringBuilder("");
            for (int i=1; i<tokens.length; ++i){
                errorMessage.append(tokens[i]);
                errorMessage.append(" ");
            }

            screen.setErrorMessage(errorMessage.toString());
            screen.refresh();
        }
        else if(tokens[0].equals("refresh")){
            screen.refresh();
        }
        else {
            //TODO terminate
        }
    }
}
