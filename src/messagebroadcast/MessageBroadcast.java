package messagebroadcast;

import messagebroadcast.gui.MessageBroadcastGUI;
import messagebroadcast.server.BroadcastServer;
import messagebroadcast.server.ServerThread;

public class MessageBroadcast {
    
    BroadcastServer server;
    MessageBroadcastGUI[] guis = new MessageBroadcastGUI[3];
    
    MessageBroadcast () {
        this.server = new BroadcastServer();
        
        for ( MessageBroadcastGUI gui : this.guis ) {
            gui = new MessageBroadcastGUI(this.server.getPort());
        }
    }
    
    public void startServer() {
        new Thread( new ServerThread( this.server ) ).start();
    }

    public static void main(String[] args) {
        MessageBroadcast b = new MessageBroadcast();
        
        for( MessageBroadcastGUI gui : b.guis) {
            gui.setVisible(true);
        }
    }
    
}
