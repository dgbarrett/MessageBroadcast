package messagebroadcast.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

    private ExitableFrom parent;
    
    public ExitListener(ExitableFrom parent) {
        this.parent=parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.parent.exitGUI();
    }
}
