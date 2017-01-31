package messagebroadcast.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BroadcastArea extends JPanel {
    
    private BroadcastPanel parent;
    private JLabel title;
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
        this.title = new JLabel("Broadcasts:");
        
        this.sendMessage.addActionListener( new SendMessageListener(this) );
        this.exit.addActionListener( new ExitListener(this) );
        
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        
        this.title.setFont( new Font(null, Font.BOLD, 18));
        this.add( this.title , c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10,0,0,0);

        this.add( this.broadcasts , c);
        
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.WEST;
        c.gridheight = 1;
        
        this.add( this.create , c ); 
        
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        
        this.add( this.sendMessage, c );    
        
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        
        this.add( this.exit, c );
    }
    
    protected void sendMessage() {
        String message = this.create.getBroadcast();
        this.parent.sendMessage(message);
    }
    
    public int getParentWidth() {
        return this.parent.getWidth();
    }
    
    public int getParentHeight() {
        return this.parent.getHeight();
    }

    void updateBroadcasts(List<Map.Entry<String,String>> broadcasts) {
        this.broadcasts.updateBroadcasts(broadcasts);
    }
    
    public void exitGUI() {
        this.parent.exitGUI();
    }
}
