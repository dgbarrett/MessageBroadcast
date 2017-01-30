/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author damon
 */
public class BroadcastArea extends JPanel {
    
    private BroadcastPanel parent;
    private BroadcastDisplayPane broadcasts;
    private BroadcastCreationArea create;
    private JButton sendMessage;
    private JButton exit;

    public BroadcastArea(BroadcastPanel parent) {
        super();
        
        this.parent = parent;
        this.broadcasts = new BroadcastDisplayPane(this);
        this.create = new BroadcastCreationArea(this);
        this.sendMessage = new JButton("Broadcast Message");
        this.exit = new JButton("Exit");
        
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 3;
        
        this.add( this.broadcasts , c);
        
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.insets = new Insets(10,0,0,0);
        
        this.add( this.create , c ); 
        
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;

        this.add( this.exit, c );
        
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        
        this.add( this.sendMessage, c );    
    }
    
    public int getParentWidth() {
        return this.parent.getWidth();
    }
    
    public int getParentHeight() {
        return this.parent.getHeight();
    }
    
}
