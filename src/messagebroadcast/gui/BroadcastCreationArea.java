package messagebroadcast.gui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BroadcastCreationArea extends JScrollPane {
    
    private BroadcastArea parent;
    private JTextArea textarea;

    public BroadcastCreationArea(BroadcastArea parent) {
        super(); 
        
        this.parent = parent;
        
        this.textarea = new JTextArea();
        this.textarea.setEditable(true);
        this.textarea.setLineWrap(true);

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.setViewportView(textarea);
    }
    
    public String getBroadcast() {
        String text = this.textarea.getText();
        this.textarea.setText(null);
        return text.replace("\n", " ").replace("  ", " ").replace(";", "-");
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, 50);
    }
    
    public void setBroadcast(String broadcast) {
        this.textarea.setText(null);
        this.textarea.setText(broadcast);
    }
}
