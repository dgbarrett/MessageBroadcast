package messagebroadcast.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import messagebroadcast.api.APIRequest;
import messagebroadcast.api.APIResponse;

public class APIRequestHandler implements Runnable {
    public static final String GET_PUBLIC_KEY = "GET_PK";
    public static final String GET_MESSAGE_QUEUE = "GET_QUEUE";
    public static final String SEND_MESSAGE = "SND_MSG";
    
    private Socket socket;
    private ServerResources res;
    
    public APIRequestHandler(Socket socket, ServerResources resources) {
        this.socket = socket;
        this.res = resources;
    }

    @Override
    public void run() {
        BufferedReader in;
        PrintWriter out;
        String strRequest = null;
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            while ( ( strRequest = in.readLine()) != null ) {
                if (strRequest != null) {
                    APIRequest req = new APIRequest(strRequest);
                    APIResponse resp;

                    switch (req.getRequestType()) {
                        case GET_PUBLIC_KEY:
                            resp = new APIResponse(APIResponse.SUCCESS);
                            resp.setParam("PK", this.res.getPublicKey());
                            out.println(resp.toString());
                            break;
                        case GET_MESSAGE_QUEUE:
                            resp = new APIResponse(APIResponse.SUCCESS);
                            if (!this.res.messageQueueIsEmpty()) {
                               for (String msg : this.res.getMessageQueue() ) {
                                    if (!"".equals(msg)) {
                                        resp.setParam("MESSAGE", this.res.decrypt(msg));
                                    }
                                }
                                for (String msg : this.res.getMessageIds() ) {
                                    if (!"".equals(msg)) {
                                        resp.setParam("MSGID", msg);
                                    }
                                } 
                            }
                            out.println(resp.toString());
                            break;
                        case SEND_MESSAGE:
                            String message = req.getParam("MESSAGE");
                            this.res.addMessage(message);

                            resp = new APIResponse(APIResponse.SUCCESS);
                            out.println(resp.toString());
                            break;
                        default:
                            resp = new APIResponse(APIResponse.FAIL);
                            resp.setParam("MESSAGE", "Invalid request.");
                            out.println(resp.toString());
                            break;
                    } 
                }
            }
            in.close();
            out.close();
        } catch (IOException e) {
            return;
        }
    }
    
}
