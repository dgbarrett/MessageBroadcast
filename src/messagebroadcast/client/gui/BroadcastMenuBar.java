/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        
        this.fromFile.addActionListener( new LoadFileListener(this.parent) );
        
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
    
    private class LoadFileListener implements ActionListener {

        private final BroadcastPanel parent;
        
        public LoadFileListener(BroadcastPanel parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(parent);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                
                try {
                    String fileContents = new String(Files.readAllBytes(Paths.get(filename)));
                    this.parent.setBroadcast(fileContents);
                } catch (IOException err) {
                    System.out.println("Could not get file contents.");
                }
                
            }
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
