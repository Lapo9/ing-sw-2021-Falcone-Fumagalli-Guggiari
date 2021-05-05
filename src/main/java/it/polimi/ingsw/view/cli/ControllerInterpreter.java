package it.polimi.ingsw.view.cli;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

import it.polimi.ingsw.view.cli.exceptions.ViewException;


/**
 * The class is responsible for the interpretation of commands related to what to show on the screen. These commands are mostly issued by the server.
 */
public class ControllerInterpreter {

    private Screen screen;
    private OfflineInfo offlineInfo;


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

            screen.setErrorMessage(RED(errorMessage.toString()));
            screen.refresh();
        }
        else if(tokens[0].equals("message")){
            StringBuilder errorMessage = new StringBuilder("");
            for (int i=1; i<tokens.length; ++i){
                errorMessage.append(tokens[i]);
                errorMessage.append(" ");
            }

            screen.setErrorMessage(GREEN(errorMessage.toString()));
            screen.refresh();
        }
        else if(tokens[0].equals("fatal")){
            StringBuilder errorMessage = new StringBuilder("");
            for (int i=1; i<tokens.length; ++i){
                errorMessage.append(tokens[i]);
                errorMessage.append(" ");
            }

            execute("show welcome");
            screen.setErrorMessage(BACK_RED(errorMessage.toString()));
            screen.refresh();
        }
        else if(tokens[0].equals("refresh")){
            screen.refresh();
        }
        else if(tokens[0].equals("start")){
            try {
                screen.show("start");
            } catch (ViewException ve){
                ve.printStackTrace();//TODO terminate
                return;
            }
            execute("message You are player " + tokens[1] + "!");
        }
        else if(tokens[0].equals("win")){
            try {
                screen.show("win");
            } catch (ViewException ve){
                //TODO terminate
                return;
            }
            execute("message " + tokens[1] + "won the match!!");
        }
        else if (tokens[0].equals("yourTurn")) {
            try {
                screen.show("yourTurn");
            } catch (ViewException ve){
                //TODO terminate
                return;
            }
        }
        else if (tokens[0].equals("activateProduction")){
            offlineInfo.setProduction(tokens[1], true);
        }
        else if (tokens[0].equals("deactivateProduction")){
            offlineInfo.setProduction(tokens[1], false);
        }
        else {
            //TODO terminate
        }
    }
}
