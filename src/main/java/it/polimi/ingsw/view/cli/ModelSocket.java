package it.polimi.ingsw.view.cli;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ModelSocket {

    private Socket modelReceiver;
    private ModelInterpreter modelInterpreter;
    private boolean connected = false;

    public ModelSocket(){}



    public void connect(String ip, int port){
        if(modelInterpreter == null){
            throw new IllegalThreadStateException("Cannot connect without an interpreter");
        }

        try {
            modelReceiver = new Socket(ip, port);
        } catch (IOException e) {/*TODO terminate*/}

        new Thread(this::socketRoutine).start();
    }


    public void attachInterpreter(ModelInterpreter interpreter){
        if(connected){
            throw new IllegalThreadStateException("Cannot attach the interpreter after the connection is established");
        }

        modelInterpreter = interpreter;
    }



    private void socketRoutine() {
        while (true) {
            int[] intIn = new int[38]; //TODO check in Dashboard how many ints are sent each time
            try {
                InputStream in = modelReceiver.getInputStream();
                DataInputStream dataIn = new DataInputStream(in);

                for (int i = 0; i < intIn.length; ++i) {
                    intIn[i] = dataIn.readInt();
                }
            } catch (IOException ioe){/*TODO terminate*/}

            //9 is the code to close the connection
            if(intIn[0] == 9) {
                break;
            }

            modelInterpreter.update(intIn);
        }
    }



}
