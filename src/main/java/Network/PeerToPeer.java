package Network;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerToPeer {
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String[] arguments;
    private Socket connection;
    public boolean isServer;
    public PeerToPeer(String[] args){
        arguments = args;
    }

    /**
     * Establishes connection between peers, based on number of arguments passed.
     * @throws IOException
     */
    public void InitializeConnectionToPeer() throws IOException {
        if(arguments.length == 1){
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(arguments[0]));
            System.out.println("Waiting for a client ...");
            connection = serverSocket.accept();
            System.out.println("Connection established");
            isServer = true;
        } else if(arguments.length == 2){
            connection = new Socket(arguments[0], Integer.parseInt(arguments[1]));
            System.out.println("Connection established");
            isServer = false;
        } else {
            throw new IOException("Wrong number of arguments!");
        }
        dataInputStream = new DataInputStream(connection.getInputStream());
        dataOutputStream = new DataOutputStream(connection.getOutputStream());
    }

    /**
     * Gets message from peer
     * @return Message
     * @throws IOException
     */
    public String getMessage() throws IOException {
        return dataInputStream.readUTF();
    }

    /**
     * Send message to peer
     * @param message message to send
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }

    /**
     * Closes connection after session, needs to be called explicitly.
     * @throws IOException
     */
    public void closeConnection() throws IOException {
        connection.close();
        dataOutputStream.close();
        dataInputStream.close();
    }
}
