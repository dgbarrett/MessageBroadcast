package messagebroadcast.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import messagebroadcast.client.security.ClientCrypto;
import messagebroadcast.api.APIMessage;
import messagebroadcast.api.APIMessageTypes;
import messagebroadcast.api.APIRequest;
import messagebroadcast.api.APIResponse;

/*
    Client side of the MessageBroadcast system.
*/
public class BroadcastClient {
    
    // Connection to server.
    private PrintWriter out;
    // Connection to server
    private BufferedReader in;
    // All crypto needs
    private ClientCrypto crypto;
    // <UUID,Message> broadcasts retrieved from server during this session
    private List<Map.Entry<String,String>> broadcasts;
    // Index of the next message not shown on the GUI
    private int renderIndex;

    public BroadcastClient(int port) {
        try {
            Socket echoSocket = new Socket(InetAddress.getByName("localhost"), port);
            this.out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            this.in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
        
        this.crypto = new ClientCrypto();
        this.broadcasts = new ArrayList<>();
        this.renderIndex = 0;
    }
    
    // Broadcast a message to all other users.
    public int broadcastMessage(String dirtyMessage) {
            try {
                // Send request for servers Public Key.
                this.send("MSGTYPE=GET_PK;");
                APIResponse response = this.getResponse();

                // Parse out key and key size
                String publicKey = response.getParam("PK");
                int pkSize = Integer.parseInt(response.getParam("SIZE"));
                // Break down large messages into individually encrypted chunks.
                int maxMsgSize = (pkSize/8 - 11)/2;
                
                ArrayList<String> messageParts = new ArrayList<>();
                String message = this.crypto.clean(dirtyMessage);
                String submsg = null;
                int strLen = message.length();

                // Partions message into messages of size maxMsgSize or less.
                for (int i = 0; i < strLen ; i= i + maxMsgSize) {
                    if (i + maxMsgSize > strLen) {
                       submsg = message.substring(i, strLen);
                    } else {
                        submsg = message.substring(i, i + maxMsgSize);
                    }

                    messageParts.add(submsg);
                }
                
                if (!messageParts.isEmpty()) {
                    APIRequest req = new APIRequest(APIMessageTypes.SEND_MESSAGE, true);
                
                    for (String messagePart : messageParts) {
                        // Encrypt each part of the message.
                        String encryptedMessage = this.crypto.encrypt(messagePart, publicKey);
                        // Add as a parameter to the APIRequest
                        req.addParam("MESSAGE", encryptedMessage);
                    }

                    // Send message to server.
                    this.send(req);
                    response = this.getResponse();
                }
            } catch (Exception e ) {
                System.out.println(e.getMessage());
            }
            
        return 0;
    } 
    
    // Get all broadcasts from currently on the server.
    public List<Map.Entry<String, String>> getBroadcasts() {
        try {
            this.send("MSGTYPE=GET_QUEUE");
            APIResponse response = this.getResponse();
            
            // Messages on the server and their corresponding unique ID's
            String[] messages = response.getParams("MESSAGE");
            String[] msgIds = response.getParams("MSGID");
            
            if (messages.length == msgIds.length) {
                
                // Loop through all message returned from server.
                for (int i = 0 ; i < messages.length ; i++) {
                    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(msgIds[i], messages[i]);
                    
                    /*
                        If we dont have the message client side already, store 
                        it in the broadcasts list.
                    */
                    if (!this.broadcasts.contains(entry)) {
                        this.broadcasts.add(entry);
                    }
                }
                 
                /*
                    Slice new messages (messages not on GUI) off end of list of 
                    all messages retreived from server during this session.
                */
                List<Map.Entry<String,String>> newEntries = 
                        new ArrayList( this.broadcasts.subList(this.renderIndex, 
                                                               this.broadcasts.size()));
                
                

                this.renderIndex += newEntries.size();
                
                return newEntries;
            }
            
            return null;
        }catch (Exception e ) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Send APIMessage to server.
    private void send(APIMessage apiMessage) {
        this.send(apiMessage.toString());
    }
    
    // Send raw string to server.
    private void send(String apiString) {
        this.out.println(apiString);
    }
    
    // Get response from server after sending message.
    private APIResponse getResponse() {
        try {
            return new APIResponse(in.readLine());
        } catch (IOException e) {
            return null;
        }
    }
} 
