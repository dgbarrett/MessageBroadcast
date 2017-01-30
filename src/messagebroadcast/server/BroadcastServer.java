package messagebroadcast.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BroadcastServer {

    private ServerSocket serverSocket;
    private ServerResources res;
    private final int port;

    
    public BroadcastServer() {
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
                clientSocket = this.serverSocket.accept();
                Runnable requestHandler = new APIRequestHandler(clientSocket, this.res);
                new Thread(requestHandler).start();
            } catch (IOException e) { 
                System.out.println(e.getMessage());
            }
        }
        
    }
    

}
