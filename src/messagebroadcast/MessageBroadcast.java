package messagebroadcast;

import messagebroadcast.gui.GUIThread;
import messagebroadcast.server.BroadcastServer;
import messagebroadcast.server.ServerThread;

public class MessageBroadcast {
    
    BroadcastServer server;
    
    MessageBroadcast () {
        this.server = new BroadcastServer();
    }
    
    public void startServer() {
        new Thread( new ServerThread( this.server ) ).start();
    }

    public static void main(String[] args) {
        MessageBroadcast b = new MessageBroadcast();
        b.startServer();
        
        for(int i = 0 ; i < 3 ; i++) {
            new Thread( new GUIThread( b.server.getPort() ));
        }
    }
    
}
