package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.ClientSocket;
import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.ModelInterpreter;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;


/**
 * Class responsible for the communication between this client and the server.
 */
public class ServerSocket {

    private ClientSocket socket;
    private ControllerInterpreter controllerInterpreter;
    private ModelInterpreter modelInterpreter;
    private boolean connected = false;
    private boolean destroy = false;

    private Thread listenRoutineThread;
    private Thread heartbeatThread;


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
     * @param isSinglePlayer whether the match is a single player match or not
     */
    public synchronized void connect(String ip, int port, String playerName, String matchId, String isSinglePlayer){
        if(controllerInterpreter == null || modelInterpreter == null){
            throw new IllegalThreadStateException("Cannot connect without an interpreter");
        }
        else if(isConnected()){
            controllerInterpreter.execute("error You are already connected!");
            return;
        }

        setDestroy(false);

        try {
            //connect
            socket = new ClientSocket(ip, port);
            //send player name, if the send fails, terminate
            if(!send("name " + playerName + " " + matchId + " " + isSinglePlayer)){
                return;
            }

            //wait for server response (max 10 seconds)
            String response = socket.receiveAndTransformWithType(10000, ClientSocket::bytesToString).second;
            if (isError(response)){
                terminate(response);
                return;
            }
            controllerInterpreter.execute(response);
        } catch (Exception e) {
            terminate("fatal Server is down :(");
            return;
        }

        setConnected(true);

        //start listening
        listenRoutineThread = new Thread(this::socketListenRoutine);
        heartbeatThread = new Thread(this::heartbeat);
        listenRoutineThread.start();
        heartbeatThread.start();
    }


    public synchronized boolean isConnected(){
        return connected;
    }

    private synchronized void setConnected(boolean c){
        connected = c;
    }


    private synchronized boolean isDestroyed(){
        return destroy;
    }

    private synchronized boolean setDestroy(boolean d){
        boolean res = d != destroy;
        destroy = d;
        return res; //whether it was this call to change the status or not
    }



    private boolean isError(String message){
        String command = message.split(" ")[0];
        if (command.equals("error") || command.equals("fatal")){
            return true;
        }
        return false;
    }

    private boolean isFatal(String message){
        String command = message.split(" ")[0];
        if (command.equals("fatal")){
            return true;
        }
        return false;
    }

    private boolean isECG(String message){
        String command = message.split(" ")[0];
        if (command.equals("ECG")){
            return true;
        }
        return false;
    }



    /**
     * Attaches the component responsible to interpret the commands from the controller
     * @param interpreter the controller interpreter
     */
    public void attachInterpreter(ControllerInterpreter interpreter){
        if(isConnected()){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }
        controllerInterpreter = interpreter;
    }


    /**
     * Attaches the component responsible to interpret the commands (mostly updates) from the model
     * @param interpreter the model interpreter
     */
    public void attachInterpreter(ModelInterpreter interpreter){
        if(isConnected()){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }
        modelInterpreter = interpreter;
    }


    /**
     * Sends the specified message to the server.
     * @param message Message to send
     */
    public boolean send(String message) {
        try {
            socket.send(message, ClientSocket.packUpStringWithLength());
        } catch (IOException ioe) {
            terminate("fatal Server is down :(");
            return false;
        }
        return true;
    }



    /*
    Send an ACK once every 4 seconds in order to keep the connection to the server alive
     */
    private void heartbeat() {
        while (!isDestroyed()) {
            try {
                socket.send("ECG", ClientSocket.packUpStringWithLength());
            } catch (IOException ioe) {
                terminate("fatal Something went wrong :(");
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
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
        while (!isDestroyed()) {
            try {
                Pair<Byte, byte[]> tmp;
                try {
                     tmp = socket.receiveWithType(10000); //cli expects to receive an ECG each 10 seconds
                } catch (SocketTimeoutException ste){
                    terminate("fatal Server doesn't respond"); //if you don't receive any message in 10 seconds
                    return;
                }
                byte type = tmp.first;
                byte[] message = tmp.second;

                if (type == 0) {
                    String stringMessage = ClientSocket.bytesToString(message);
                    if(isFatal(stringMessage)){
                        terminate(stringMessage);
                    }
                    else if(!isECG(stringMessage)) {
                        controllerInterpreter.execute(ClientSocket.bytesToString(message));
                    }
                }
                else if (type == 1) {
                    modelInterpreter.update(ClientSocket.bytesToInts(message));
                }
                else if (type == 2) {
                    terminate("fatal Server closed connection");
                }

            } catch (IOException ioe) {
                terminate("fatal Something went wrong :(");
            }
        }
    }




    private void terminate(String fatalMessage) {
        if (setDestroy(true)) {
            //a new thread must wait for the joins, in order not to create deadlocks
            new Thread(() -> {
                try {
                    socket.close();
                    //wait for the threads to join
                    listenRoutineThread.join();
                    heartbeatThread.join();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                controllerInterpreter.execute(fatalMessage);
                setConnected(false);
            }).start();
        }
    }

}
