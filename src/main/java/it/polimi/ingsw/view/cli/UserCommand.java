package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.Arrays;

public class UserCommand {

    private String command;
    private ArrayList<String> arguments;
    private boolean isServerOperation;



    public UserCommand(boolean isServerOperation, String command, String... arguments){
        this.command = command;
        this.arguments = new ArrayList<String>(Arrays.stream(arguments).toList());
        this.isServerOperation = isServerOperation;
    }


    public int getArgsCount() {
        return arguments.size();
    }


    public String toString() {
        return command;
    }


    public String getArg(int i) {
        return arguments.get(i);
    }


    public boolean isServerOperation() {
        return isServerOperation;
    }

}
