package messagebroadcast.client.gui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
    JPanel extension which populates main MessageBroadcastGUI JFrame.
    Holds all MessageBroadcast GUI components.
*/

public class BroadcastPanel extends JPanel {
    
    private MessageBroadcastGUI parent;
    private BroadcastMenuBar menubar;
    private BroadcastAreaPanel broadcast;
    private BroadcastStatusBar status;

    public BroadcastPanel(MessageBroadcastGUI parent) {
        super();
        
        this.parent = parent;
        this.setLayout( new BorderLayout() );
        
        this.menubar = new BroadcastMenuBar(this);
        this.broadcast = new BroadcastAreaPanel(this);
        this.status = new BroadcastStatusBar(this);
        
        this.add( this.menubar, BorderLayout.NORTH );
        this.add( this.broadcast, BorderLayout.CENTER);
        this.add( this.status, BorderLayout.SOUTH);
    }
    
    // Send a broadcast message to the MessageBroadcast server.
    protected void sendMessage(String message) {
        this.parent.sendMessage(message);
    }
    
    // Closes the GUI housing this component.
    public void exitGUI() {
        this.parent.exitGUI();
    }
    
    // Update the display of broadcasts retreived from the server.
    void updateBroadcasts(List<Map.Entry<String,String>> broadcasts) {
        this.broadcast.updateBroadcasts(broadcasts);
    }

    // Get the JFrame housing this JPanel extension.
    public JFrame getFrame() {
        return this.parent;
    }
    
    // Set the broadcast creation text area to a String.
    public void setBroadcast(String broadcast) {
        this.broadcast.setBroadcast(broadcast);
    }
    
}
