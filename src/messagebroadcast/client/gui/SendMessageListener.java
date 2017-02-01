package messagebroadcast.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Listens for click on "Send Broadcast" button.
*/
public class SendMessageListener implements ActionListener {
    
    private BroadcastAreaPanel panel;

    public SendMessageListener(BroadcastAreaPanel panel) {
        this.panel = panel;
    }
    
    // Called when user clicks "Send Broadcast" button
    @Override
    public void actionPerformed(ActionEvent e) {
        this.panel.sendMessage();
    }
    
}
