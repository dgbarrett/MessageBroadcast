package messagebroadcast.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class BroadcastPanel extends JPanel {
    
    private MessageBroadcastGUI parent;
    private BroadcastMenuBar menubar;
    private BroadcastArea broadcast;
    private BroadcastStatusBar status;

    public BroadcastPanel(MessageBroadcastGUI parent) {
        super();
        
        this.parent = parent;
        this.setLayout( new BorderLayout() );
        
        this.menubar = new BroadcastMenuBar(this);
        this.broadcast = new BroadcastArea(this);
        this.status = new BroadcastStatusBar(this);
        
        this.add( this.menubar, BorderLayout.NORTH );
        this.add( this.broadcast, BorderLayout.CENTER);
        this.add( this.status, BorderLayout.SOUTH);
    }
    
    protected void sendMessage(String message) {
        
    }
    
    public int getParentWidth() {
        return this.parent.getWidth();
    }
    
    public int getParentHeight() {
        return this.parent.getHeight();
    }
    
}
