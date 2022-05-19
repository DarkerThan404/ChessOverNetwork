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
    public PeerToPeer(String[] args){
        arguments = args;
    }

    public void InitializeConnectionToPeer() throws IOException {
        if(arguments.length == 1){
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(arguments[0]));
            connection = serverSocket.accept();
        } else if(arguments.length == 2){
            connection = new Socket(arguments[0], Integer.parseInt(arguments[1]));
        } else {
            throw new IOException("Wrong number of arguments!");
        }
        dataInputStream = new DataInputStream(connection.getInputStream());
        dataOutputStream = new DataOutputStream(connection.getOutputStream());
    }

    public String getMessage() throws IOException {
        return dataInputStream.readUTF();
    }

    public void sendMessage(String message) throws IOException {
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }

    public void closeConnection() throws IOException {
        connection.close();
        dataOutputStream.close();
        dataInputStream.close();
    }
}
