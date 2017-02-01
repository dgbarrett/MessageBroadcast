package messagebroadcast.server;

import messagebroadcast.api.APIRequestHandler;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
    Server for the MessageBroadcast API.
*/
public class BroadcastServer {

    private ServerSocket serverSocket;
    // Shared resources on the server (Thread safe).
    private ServerResources res;
    private final int port;

    
    public BroadcastServer() {        
        // Open server socket.
        try {
            this.serverSocket = new ServerSocket(0, 0, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            System.out.println("Could not connect to server\n");
        }
        
        this.port = this.serverSocket.getLocalPort();  
        this.res = new ServerResources();
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void run() {
        Socket clientSocket;

        while(true) {
            try { 
                // Get client connection.
                clientSocket = this.serverSocket.accept();
                // Create new thread to handle request.
                Runnable requestHandler = new APIRequestHandler(clientSocket, this.res);
                // Start new thread so we are free to handle more clients.
                new Thread(requestHandler).start();
            } catch (IOException e) { 
                System.out.println(e.getMessage());
            }
        }
        
    }
}
