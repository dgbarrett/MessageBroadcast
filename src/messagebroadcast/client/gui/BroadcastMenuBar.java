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

/*
    Menu bar for the MessageBroadcast gui.
*/
public class BroadcastMenuBar extends JMenuBar implements ExitableFrom {
    
    private final BroadcastPanel parent;
    private final JMenu about;
    private final JMenu messaging;
    private final JMenuItem versInfo;    
    private final JMenuItem exit;
    private final JMenuItem sendMessage;    
    //private final JMenuItem sendPicture;
    private final JMenuItem fromFile;

    public BroadcastMenuBar(BroadcastPanel parent) {
        super();
     
        this.parent = parent;
        this.about = new JMenu("About");
        this.messaging = new JMenu("Messaging");
        
        this.versInfo = new JMenuItem("Version Info");        
        this.exit = new JMenuItem("Exit...");
        
        this.sendMessage = new JMenuItem("Send broadcast");
        this.fromFile = new JMenuItem("Load broadcast from file");
        //this.sendPicture = new JMenuItem("Send Picture");
        
        this.about.add(this.versInfo);
        this.about.add(this.exit);
        
        this.messaging.add(this.sendMessage);
        this.messaging.add(this.fromFile);
        //this.messaging.add(this.sendPicture);
        
        // Allows user to exit GUI.
        this.exit.addActionListener( new ExitListener(this) );
        // Allows user to see version info.
        this.versInfo.addActionListener( new VersionInfoListener(this.getFrame()) );
        // Allows user to load broadcast from file.
        this.fromFile.addActionListener( new LoadFileListener(this.parent) );
        // Allows user to send message from menu bar.
        this.sendMessage.addActionListener( new SendMessageListener(this.parent) );
        
        this.add(this.about);
        this.add(this.messaging);
    }
    
    private class SendMessageListener implements ActionListener {
        private final BroadcastPanel parent;
        
        // Send message from menu bar.
        public SendMessageListener( BroadcastPanel parent ){
            this.parent = parent;
        }
        
        // Called when a used is trying to see the version info of the software.
        @Override
        public void actionPerformed(ActionEvent e) {
            this.parent.sendCurrentMessage();
        }        
    }
    
    private class VersionInfoListener implements ActionListener {
        private final JFrame gui;
        
        public VersionInfoListener( JFrame gui ){
            this.gui = gui;
        }
        
        // Called when a used is trying to see the version info of the software.
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

        // Called when a user is trying to load a message broadcast from file.
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create File Chooser
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files", "txt");
            chooser.setFileFilter(filter);
            
            //Display File Chooser
            int returnVal = chooser.showOpenDialog(parent);
            
            // If user selected a file.
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                
                try {
                    /* Put the file contents in the message broadcast creations 
                    text area. */
                    String fileContents = new String(Files.readAllBytes(Paths.get(filename)));
                    this.parent.setBroadcast(fileContents);
                } catch (IOException err) {
                    System.out.println("Could not get file contents.");
                }
            }
        }        
    }
    
    // Get the JFrame housing this component.
    private JFrame getFrame() {
        return this.parent.getFrame();
    }
    
    // Close the JFrame housing this component.
    @Override
    public void exitGUI() {
        this.parent.exitGUI();
    }
    
}
