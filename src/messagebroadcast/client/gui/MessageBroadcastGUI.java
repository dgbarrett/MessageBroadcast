package messagebroadcast.client.gui;

import java.awt.BorderLayout;
import java.util.Random;
import java.util.Timer;
import javax.swing.JFrame;

import messagebroadcast.client.BroadcastClient;

public class MessageBroadcastGUI extends JFrame implements ExitableFrom {
    
    public static final int WIDTH = 400;    
    public static final int HEIGHT = 600;
    
    private BroadcastPanel broadcast;
    private BroadcastClient broadcastClient;
    private final ServerUpdateTask updateTask;
    private Timer timer;
    
    public MessageBroadcastGUI(int serverPort) {
        super("MessageBroadcast");
        
        this.broadcast = new BroadcastPanel(this);
        this.broadcastClient = new BroadcastClient(serverPort);
        this.updateTask = new ServerUpdateTask( this ) ;
        this.timer = new Timer();

        this.setLayout( new BorderLayout() );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        
        this.add( this.broadcast, BorderLayout.CENTER );
        
        scheduleServerUpdates();
        
        this.setVisible(true);
    } 
    
    public void sendMessage(String message) {
        if (!message.isEmpty()) {
            this.broadcastClient.broadcastMessage(message);
            this.broadcast.updateBroadcasts( this.broadcastClient.getBroadcasts() );
        }
    }
   
    public void updateBroadcastsFromServer() {
        this.broadcast.updateBroadcasts( this.broadcastClient.getBroadcasts() );
    }
    
    private void scheduleServerUpdates() {
        this.timer.scheduleAtFixedRate( this.updateTask , new Random().nextInt(200), 1000);
    }
    
    @Override
    public void exitGUI() {
        this.dispose();
    }
}
