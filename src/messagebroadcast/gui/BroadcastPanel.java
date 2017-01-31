package messagebroadcast.gui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
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
        this.parent.sendMessage(message);
    }
    
    public int getParentWidth() {
        return this.parent.getWidth();
    }
    
    public int getParentHeight() {
        return this.parent.getHeight();
    }
    
    public void exitGUI() {
        this.parent.exitGUI();
    }
    
    public JButton getSubmit() {
        return this.broadcast.getSubmit();
    }

    void updateBroadcasts(List<Map.Entry<String,String>> broadcasts) {
        this.broadcast.updateBroadcasts(broadcasts);
    }
    
}
