package messagebroadcast.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

    private BroadcastArea parent;
    
    public ExitListener(BroadcastArea parent) {
        this.parent=parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.parent.exitGUI();
    }
    
}
