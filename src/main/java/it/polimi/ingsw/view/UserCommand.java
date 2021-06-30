package it.polimi.ingsw.view;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represent the right syntax for a command the user can send.
 */
public class UserCommand {

    private String command;
    private ArrayList<ArrayList<String>> arguments = new ArrayList<>();
    private boolean isServerOperation;


    /**
     * Creates a user command.
     * @param isServerOperation Whether this command must go to the server to be executed or not
     * @param command String that represents the command
     * @param arguments Each argument contains the list of strings that can be used as argument. The order of the arguments matters.
     */
    public UserCommand(boolean isServerOperation, String command, ArrayList<String>... arguments){
        this.command = command;
        this.isServerOperation = isServerOperation;

        for (ArrayList<String> argument : arguments){
            ArrayList<String> currentArg = new ArrayList<String>();
            this.arguments.add(currentArg);
            for (String possibleArg : argument){
                currentArg.add(possibleArg);
            }
        }
    }


    /**
     * Returns how many arguments this command requires
     * @return How many arguments this command requires
     */
    public int getArgsCount() {
        return arguments.size();
    }


    /**
     * Returns the name of the command.
     * @return The name of the command
     */
    public String toString() {
        return command;
    }

    /**
     * Returns the list of strings that can be used as i-th argument
     * @param i Argument to return
     * @return The list of strings that can be used as i-th argument
     */
    public ArrayList<String> getArg(int i) {
        return arguments.get(i);
    }


    /**
     * Returns whether this command must go to the server to be executed or not
     * @return Whether this command must go to the server to be executed or not
     */
    public boolean isServerOperation() {
        return isServerOperation;
    }

}
