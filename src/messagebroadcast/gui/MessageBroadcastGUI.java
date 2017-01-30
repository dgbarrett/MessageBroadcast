/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author damon
 */
public class MessageBroadcastGUI extends JFrame {
    
    public static final int WIDTH = 400;    
    public static final int HEIGHT = 600;
    
    private BroadcastPanel broadcast;
    
    public MessageBroadcastGUI() {
        super("MessageBroadcast");
        
        this.broadcast = new BroadcastPanel(this);

        this.setLayout( new BorderLayout() );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        
        this.add( this.broadcast, BorderLayout.CENTER );
        
        this.setVisible(true);
    } 
}
