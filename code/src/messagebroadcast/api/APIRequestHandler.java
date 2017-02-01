package messagebroadcast.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import messagebroadcast.server.ServerResources;

/*
    Handler for request to the MessageBroadcast API that can be easily run in 
    its own thread.
*/
public class APIRequestHandler implements Runnable {
    private Socket socket;
    private ServerResources res;
    
    public APIRequestHandler(Socket socket, ServerResources resources) {
        // Socket is the connection to the client passed from the central server.
        this.socket = socket;
        // Server resources shared between all threads.
        this.res = resources;
    }

    @Override
    public void run() {
        BufferedReader in;
        PrintWriter out;
        String strRequest;
        
        try {
            // Open read and write connections to/from the client socket.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            // While the client connection remains open.
            while ( ( strRequest = in.readLine()) != null ) {
                APIRequest req = new APIRequest(strRequest);
                APIResponse resp = null;

                switch (req.getRequestType()) {
                    
                    case APIMessageTypes.GET_PUBLIC_KEY:
                        resp = new APIResponse(APIResponse.SUCCESS);
                        
                        // Return Public Key and the size of the public key (bits).
                        resp.addParam("PK", this.res.getPublicKey());
                        resp.addParam("SIZE", Integer.toString(this.res.getPublicKeySize()));
                        break;
                        
                    case APIMessageTypes.GET_MESSAGE_QUEUE:
                        resp = new APIResponse(APIResponse.SUCCESS);
                        
                        // Return messages in the message queue and their UUID's
                        if (!this.res.messageQueueIsEmpty()) {
                            for (String msg : this.res.getMessageQueue()) {
                                if (!"".equals(msg)) {
                                    resp.addParam("MESSAGE", msg);
                                }
                            }
                            for (String msg : this.res.getMessageIds()) {
                                if (!"".equals(msg)) {
                                    resp.addParam("MSGID", msg);
                                }
                            }
                        }
                        break;
                        
                    case APIMessageTypes.SEND_MESSAGE:
                        String[] messageParts = req.getParams("MESSAGE");
                        String message = "";

                        /*
                            Long messages are broken into peices that are 
                            encrytpted indivdually.
                        */
                        for (String messagePart : messageParts) {
                            message += this.res.decrypt(messagePart);
                        }

                        this.res.addMessage(message);

                        resp = new APIResponse(APIResponse.SUCCESS);
                        break;
                        
                    default:
                        resp = new APIResponse(APIResponse.FAIL);
                        
                        // Bad request.
                        resp.addParam("MESSAGE", "Invalid request.");
                        break;
                }
                
                // Send response.
                out.println(resp.toString());
            }
            
            // Close streams when client disconnects.
            in.close();
            out.close();
        } catch (IOException e) {
            // Error opening input/ouputs streams on client socket.
            System.out.print(e.getClass());
            System.out.println(" " + e.getMessage());
        }
    }
    
}
