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
    
    public MessageBroadcastGUI() {
        super();
        
        this.setLayout( new BorderLayout() );
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add( new SplashPanel() , BorderLayout.CENTER );
        this.setVisible(true);
    }
    
}
