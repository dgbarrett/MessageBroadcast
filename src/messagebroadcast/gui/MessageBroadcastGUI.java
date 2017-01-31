package messagebroadcast.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import messagebroadcast.client.BroadcastClient;

public class MessageBroadcastGUI extends JFrame {
    
    public static final int WIDTH = 400;    
    public static final int HEIGHT = 600;
    
    private BroadcastPanel broadcast;
    private BroadcastClient broadcastClient;
    
    public MessageBroadcastGUI(int serverPort) {
        super("MessageBroadcast");
        
        this.broadcast = new BroadcastPanel(this);
        this.broadcastClient = new BroadcastClient(serverPort);

        this.setLayout( new BorderLayout() );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        
        this.add( this.broadcast, BorderLayout.CENTER );
        
        this.setVisible(true);
    } 
    
    public void sendMessage(String message) {
        this.broadcastClient.broadcastMessage(message);
    }
}
