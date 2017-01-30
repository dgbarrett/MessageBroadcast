package messagebroadcast.server;

import messagebroadcast.api.APIRequest;
import messagebroadcast.api.APIResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import messagebroadcast.server.security.ServerCrypt;


public class BroadcastServer {
    
    public static final String GET_PUBLIC_KEY = "GET_PK";
    public static final String GET_MESSAGE_QUEUE = "GET_QUEUE";
    public static final String SEND_MESSAGE = "SND_MSG";
    
    private ServerSocket serverSocket;
    private final int port;
    private MessageQueue queue;
    private ServerCrypt crypto;
    
    public BroadcastServer() {
        try {
            this.serverSocket = new ServerSocket(0, 0, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            System.out.println("Could not connect to server\n");
        }
        
        this.port = this.serverSocket.getLocalPort();  
        this.queue = new MessageQueue(50);
        this.crypto = new ServerCrypt();
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void run() {
        PrintWriter out;
        Socket clientSocket;
        BufferedReader in;
        String inputLine;

        try { 
            clientSocket = this.serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            while ((inputLine = in.readLine()) != null) {
                APIRequest req = new APIRequest(inputLine);
                APIResponse resp;
            
                
                switch (req.getRequestType()) {
                    case GET_PUBLIC_KEY:
                        resp = new APIResponse(APIResponse.SUCCESS);
                        resp.setParam("PK", this.crypto.getPublicKey() );
                        out.println( resp.toString() );
                        break;
                    case GET_MESSAGE_QUEUE:
                        resp = new APIResponse(APIResponse.SUCCESS);
                        for (String msg : this.queue.dump()) {
                            resp.setParam("MESSAGE", this.crypto.decrypt(msg));
                        }
                        out.println( resp.toString() );
                        break;
                    case SEND_MESSAGE:
                        String message = req.getParam("MESSAGE");
                        this.queue.append(message);
                        
                        resp = new APIResponse(APIResponse.SUCCESS);
                        out.println( resp.toString() );
                        break;
                    default:

                        resp = new APIResponse(APIResponse.FAIL);
                        resp.setParam("MESSAGE", "Invalid request.");
                        out.println( resp.toString() );
                        break;
                }
            }
        } catch (IOException e) { 
            System.out.println(e.getMessage());
        }
    }
    

}
