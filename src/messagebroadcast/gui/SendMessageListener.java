package messagebroadcast.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageListener implements ActionListener {
    
    private BroadcastArea panel;

    public SendMessageListener(BroadcastArea panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.panel.sendMessage();
    }
    
}
