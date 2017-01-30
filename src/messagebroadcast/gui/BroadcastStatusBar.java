/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import javax.swing.JLabel;

/**
 *
 * @author damon
 */
public class BroadcastStatusBar extends JLabel {
    
    BroadcastPanel parent;

    public BroadcastStatusBar(BroadcastPanel parent) {
        super();
        
        this.parent = parent;
        this.setText("Updating...");
    }
    
}
