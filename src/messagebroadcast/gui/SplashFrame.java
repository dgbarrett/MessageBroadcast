/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author damon
 */
public class SplashFrame extends JFrame implements ActionListener {
    
    private MessageBroadcastGUI parent;

    public SplashFrame(MessageBroadcastGUI parent) {
        super();
        
        this.parent = parent;
        
        this.setLayout( new BorderLayout() );
        
        JTextPane output = new JTextPane();
        output.setFont( new Font(Font.SANS_SERIF,Font.BOLD, 25) );
        output.setMargin( new Insets(50,0,0,0));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        output.setParagraphAttributes(attribs, true);
        
        output.setText("MessageBroadcast \n v0.1");
        
        JButton button = new JButton("Enter");
        button.addActionListener(this);
        
        this.add( output, BorderLayout.CENTER );        
        this.add( button, BorderLayout.SOUTH );
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.parent.showBroadcastScreen();
    }
    
}
