package it.polimi.ingsw.view;

import it.polimi.ingsw.view.OfflineInfo;
import it.polimi.ingsw.view.Screen;
import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.gui.MessageType;
import it.polimi.ingsw.view.gui.controllers.ResettableScene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


/**
 * The class is responsible for the interpretation of commands related to what to show on the screen. These commands are mostly issued by the server.
 */
public class ControllerInterpreter {

    private Screen screen;
    private HashMap<String, Consumer<String[]>> knownCommands = new HashMap<>();
    private OfflineInfo offlineInfo;
    private ArrayList<ResettableScene> toReset = new ArrayList<>();


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


    /**
     * Shows the View contained in tokens[1]
     * @param tokens array containing the View to show
     */
    private void show(String... tokens){
        if (offlineInfo.isMatchStarted() || !(tokens[1].equals("dashboard") || tokens[1].equals("faithTrack") || tokens[1].equals("marketplace") || tokens[1].equals("developmentGrid"))) {
            try {
                screen.show(tokens[1]);
                screen.refresh();
            } catch (ViewException ve) {
                execute("fatal Sorry, we had a problem, please retry, and if the problem keeps happening try to disconnect and reconnect");
            }
        }

        else execute("error Connect to a match first! Use command connect serverIP portNumber username matchID");
    }

    /**
     * Hides the viewable contained in tokens[1]
     * @param tokens array containing the View to hide
     */
    private void hide(String... tokens){
        screen.hide(tokens[1]);
    }

    /**
     * Allows to show the other player's dashboard
     * @param tokens array containing the name of the player to spy
     */
    private void spy(String... tokens) {
        if (tokens[1].equals(offlineInfo.getYourName())) {
            execute("show dashboard");
            return;
        }
        execute("show opponent" + offlineInfo.getOpponentOrder(tokens[1]));
    }

    /**
     * Allows to show all the errors encountered
     * @param tokens array composed by the error message
     */
    private void error(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        screen.setMessage(errorMessage.toString(), MessageType.ERROR);
        screen.refresh();
    }

    /**
     * Allows to show messages
     * @param tokens array composed by the message to show
     */
    private void message(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        screen.setMessage(errorMessage.toString(), MessageType.MESSAGE);
        screen.refresh();
    }

    /**
     * Stops the match and shows fatal errors
     * @param tokens array composed by the fatal error message
     */
    private void fatal(String... tokens){
        StringBuilder errorMessage = new StringBuilder("");
        for (int i=1; i<tokens.length; ++i){
            errorMessage.append(tokens[i]);
            errorMessage.append(" ");
        }

        offlineInfo.setMatchStarted(false);
        execute("show welcome");
        screen.setMessage(errorMessage.toString(), MessageType.FATAL);
        screen.refresh();

        System.exit(0);
    }

    /**
     * Allows to refresh the screen (and every viewable on it)
     * @param tokens
     */
    private void refresh(String... tokens){
        screen.refresh();
    }

    /**
     * Starts the match
     * @param tokens array composed by the player's name and number
     */
    private void start(String... tokens){
        offlineInfo.setPlayers(Arrays.copyOfRange(tokens, 2, tokens.length));
        offlineInfo.setMatchStarted(true);
        execute("message You are player " + tokens[1] + "!");
    }

    /**
     * Ends the match and declares the winner
     * @param tokens array composed by the player number which will visualize the message and the number of the winner
     */
    private void win(String... tokens){
        try {
            screen.show("endMatch");
        } catch (ViewException ve){
            System.exit(1); //TODO terminate
            return;
        }
        execute("message " + tokens[1] + " won the match!!");

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException ie){
                System.exit(1);
            }
            System.exit(0);
        }).start();
    }

    /**
     * Shows to the player the start of his turn
     * @param tokens
     */
    private void yourTurn(String... tokens){
        try {
            screen.show("yourTurn");
        } catch (ViewException ve){
            execute("message It's your turn");
        }
    }

    /**
     * Activate the selected production
     * @param tokens array composed by the production to activate
     */
    private void activateProduction(String... tokens){
        offlineInfo.setProduction(tokens[1], true);
    }

    /**
     * Deactivate the selected production
     * @param tokens array composed by the production to deactivate
     */
    private void deactivateProduction(String... tokens){
        offlineInfo.setProduction(tokens[1], false);
    }

    /**
     * Sets the number of players and their names
     * @param tokens array composed by the players' name
     */
    private void setPlayers(String... tokens){
        StringBuilder toSend = new StringBuilder();

        for (int i = 1; i < tokens.length; ++i){
            toSend.append(tokens[i] + " ");
        }

        screen.setPlayers(toSend.toString());
    }

    /**
     * Resets the warehouse putting in order the resources and empty the paychecks returning all the resources to their places
     * @param tokens
     */
    private void reset(String... tokens){
        offlineInfo.setSelectedWarehouseRow("");
        offlineInfo.setSelectedItem("");
        for (ResettableScene rs : toReset){
            rs.reset();
        }
    }

    /**
     *
     * @param tokens
     */
    private void setActive(String... tokens){
        for(ResettableScene rs : toReset){
            rs.setActive(Arrays.copyOfRange(tokens, 1, tokens.length));
        }
    }

    /**
     * Shows the list of commands to the player
     * @param tokens 
     */
    private void help(String... tokens){
        try {
            screen.show("help");
            screen.refresh();
        } catch (ViewException ve){
            System.exit(1); //TODO terminate
            return;
        }
    }


    /**
     * Sets the standard commands the server can send to the view
     */
    private void fillStandardKnownCommands(){
        knownCommands.put("show", this::show);
        knownCommands.put("hide", this::hide);
        knownCommands.put("spy", this::spy);
        knownCommands.put("error", this::error);
        knownCommands.put("message", this::message);
        knownCommands.put("fatal", this::fatal);
        knownCommands.put("refresh", this::refresh);
        knownCommands.put("u", this::refresh);
        knownCommands.put("start", this::start);
        knownCommands.put("win", this::win);
        knownCommands.put("yourTurn", this::yourTurn);
        knownCommands.put("activateProduction", this::activateProduction);
        knownCommands.put("deactivateProduction", this::deactivateProduction);
        knownCommands.put("setPlayers", this::setPlayers);
        knownCommands.put("reset", this::reset);
        knownCommands.put("setActive", this::setActive);
        knownCommands.put("help", this::help);
    }


    /**
     *
     * @param rs
     */
    public void attachToResetScene(ResettableScene rs){
        toReset.add(rs);
    }

}
