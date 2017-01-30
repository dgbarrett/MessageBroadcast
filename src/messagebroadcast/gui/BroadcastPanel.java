/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author damon
 */
public class BroadcastPanel extends JPanel {
    
    MessageBroadcastGUI parent;

    public BroadcastPanel(MessageBroadcastGUI parent) {
        super();
        
        this.parent = parent;
        this.setLayout( new BorderLayout() );
        
        this.add( new BroadcastMenuBar(this), BorderLayout.NORTH );
        this.add( new JButton("HELLO"), BorderLayout.CENTER);
    }
    
}
