package messagebroadcast.client.gui;

import java.awt.BorderLayout;
import java.util.Random;
import java.util.Timer;
import javax.swing.JFrame;

import messagebroadcast.client.BroadcastClient;

/*
    GUI client for the MessageBroadcast API.
*/
public class MessageBroadcastGUI extends JFrame implements ExitableFrom {
    
    public static final int WIDTH = 400;    
    public static final int HEIGHT = 600;
    
    // GUI components
    private BroadcastPanel broadcast;
    // Client side of MessageBroadcast API
    private BroadcastClient broadcastClient;
    // For background updates from server.
    private Timer timer;
    // Background task.
    private final ServerUpdateTask updateTask;
    
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
    
    // Send a message broadcast to the central server.
    public void sendMessage(String message) {
        if (!message.isEmpty()) {
            this.broadcastClient.broadcastMessage(message);
            this.broadcast.updateBroadcasts( this.broadcastClient.getBroadcasts() );
        }
    }
   
    // Get message broadcasts from the server.
    public void updateBroadcastsFromServer() {
        this.broadcast.updateBroadcasts( this.broadcastClient.getBroadcasts() );
    }
    
    // Schedules checks for new message broadcasts in the background.
    private void scheduleServerUpdates() {
        this.timer.scheduleAtFixedRate( this.updateTask , new Random().nextInt(200), 1000);
    }
    
    // End the GUI session.
    @Override
    public void exitGUI() {
        this.dispose();
    }
}
