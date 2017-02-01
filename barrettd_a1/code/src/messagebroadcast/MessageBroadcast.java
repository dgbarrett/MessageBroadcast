package messagebroadcast;

import messagebroadcast.client.gui.GUIThread;
import messagebroadcast.server.BroadcastServer;
import messagebroadcast.server.ServerThread;

public class MessageBroadcast {
    
    // Number of GUI clients to launch.
    public static final int NUM_GUIS = 3;
    
    // Central server for the reciving and sending broadcasts to all users.
    BroadcastServer server;
    
    MessageBroadcast () {
        this.server = new BroadcastServer();
    }
    
    public void startServer() {
        // Central server runs on its own thread.
        new Thread( new ServerThread( this.server ) ).start();
    }

    public static void main(String[] args) {
        MessageBroadcast b = new MessageBroadcast();
        
        // Start central server
        b.startServer();
        
        // Launch NUM_GUIS graphical clients.
        for(int i = 0 ; i < 3 ; i++) {
            // Each GUI is on its own thread.
            new Thread( new GUIThread( b.server.getPort() ));
        }
    }
}
