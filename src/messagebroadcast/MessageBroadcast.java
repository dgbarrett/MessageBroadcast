package messagebroadcast;

import messagebroadcast.client.BroadcastClient;
import messagebroadcast.gui.MessageBroadcastGUI;
import messagebroadcast.server.BroadcastServer;
import messagebroadcast.server.ServerThread;

public class MessageBroadcast {
    
    BroadcastServer server;
    BroadcastClient client;
    
    MessageBroadcast () {
        this.server = new BroadcastServer();
        this.client = new BroadcastClient(this.server.getPort());  
    }
    
    public void start() {
        new Thread( new ServerThread(this.server) ).start();
        
        while(true) {
            this.client.broadcastMessage("Hello World");
            this.client.getBroadcasts();
        }
    }

    public static void main(String[] args) {
//        MessageBroadcast b = new MessageBroadcast();
//        b.start();
          MessageBroadcastGUI gui = new MessageBroadcastGUI();
          gui.setVisible(true);
    }
    
}
