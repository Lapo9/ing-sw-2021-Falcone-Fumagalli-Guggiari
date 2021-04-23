package it.polimi.ingsw.view.cli;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * A CommandInterpreter is responsible for the translation of user commands to commands readable by the server or the server interpreter.
 * It also checks if the command submitted by the user is syntactically correct.
 */
public class CommandInterpreter {

    //TODO private Socket serverSocket;
    private Set<UserCommand> commands;
    private ServerInterpreter serverInterpreter;


    /**
     * Creates an interpreter and adds a standard set of known user commands.
     * @param serverInterpreter Where to send short circuit commands (commands which don't need the server to be executed)
     */
    public CommandInterpreter(ServerInterpreter serverInterpreter) {
        this.serverInterpreter = serverInterpreter;
        this.commands = allCommands();
    }


    /**
     * Constructor.
     * @param serverInterpreter Where to send short circuit commands (commands which don't need the server to be executed)
     * @param knownCommands User commands known to the interpreter
     */
    public CommandInterpreter(ServerInterpreter serverInterpreter, Set<UserCommand> knownCommands) {
        this.serverInterpreter = serverInterpreter;
        this.commands = knownCommands;
    }


    /**
     * Executes the command submitted by the user. If the command is not syntactically correct, show an error message on the screen.
     * @param userCommand Command submitted by the user
     */
    public void execute(String userCommand) {
        String[] tokens = userCommand.split(" ");

        //check if the command is syntactically correct
        String error = checkSyntacticalCorrectness(tokens);
        if(error.equals("OK")) {
            //check if the server is required
            if (commands.stream().filter(command -> command.toString().equals(tokens[0])).collect(Collectors.toList()).get(0).isServerOperation()) {
                //TODO send the command to the socket
            }
            else {
                serverInterpreter.execute(userCommand); //since for this command the server isn't required (for example "show player2dashboard")
            }
        }
        else {
            serverInterpreter.execute("error "+error);
        }
    }


    //checks if the command is legal
    private String checkSyntacticalCorrectness(String[] tokens) {
        List<UserCommand> tmp = commands.stream().filter(command -> command.toString().equals(tokens[0])).collect(Collectors.toList());
        //check if the command exists and if the arguments count is correct
        if(tmp.size() == 1 && tmp.get(0).getArgsCount() == tokens.length-1) {
            UserCommand actualCommand = tmp.get(0);
            for (int i=0; i<actualCommand.getArgsCount(); ++i) {
                //check if the arguments are appropriate for the command
                if(!actualCommand.getArg(i).equals(tokens[i+1])){
                    return tokens[i+1] + " is not an appropriate argument for the command " + actualCommand.toString();
                }
            }
            return "OK";
        }
        else {
            return tmp.get(0).toString() + " is not a recognized command";
        }
    }


    //inserts all known commands
    private static Set<UserCommand> allCommands() {
        Set<UserCommand> commands = new HashSet<>();
        //TODO add all of the commands!
        return commands;
    }


}
