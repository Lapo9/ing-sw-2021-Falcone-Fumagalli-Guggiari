package it.polimi.ingsw.view.cli;


import it.polimi.ingsw.ClientSocket;

import java.util.*;
import java.util.stream.Collectors;


/**
 * A UserInterpreter is responsible for the translation of user commands to commands readable by the server or the server interpreter.
 * It also checks if the command submitted by the user is syntactically correct.
 */
public class UserInterpreter {

    private ServerSocket socket;
    private Set<UserCommand> commands;
    private ControllerInterpreter controllerInterpreter;


    /**
     * Creates an interpreter and adds a standard set of known user commands.
     * @param controllerInterpreter Where to send short circuit commands (commands which don't need the server to be executed)
     */
    public UserInterpreter(ControllerInterpreter controllerInterpreter, ServerSocket socket) {
        this.socket = socket;
        this.controllerInterpreter = controllerInterpreter;
        this.commands = allCommands();
    }


    /**
     * Constructor.
     * @param controllerInterpreter Where to send short circuit commands (commands which don't need the server to be executed)
     * @param knownCommands User commands known to the interpreter
     */
    public UserInterpreter(ControllerInterpreter controllerInterpreter, Set<UserCommand> knownCommands) {
        this.controllerInterpreter = controllerInterpreter;
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
            //check if it is a connection request
            if (tokens[0].equals("connect")){
                socket.connect(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]); //connect to the specified server
            }
            //check if the server is required
            else if (commands.stream().filter(command -> command.toString().equals(tokens[0])).collect(Collectors.toList()).get(0).isServerOperation()) {
                socket.send(userCommand);
            }
            else {
                controllerInterpreter.execute(userCommand); //since for this command the server isn't required (for example "show player2dashboard")
            }
        }
        else {
            controllerInterpreter.execute("error "+error);
        }
    }


    //checks if the command is legal
    private String checkSyntacticalCorrectness(String[] tokens) {
        List<UserCommand> tmp = commands.stream().filter(command -> command.toString().equals(tokens[0])).collect(Collectors.toList());
        //check if the command exists and if the arguments count is correct
        if (tmp.size() != 1) {
            return "\"" + tokens[0] + "\" is not a recognized command";
        }
        else if (tmp.get(0).getArgsCount() != tokens.length-1) {
            return "Too many/few arguments for the command \"" + tokens[0] + "\"";
        }
        else  {
            UserCommand actualCommand = tmp.get(0);
            //connect is the only command that doesn't have a proper list of arguments (we cannot list all of the possible IPs or names obviously)
            if (actualCommand.toString().equals("connect")){
                return "OK";
            }

            for (int i=0; i<actualCommand.getArgsCount(); ++i) {
                //check if the arguments are appropriate for the command
                if(!actualCommand.getArg(i).contains(tokens[i+1])){
                    return "\"" + tokens[i+1] + "\" is not an appropriate argument for the command \"" + actualCommand.toString() + "\"";
                }
            }
            return "OK";
        }
    }


    //inserts all known commands
    private static Set<UserCommand> allCommands() {
        Set<UserCommand> commands = new HashSet<>();
        //TODO add all of the commands!
        commands.add(new UserCommand(false, "show", new ArrayList<>(Arrays.asList("ViewTest1", "ViewTest2")))); //TODO test to eliminate
        commands.add(new UserCommand(false, "connect", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        commands.add(new UserCommand(true, "info"));
        commands.add(new UserCommand(true, "start"));
        commands.add(new UserCommand(true, "select", new ArrayList<>(Arrays.asList("coin", "servant", "shield", "stone"))));
        commands.add(new UserCommand(true, "marketplace", new ArrayList<>(Arrays.asList("h", "v")), new ArrayList<>(Arrays.asList("1", "2", "3", "4"))));
        commands.add(new UserCommand(true, "moveMarble", new ArrayList<>(Arrays.asList("yellow", "blue", "violet", "grey")), new ArrayList<>(Arrays.asList("wh1", "wh2", "wh3", "dev1", "dev2", "dev3", "leader1", "leader2"))));
        commands.add(new UserCommand(true, "colorMarble", new ArrayList<>(Arrays.asList("yellow", "blue", "violet", "grey"))));
        commands.add(new UserCommand(true, "discard"));
        commands.add(new UserCommand(true, "move", new ArrayList<>(Arrays.asList("coin", "servant", "shield", "stone")),  new ArrayList<>(Arrays.asList("wh1", "wh2", "wh3", "dev1", "dev2", "dev3", "leader1", "leader2", "paycheck", "coffer")), new ArrayList<>(Arrays.asList("wh1", "wh2", "wh3", "dev1", "dev2", "dev3", "leader1", "leader2", "paycheck", "coffer"))));
        commands.add(new UserCommand(true, "endTurn"));

        return commands;
    }


}
