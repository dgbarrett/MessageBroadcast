/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author damon
 */
public class BroadcastCreationArea extends JScrollPane {
    
    private BroadcastArea parent;
    private JTextArea textarea;

    public BroadcastCreationArea(BroadcastArea parent) {
        super(); 
        
        this.parent = parent;
        
        this.textarea = new JTextArea();
        this.textarea.setEditable(true);
        this.textarea.setLineWrap(true);

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.setViewportView(textarea);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, 50);
    }
}
