package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.ClientSocket;
import it.polimi.ingsw.Pair;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


/**
 * Class responsible for the communication between this client and the server.
 */
public class ServerSocket {

    private ClientSocket socket;
    private ControllerInterpreter controllerInterpreter;
    private ModelInterpreter modelInterpreter;
    private boolean connected = false;


    /**
     * Creates an empty, non-connected socket
     */
    public ServerSocket(){
        super();
    }



    /**
     * Starts the connection with the specified server
     * @param ip IP address of the server
     * @param port Port of the server
     * @param playerName Name of the player
     */
    public void connect(String ip, int port, String playerName, String matchId){
        if(controllerInterpreter == null || modelInterpreter == null){
            throw new IllegalThreadStateException("Cannot connect without an interpreter");
        }
        else if(connected){
            controllerInterpreter.execute("error You are already connected!");
            return;
        }


        try {
            //connect
            socket = new ClientSocket(ip, port);
            //send player name
            socket.send("name " + playerName + " " + matchId, ClientSocket.packUpStringWithLength());

            //wait for server response (max 10 seconds)
            String response = socket.receiveAndTransformWithType(10000, ClientSocket::bytesToString).second;
            if (isError(response)){
                terminate();
                return;
            }
            controllerInterpreter.execute(response);
        } catch (IOException ioe) {
            terminate();
            return;
        }

        connected = true;

        //start listening
        new Thread(this::socketListenRoutine).start();
        new Thread(this::keepConnectionAlive).start();
    }



    private boolean isError(String message){
        String command = message.split(" ")[0];
        if (command.equals("error") || command.equals("fatal")){
            return true;
        }
        return false;
    }



    /**
     * Attaches the component responsible to interpret the commands from the controller
     * @param interpreter the controller interpreter
     */
    public void attachInterpreter(ControllerInterpreter interpreter){
        if(connected){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }
        controllerInterpreter = interpreter;
    }


    /**
     * Attaches the component responsible to interpret the commands (mostly updates) from the model
     * @param interpreter the model interpreter
     */
    public void attachInterpreter(ModelInterpreter interpreter){
        if(connected){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }
        modelInterpreter = interpreter;
    }


    /**
     * Sends the specified message to the server.
     * @param message Message to send
     */
    public void send(String message) {
        try {
            socket.send(message, ClientSocket.packUpStringWithLength());
        } catch (IOException ioe) {
            terminate();
        }
    }



    /*
    Send an ACK once every 4 seconds in order to keep the connection to the server alive
     */
    private void keepConnectionAlive() {
        while (connected) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            try {
                socket.send("ECG", ClientSocket.packUpStringWithLength());
            } catch (IOException ioe) {
                terminate();
            }
        }
    }



    /*
Structure of the packet

     byte 0   byte 1   byte 2   byte 3   byte ...
    +--------+--------+--------+--------+------
    | length | type   | data   | data   | ...
    +--------+--------+--------+--------+------
*/
    private void socketListenRoutine() {
        boolean exit = false;
        while (connected) {
            try {
                Pair<Byte, byte[]> tmp = socket.receiveWithType();
                byte type = tmp.first;
                byte[] message = tmp.second;

                if (type == 0) {
                    controllerInterpreter.execute(ClientSocket.bytesToString(message));
                }
                else if (type == 1) {
                    modelInterpreter.update(ClientSocket.bytesToInts(message));
                }
                else if (type == 2) {
                    terminate();
                }

            } catch (IOException ioe) {
                terminate();
            }
        }
    }




    private synchronized void terminate() {
        connected = false;
        try {
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        controllerInterpreter.execute("error Something went wrong :(");
    }

}
