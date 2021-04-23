package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.exceptions.ViewException;

public class ServerInterpreter {

    private Screen screen;



    public ServerInterpreter(Screen screen) {
        this.screen = screen;
    }



    /**
     * Executes the command specified by the caller (the server most of the cases).
     * A server can only tells the CLI what view to show and the error message to show.
     * Any other command results in the end of the program.
     * @param command Command of the server
     */
    public void execute(String command){
        String[] tokens = command.split(" ");

        if(tokens[0].equals("show")){
            try {
                screen.show(tokens[1]);
            } catch (ViewException ve){/*TODO terminate*/}
        }
        else if(tokens[0].equals("error")){
            screen.setErrorMessage(tokens[1]);
            screen.refresh();
        }
        else {
            //TODO terminate
        }
    }
}
