package it.polimi.ingsw.view.cli;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ControllerSocket {

    Socket socket;
    ControllerInterpreter controllerInterpreter;
    boolean connected = false;


    public ControllerSocket(){}


    public void connect(String ip, int port, String playerName){
        if(controllerInterpreter == null){
            throw new IllegalThreadStateException("Cannot connect without an interpreter");
        }

        try {
            socket = new Socket(ip, port);
        } catch (IOException ioe) {
            controllerInterpreter.execute("error Server is full or we messed up (probably the second one...");
        }

        //send player name
        sendMessage("name " + playerName);

        //start listening
        new Thread(this::socketListenRoutine).start();

        connected = true;
    }


    public void attachInterpreter(ControllerInterpreter interpreter){
        if(connected){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }
        controllerInterpreter = interpreter;
    }


    private void socketListenRoutine() {

        while (true) {
            StringBuilder message = new StringBuilder(); //string where to put the message

            try {
                InputStream in = socket.getInputStream();
                DataInputStream dataIn = new DataInputStream(in);

                //get the length of the message tp be received (first byte)
                int length = dataIn.readByte();
                byte[] byteString = new byte[9700]; //TODO check max bytes for TCP packet
                int byteReadTot = 0; //number of bytes read
                boolean done = false; //we read all of the bytes?
                while (!done){
                    int currentBytesRead = dataIn.read(byteString); //read as many bytes as you can and store how many you read
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

            } catch (IOException ioe){/*TODO terminate*/}


            controllerInterpreter.execute(message.toString());

            if(message.equals("exit")){
                break;
            }
        }

    }


    public synchronized void sendMessage(String message) {
        byte[] stringByte = message.getBytes(StandardCharsets.UTF_8); //convert the message to bytes
        byte[] stringWithLength = new byte[message.length()+1]; //create a new array where to save the length of the message + the message
        stringWithLength[0] = (byte) message.length(); //add the length of the message as first element

        //copy the message
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

}
