package messagebroadcast.client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
    Holds main GUI components for the MessageBroadcast gui.
*/
public class BroadcastAreaPanel extends JPanel implements ExitableFrom {
    
    private final BroadcastPanel parent;
    private final JLabel title;
    // Displays messages from the MessageBroadcast server
    private final BroadcastDisplayPane broadcasts;
    // Allows users to type a new broadcast to send.
    private final BroadcastCreationArea create;
    private final JButton sendMessage;
    private JButton exit;

    public BroadcastAreaPanel(BroadcastPanel parent) {
        super();
        
        this.parent = parent;
        this.broadcasts = new BroadcastDisplayPane(this);
        this.create = new BroadcastCreationArea(this);
        this.sendMessage = new JButton("Broadcast Message");
        this.exit = new JButton("Exit");
        this.title = new JLabel("Broadcasts:");
        
        this.title.setFont( new Font(null, Font.BOLD, 18));
        this.title.setBorder( new EmptyBorder(25,0,5,0));
        
        this.sendMessage.addActionListener( new SendMessageListener(this) );
        this.exit.addActionListener( new ExitListener(this) );
        
        this.setLayout( new BorderLayout() );
        
        this.add(this.title, BorderLayout.NORTH);        
        this.add(this.broadcasts, BorderLayout.CENTER);
        
        JPanel p = new JPanel();        
        JPanel p2 = new JPanel();

        p.setLayout( new GridLayout(3, 1));
        
        p.add(new JLabel("Create broadcast:"));
        p.add(this.create);
        
        p2.setLayout( new FlowLayout());
        
        p2.add(this.sendMessage);
        p2.add(this.exit);
        
        p.add(p2);

        this.add(p, BorderLayout.SOUTH);
    }
    
    // Private Listener class for "Broadcast Message" button.
    private class SendMessageListener implements ActionListener {
    
        private BroadcastAreaPanel panel;

        public SendMessageListener(BroadcastAreaPanel panel) {
            this.panel = panel;
        }

        // Called when user clicks "Broadcast Message" button
        @Override
        public void actionPerformed(ActionEvent e) {
            this.panel.sendMessage();
        }
    }
    
    // Return the contents of the broadcast creation area.
    protected String getBroadcast() {
        return this.create.getBroadcast();
    }
    
    // Send the text currently in this.create to the server.
    protected void sendMessage() {
        String message = this.create.getBroadcast();
        this.parent.sendMessage(message);
    }

    // Add the most recent message broadcasts retrieved from the server to the display area.
    void updateBroadcasts(List<Map.Entry<String,String>> broadcasts) {
        this.broadcasts.updateBroadcasts(broadcasts);
    }
    
    // Set the text in this.create
    public void setBroadcast(String broadcast) {
        this.create.setBroadcast(broadcast);
    }
    
    // Close the JFrame containing this component.
    @Override
    public void exitGUI() {
        this.parent.exitGUI();
    }
}
