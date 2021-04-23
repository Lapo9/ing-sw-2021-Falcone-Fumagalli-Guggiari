package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.Arrays;

public class UserCommand {

    private String command;
    private ArrayList<ArrayList<String>> arguments = new ArrayList<>();
    private boolean isServerOperation;



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


    public int getArgsCount() {
        return arguments.size();
    }


    public String toString() {
        return command;
    }


    public ArrayList<String> getArg(int i) {
        return arguments.get(i);
    }


    public boolean isServerOperation() {
        return isServerOperation;
    }

}
