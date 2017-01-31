/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author damon
 */
public class BroadcastMenuBar extends JMenuBar implements ExitableFrom {
    
    private final BroadcastPanel parent;
    private final JMenu about;
    private final JMenu messaging;
    private final JMenuItem versInfo;    
    private final JMenuItem exit;
    private final JMenuItem sendMessage;    
    private final JMenuItem sendPicture;
    private final JMenuItem fromFile;

    public BroadcastMenuBar(BroadcastPanel parent) {
        super();
     
        this.parent = parent;
        this.about = new JMenu("About");
        this.messaging = new JMenu("Messaging");
        
        this.versInfo = new JMenuItem("Version Info");        
        this.exit = new JMenuItem("Exit...");
        
        this.sendMessage = new JMenuItem("Send Message");
        this.fromFile = new JMenuItem("\t\t\t...from file");
        this.sendPicture = new JMenuItem("Send Picture");
        
        this.about.add(this.versInfo);
        this.about.add(this.exit);
        
        this.messaging.add(this.sendMessage);
        this.messaging.add(this.fromFile);
        this.messaging.add(this.sendPicture);
        
        this.exit.addActionListener( new ExitListener(this) );
        this.versInfo.addActionListener( new VersionInfoListener(this.getFrame()) );
        
        this.add(this.about);
        this.add(this.messaging);
    }
    
    private class VersionInfoListener implements ActionListener {
        private final JFrame gui;
        
        public VersionInfoListener( JFrame gui ){
            this.gui = gui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this.gui, 
                                          "          Current version is v0.1", 
                                          "Version Info", 
                                          JOptionPane.PLAIN_MESSAGE);
        }        
    }
    
    private JFrame getFrame() {
        return this.parent.getFrame();
    }
    
    @Override
    public void exitGUI() {
        this.parent.exitGUI();
    }
    
}
