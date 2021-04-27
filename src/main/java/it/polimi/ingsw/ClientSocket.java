package it.polimi.ingsw;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Socket wrapper to facilitate send and receive operations.
 */
public class ClientSocket {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;


    /**
     * Creates a Socket.
     * @param ip IP address of the server.
     * @param port Port number of the server.
     * @throws IOException Couldn't create Socket.
     */
    public ClientSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);

        dataIn = new DataInputStream(socket.getInputStream());
        dataOut = new DataOutputStream(socket.getOutputStream());
    }


    /**
     * Sends the specified message, packing it in a byte array as specified by the packUp argument.
     * @param message Message to send.
     * @param packUp Function to transform the message to a byte array.
     * @param <InType> Type of message (i.e. String, int[], ...)
     * @throws IOException Couldn't send the message.
     */
    public synchronized <InType> void send(InType message, Function<InType, byte[]> packUp) throws IOException {
        byte[] byteMessage = packUp.apply(message);
        dataOut.write(byteMessage);
        dataOut.flush();
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Message.
     * @throws IOException Couldn't read the message.
     */
    public synchronized <ReturnType> ReturnType receive(Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        int length = dataIn.readByte();
        byte[] byteMessage = new byte[length];
        dataIn.readFully(byteMessage);

        return transform.apply(byteMessage);
    }




    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Type of message, message.
     * @throws IOException Couldn't read the message.
     */
    public synchronized <ReturnType> Pair<Byte, ReturnType> receiveWithType(Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        int length = dataIn.readByte();
        byte type = dataIn.readByte();
        byte[] byteMessage = new byte[length];
        dataIn.readFully(byteMessage);

        return new Pair<>(type, transform.apply(byteMessage));
    }



    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Message.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public synchronized <ReturnType> ReturnType receive(int milliseconds, Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        socket.setSoTimeout(milliseconds);
        ReturnType res = receive(transform);
        socket.setSoTimeout(0); //disable timeout
        return res;
    }



    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Type of message, message.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public synchronized <ReturnType> Pair<Byte, ReturnType> receiveWithType(int milliseconds, Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        socket.setSoTimeout(milliseconds);
        Pair<Byte, ReturnType> res = receiveWithType(transform);
        socket.setSoTimeout(0); //disable timeout
        return res;
    }


    /**
     * Transforms a byte array into a String (UTF-8 encoding assumed).
     * @param in Message as byte array.
     * @return Message as String.
     */
    public static String bytesToString(byte[] in){
        return new String(in, 0, in.length, StandardCharsets.UTF_8);
    }


    /**
     * Transforms a byte array into an int array.
     * @param in Message as byte array.
     * @return Message as int array.
     */
    public static int[] bytesToInts(byte[] in){
        IntBuffer intBuffer = ByteBuffer.wrap(in).asIntBuffer();
        int[] res = new int[intBuffer.remaining()];
        intBuffer.get(res);
        return res;
    }


    /**
     * Returns a function to pack up a String into a byte array with length and specified type.
     * @param type Type of message, must be 1 byte long.
     * @return Message as byte array containing length and type as first 2 elements.
     */
    public static Function<String, byte[]> packUpStringWithLengthAndType(byte type) {
        return in -> {
            int length = in.length(); //byte = char
            byte[] res = new byte[length + 2]; //length, type, payload
            res[0] = (byte) length;
            res[1] = type;
            //String to byte[]
            System.arraycopy(in.getBytes(StandardCharsets.UTF_8), 0, res, 2, length);
            return res;
        };
    }



    /**
     * Returns a function to pack up a String into a byte array with length.
     * @return Message as byte array containing length as first element.
     */
    public static Function<String, byte[]> packUpStringWithLength() {
        return in -> {
            int length = in.length(); //byte = char
            byte[] res = new byte[length + 1]; //length, payload
            res[0] = (byte) length;
            //String to byte[]
            System.arraycopy(in.getBytes(StandardCharsets.UTF_8), 0, res, 1, length);
            return res;
        };
    }



    /**
     * Returns a function to pack up an int array into a byte array with length and specified type.
     * @param type Type of message, must be 1 byte long.
     * @return Message as byte array containing length and type as first 2 elements.
     */
    public static Function<int[], byte[]> packUpIntsWithLengthAndType(byte type) {
        return in -> {
            int length = in.length * 4; //int = 4 bytes
            byte[] res = new byte[length + 2]; //length, type, payload
            res[0] = (byte) length;
            res[1] = type;
            //int[] to byte[]
            ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            byteBuffer.asIntBuffer().put(in);
            System.arraycopy(byteBuffer.array(), 0, res, 2, length);
            return res;
        };
    }



    /**
     * Returns a function to pack up an int array into a byte array with length.
     * @return Message as byte array containing length as first element.
     */
    public static Function<int[], byte[]> packUpIntsWithLength() {
        return in -> {
            int length = in.length * 4; //int = 4 bytes
            byte[] res = new byte[length + 1]; //length, payload
            res[0] = (byte) length;
            //int[] to byte[]
            ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            byteBuffer.asIntBuffer().put(in);
            System.arraycopy(byteBuffer.array(), 0, res, 1, length);
            return res;
        };
    }


}
