/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author damon
 */
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
    
    public int getParentWidth() {
        return this.parent.getWidth();
    }
    
    public int getParentHeight() {
        return this.parent.getHeight();
    }
    
}
