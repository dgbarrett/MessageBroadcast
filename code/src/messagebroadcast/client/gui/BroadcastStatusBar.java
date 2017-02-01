package messagebroadcast.client.gui;

import javax.swing.JLabel;

/*
    Status bar for MessageBroadcast gui.
*/

public class BroadcastStatusBar extends JLabel {
    
    BroadcastPanel parent;

    public BroadcastStatusBar(BroadcastPanel parent) {
        super();
        
        this.parent = parent;
        this.setText("Updating...");
    }
    
}
