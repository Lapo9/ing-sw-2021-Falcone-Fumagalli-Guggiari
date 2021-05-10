package it.polimi.ingsw.view.cli;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

import it.polimi.ingsw.view.Screen;
import it.polimi.ingsw.view.cli.exceptions.ViewException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;


/**
 * The class is responsible for the interpretation of commands related to what to show on the screen. These commands are mostly issued by the server.
 */
public class ControllerInterpreter {

    private Screen screen;
    private HashMap<String, Consumer<String[]>> knownCommands = new HashMap<>();
    private OfflineInfo offlineInfo;


    /**
     * Creates the interpreter and attaches the screen to it.
     * @param screen Place where to show the commands received.
     */
    public ControllerInterpreter(Screen screen, OfflineInfo offlineInfo) {
        this.screen = screen;
        this.offlineInfo = offlineInfo;
        fillStandardKnownCommands();
    }



    /**
     * Executes the command specified by the caller (the server most of the cases).
     * A server can only tells the CLI what view to show, the error message to show and to refresh the screen.
     * Any other command results in the end of the program.
     * @param command Command of the server
     */
    public synchronized void execute(String command){
        String[] tokens = command.split(" ");

        knownCommands.get(tokens[0]).accept(tokens);
    }



    private void show(String... tokens){
        try {
            screen.show(tokens[1]);
        } catch (ViewException ve){
            execute("fatal Sorry, we had a problem, please retry, and if the problem keep happening try to disconnect and reconnect");
        }
    }

    private void spy(String... tokens){
        try {
            screen.show("player" + offlineInfo.getPlayerOrder(tokens[1]));
        } catch (ViewException ve){
            execute("fatal Sorry, we had a problem, please retry, and if the problem keep happening try to disconnect and reconnect");
        }
    }

    private void error(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        screen.setMessage(RED(errorMessage.toString()));
        screen.refresh();
    }

    private void message(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        screen.setMessage(GREEN(errorMessage.toString()));
        screen.refresh();
    }

    private void fatal(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        execute("show welcome");
        screen.setMessage(BACK_RED(errorMessage.toString()));
        screen.refresh();
    }

    private void refresh(String... tokens){
        screen.refresh();
    }

    private void start(String... tokens){
        offlineInfo.setPlayers(Arrays.copyOfRange(tokens, 2, tokens.length));
        try {
            screen.show("start");
        } catch (ViewException ve){
            execute("message Match started");
        }
        execute("message You are player " + tokens[1] + "!");
    }

    private void win(String... tokens){
        try {
            screen.show("win");
        } catch (ViewException ve){
            //TODO terminate
            return;
        }
        execute("message " + tokens[1] + "won the match!!");
    }

    private void yourTurn(String... tokens){
        try {
            screen.show("yourTurn");
        } catch (ViewException ve){
            execute("message It's your turn");
        }
    }

    private void activateProduction(String... tokens){
        offlineInfo.setProduction(tokens[1], true);
    }

    private void deactivateProduction(String... tokens){
        offlineInfo.setProduction(tokens[1], false);
    }

    private void autoRefresh(String... tokens){
        offlineInfo.setAutoRefresh(tokens[1].equals("on"));
    }



    //sets the standard commands the server can send to the view
    private void fillStandardKnownCommands(){
        knownCommands.put("show", this::show);
        knownCommands.put("spy", this::spy);
        knownCommands.put("error", this::error);
        knownCommands.put("message", this::message);
        knownCommands.put("fatal", this::fatal);
        knownCommands.put("refresh", this::refresh);
        knownCommands.put("start", this::start);
        knownCommands.put("win", this::win);
        knownCommands.put("yourTurn", this::yourTurn);
        knownCommands.put("activateProduction", this::activateProduction);
        knownCommands.put("deactivateProduction", this::deactivateProduction);
        knownCommands.put("autoRefresh", this::autoRefresh);
    }

}
