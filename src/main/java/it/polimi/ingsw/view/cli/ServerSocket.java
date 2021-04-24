package it.polimi.ingsw.view.cli;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * Class responsible for the communication between this client and the server.
 */
public class ServerSocket {

    private Socket socket;
    private ControllerInterpreter controllerInterpreter;
    private ModelInterpreter modelInterpreter;
    private boolean connected = false;


    /**
     * Creates an empty, non-connected socket
     */
    public ServerSocket(){}



    /**
     * Starts the connection with the specified server
     * @param ip IP address of the server
     * @param port Port of the server
     * @param playerName Name of the player
     */
    public void connect(String ip, int port, String playerName){
        if(controllerInterpreter == null || modelInterpreter == null){
            throw new IllegalThreadStateException("Cannot connect without an interpreter");
        }
        else if(connected){
            controllerInterpreter.execute("error You are already connected!");
            return;
        }

        //connect
        try {
            socket = new Socket(ip, port);
        } catch (IOException ioe) {
            controllerInterpreter.execute("error Server is full or we messed up (probably the second one...)");
            return;
        }

        //send player name
        sendMessage("name " + playerName);

        //start listening
        new Thread(this::socketListenRoutine).start();

        connected = true;
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
     * Sends a message to the server in the right format.
     * @param message Message to send
     */
    public synchronized void sendMessage(String message) {
        //the format is the actual message, preceded by its length in bytes
        byte[] stringByte = message.getBytes(StandardCharsets.UTF_8); //convert the message to bytes
        byte[] stringWithLength = new byte[message.length()+1]; //create a new array where to save the length of the message + the message
        stringWithLength[0] = (byte) message.length(); //add the length of the message as first element

        //copy the message in the new array
        for(int i=0; i<stringByte.length; ++i){
            stringWithLength[i+1] = stringByte[i];
        }

        //send the new message
        try {
            OutputStream out = socket.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.write(stringWithLength);
        } catch (IOException ioe) {/*TODO terminate??*/}

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
        while (!exit) {
            try {
                InputStream in = socket.getInputStream();
                DataInputStream dataIn = new DataInputStream(in);

                int length = dataIn.readByte(); //get the length of the message tp be received (first byte)
                int type = dataIn.readByte(); //get the type of message (0 = controller, 1 = model)

                if (type == 0) {
                    controllerInterpreter.execute(readControllerMessage(dataIn, length));
                }
                else if (type == 1) {
                    modelInterpreter.update(readModelMessage(dataIn, length));
                }
                else if (type == 2) {
                    exit = true;
                }

            } catch (IOException ioe) {/*TODO terminate*/}
        }
    }


    /*
    Reads the message on the socket if it is a message from the controller
     */
    private String readControllerMessage(DataInputStream dataIn, int length){
        StringBuilder message = new StringBuilder(); //string where to put the message

        byte[] byteString = new byte[9700]; //TODO check max bytes for TCP packet
        int byteReadTot = 0; //number of bytes read
        boolean done = false; //we read all of the bytes?

        while (!done){
            int currentBytesRead = 0;
            try{
                currentBytesRead = dataIn.read(byteString); //read as many bytes as you can and store how many you read
            } catch (IOException ioe){/*TODO terminate*/}
            byteReadTot += currentBytesRead; //add the read bytes to the total

            //if we read less bytes than the length of the message, put all of the just read bytes in the message string
            if (byteReadTot <= length){
                message.append(new String(byteString, 0, currentBytesRead, StandardCharsets.UTF_8));
            }
            //if we read too many bytes, store in the string only the necessary bytes, and discard what remains
            else {
                message.append(new String(byteString, 0, byteReadTot - length, StandardCharsets.UTF_8));
            }

            //if we read everything, exit the loop
            if(message.length() == length){
                done = true;
            }
        }

        return message.toString();
    }


    /*
    Reads the message on the socket if it is a message from the model
     */
    private int[] readModelMessage(DataInputStream dataIn, int length) {
        length /= 4; //length is in bytes (8 bits), but we read ints (32 bit)

        int[] intIn = new int[length]; //create array where to store what we read

        try {
            for (int i = 0; i < intIn.length; ++i) {
                intIn[i] = dataIn.readInt(); //read all ints
            }
        } catch (IOException ioe){/*TODO terminate*/}

        return intIn;
    }

}
