package messagebroadcast.client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BroadcastArea extends JPanel implements ExitableFrom {
    
    private final BroadcastPanel parent;
    private final JLabel title;
    private final BroadcastDisplayPane broadcasts;
    private final BroadcastCreationArea create;
    private final JButton sendMessage;
    private JButton exit;

    public BroadcastArea(BroadcastPanel parent) {
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
    
    @Override
    public void exitGUI() {
        this.parent.exitGUI();
    }
    
    public void setBroadcast(String broadcast) {
        this.create.setBroadcast(broadcast);
    }
}
