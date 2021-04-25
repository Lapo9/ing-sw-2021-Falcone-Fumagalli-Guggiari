package it.polimi.ingsw.controller;

import it.polimi.ingsw.Pair;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Player {

    Socket client;
    String name;
    boolean isConnected;



    public Player(Socket client) {
        this.client = client;

        new Thread(this::handshake);
    }



    private void handshake(){
        try {
            InputStream in = client.getInputStream();
            byte[] byteMessage = new byte[9700];
            int byteRead = 0;

            client.setSoTimeout(5000);
            try {
                byteRead = in.read(byteMessage);
            } catch (SocketTimeoutException ste) {/*TODO tell this player he is out and delete him*/}

            String message = new String(byteMessage, 2, byteRead-2, StandardCharsets.UTF_8);
            String[] tokens = message.split(" ");
            this.name = tokens[1];
            String match = tokens[2];
            
            //TODO tell this player he is connected and add him to the specified match

        } catch(Exception e){/*TODO tell the player something bad happened and delete this player*/}
    }

}
