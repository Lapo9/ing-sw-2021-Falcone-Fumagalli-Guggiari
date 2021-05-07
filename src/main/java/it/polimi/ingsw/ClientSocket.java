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
        connect(ip, port);
    }

    /**
     * Creates an empty Socket.
     */
    public ClientSocket() {}

    /**
     * Creates a new Socket identical to the Socket passed as argument.
     * @param socket Socket to copy.
     * @throws IOException Couldn't create Socket.
     */
    public ClientSocket(Socket socket) throws IOException {
        connect(socket);
    }



    /**
     * Connects the Socket to the specified server.
     * @param ip IP address of the server.
     * @param port Port number of the server.
     * @throws IOException Couldn't connect to the specified server.
     */
    public void connect(String ip, int port) throws IOException{
        socket = new Socket(ip, port);

        dataIn = new DataInputStream(socket.getInputStream());
        dataOut = new DataOutputStream(socket.getOutputStream());
    }


    /**
     * Connects the Socket to the same server as the Socket passed as argument is connected to.
     * @param socket Socket to copy.
     * @throws IOException Couldn't connect.
     */
    public void connect(Socket socket) throws IOException {
        this.socket = socket;

        dataIn = new DataInputStream(this.socket.getInputStream());
        dataOut = new DataOutputStream(this.socket.getOutputStream());
    }




    /**
     * Closes the socket and the associated input and output streams.
     * @throws IOException Couldn't close the Socket properly.
     */
    public void close() throws IOException {
        dataIn.close();
        dataOut.close();
        socket.close();
    }





    /**
     * Sends the specified message, packing it in a byte array as specified by the packUp argument.
     * @param message Message to send.
     * @param packUp Function to transform the message to a byte array.
     * @param <InType> Type of message (i.e. String, int[], ...)
     * @throws IOException Couldn't send the message.
     */
    public <InType> void send(InType message, Function<InType, byte[]> packUp) throws IOException {
        synchronized (dataOut) {
            byte[] byteMessage = packUp.apply(message);
            dataOut.write(byteMessage);
            dataOut.flush();
        }
    }





    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message.
     * @return Message as byte array.
     * @throws IOException Couldn't read the message.
     */
    public byte[] receive() throws IOException {
        synchronized (dataIn) {
            int length = dataIn.readInt();
            byte[] byteMessage = new byte[length];
            dataIn.readFully(byteMessage);
            return byteMessage;
        }
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @return Message as byte array.
     * @throws IOException Couldn't read the message.
     */
    public Pair<Byte, byte[]> receiveWithType() throws IOException {
        synchronized (dataIn) {
            int length = dataIn.readInt();
            byte type = dataIn.readByte();
            byte[] byteMessage = new byte[length];
            dataIn.readFully(byteMessage);

            return new Pair<>(type, byteMessage);
        }
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message.
     * @return Message as byte array.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public byte[] receive(int milliseconds) throws IOException, SocketTimeoutException {
        synchronized (dataIn) {
            socket.setSoTimeout(milliseconds);
            byte[] res = receive();
            socket.setSoTimeout(0); //disable timeout
            return res;
        }
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @return Message as byte array.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public Pair<Byte, byte[]> receiveWithType(int milliseconds) throws IOException, SocketTimeoutException {
        synchronized (dataIn) {
            socket.setSoTimeout(milliseconds);
            Pair<Byte, byte[]> res = receiveWithType();
            socket.setSoTimeout(0); //disable timeout
            return res;
        }
    }



    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Message.
     * @throws IOException Couldn't read the message.
     */
    public <ReturnType> ReturnType receiveAndTransform(Function<byte[], ReturnType> transform) throws IOException {
        return transform.apply(receive());
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Type of message, message.
     * @throws IOException Couldn't read the message.
     */
    public <ReturnType> Pair<Byte, ReturnType> receiveAndTransformWithType(Function<byte[], ReturnType> transform) throws IOException {
        Pair<Byte, byte[]> tmp = receiveWithType();
        return new Pair<>(tmp.first, transform.apply(tmp.second));
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Message.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public <ReturnType> ReturnType receiveAndTransform(int milliseconds, Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        return transform.apply(receive(milliseconds));
    }


    /**
     * Listens to the Socket until a message is received. Blocking operation. Assumes length is always the first byte of the message and type the second one.
     * @param transform Function to transform the byte array received to something the client can understand.
     * @param <ReturnType> Type of the message returned (after transformation).
     * @return Type of message, message.
     * @throws IOException Couldn't read the message.
     * @throws SocketTimeoutException No messages received in the specified time span.
     */
    public <ReturnType> Pair<Byte, ReturnType> receiveAndTransformWithType(int milliseconds, Function<byte[], ReturnType> transform) throws IOException, SocketTimeoutException {
        Pair<Byte, byte[]> tmp = receiveWithType(milliseconds);
        return new Pair<>(tmp.first, transform.apply(tmp.second));
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
            int length = in.length(); //byte = char;
            byte[] res = new byte[length + 5]; //length (4 bytes), type, payload

            //put length into the array to send
            ByteBuffer intToBytes = ByteBuffer.allocate(4).putInt(length);
            System.arraycopy(intToBytes.array(), 0,res,0,4);

            res[4] = type;
            //String to byte[]
            System.arraycopy(in.getBytes(StandardCharsets.UTF_8), 0, res, 5, length);
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
            byte[] res = new byte[length + 4]; //length (4 bytes), payload

            //put length into the array to send
            ByteBuffer intToBytes = ByteBuffer.allocate(4).putInt(length);
            System.arraycopy(intToBytes.array(), 0,res,0,4);

            //String to byte[]
            System.arraycopy(in.getBytes(StandardCharsets.UTF_8), 0, res, 4, length);
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
            byte[] res = new byte[length + 5]; //length (4 bytes), type, payload

            //put length into the array to send
            ByteBuffer intToBytes = ByteBuffer.allocate(4).putInt(length);
            System.arraycopy(intToBytes.array(), 0,res,0,4);

            res[4] = type;
            //int[] to byte[]
            ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            byteBuffer.asIntBuffer().put(in);
            System.arraycopy(byteBuffer.array(), 0, res, 5, length);
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
            byte[] res = new byte[length + 4]; //length, payload

            //put length into the array to send
            ByteBuffer intToBytes = ByteBuffer.allocate(4).putInt(length);
            System.arraycopy(intToBytes.array(), 0,res,0,4);

            //int[] to byte[]
            ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            byteBuffer.asIntBuffer().put(in);
            System.arraycopy(byteBuffer.array(), 0, res, 4, length);
            return res;
        };
    }


}
