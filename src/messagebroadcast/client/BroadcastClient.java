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
import messagebroadcast.client.security.ClientCrypt;
import messagebroadcast.api.APIMessage;
import messagebroadcast.api.APIMessageTypes;
import messagebroadcast.api.APIRequest;
import messagebroadcast.api.APIResponse;
import messagebroadcast.api.APIRequestHandler;

public class BroadcastClient {
    
    private PrintWriter out;
    private BufferedReader in;
    private ClientCrypt crypto;
    
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
        
        this.crypto = new ClientCrypt();
    }
    
    public int broadcastMessage(String dirtyMessage) {
            try {
                System.out.println("SENDING PK_GET");
                this.send("MSGTYPE=GET_PK;");
                
                APIResponse response = this.getResponse();
                System.out.println("Response was: " + response.toString());

                String publicKey = response.getParam("PK");
                int pkSize = Integer.parseInt(response.getParam("SIZE"));
                int maxMsgSize = pkSize/8 - 11;
                maxMsgSize /= 2;
                
                ArrayList<String> messageParts = new ArrayList<>();
                String message = this.crypto.clean(dirtyMessage);
                String submsg = null;
                int strLen = message.length();
                
                System.out.println("Message len is " + strLen);

                for (int i = 0; i < strLen ; i= i + maxMsgSize) {
                    if (i + maxMsgSize > strLen) {
                       submsg = message.substring(i, strLen);
                    } else {
                        submsg = message.substring(i, i + maxMsgSize);
                    }
                    System.out.println("Submsg is " + submsg.length());
                    messageParts.add(submsg);
                }
                
                if (!messageParts.isEmpty()) {
                    APIRequest req = new APIRequest(APIMessageTypes.SEND_MESSAGE, true);
                
                    for (String messagePart : messageParts) {
                        System.out.println("Submsg is " + messagePart.length());
                        String encryptedMessage = this.crypto.encrypt(messagePart, publicKey);
                        req.addParam("MESSAGE", encryptedMessage);
                    }

                    System.out.println("SENDING ENCYRYPTED MESSAGE: " + req.toString() );
                    this.send(req);
                    response = this.getResponse();
                    System.out.println("Response was: " + response.toString());
                    System.out.println(" ");
                }
                
            } catch (Exception e ) {
                System.out.println(e.getMessage());
            }
        return 0;
    } 
    
    public List<Map.Entry<String, String>> getBroadcasts() {
        try {
            this.send("MSGTYPE=GET_QUEUE");
            APIResponse response = this.getResponse();
            
            String[] messages = response.getParams("MESSAGE");
            String[] msgIds = response.getParams("MSGID");
            
            if (messages.length == msgIds.length) {
                List<Map.Entry<String,String>> messageMap = new ArrayList<>();
                
                for (int i = 0 ; i < messages.length ; i++) {
                    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(msgIds[i], messages[i]);
                    messageMap.add(entry);
                }
                
                return messageMap;
            }
            
            return null;
        }catch (Exception e ) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private void send(APIMessage apiMessage) {
        this.send(apiMessage.toString());
    }
    
    private void send(String apiString) {
        this.out.println(apiString);
    }
    
    private APIResponse getResponse() {
        try {
            return new APIResponse(in.readLine());
        } catch (IOException e) {
            return null;
        }
    }
} 
