package messagebroadcast.client.gui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
    Extended JTextArea used to create Message Broadcasts to be sent to the server.
*/
public class BroadcastCreationArea extends JScrollPane {
    
    private BroadcastAreaPanel parent;
    private JTextArea textarea;

    public BroadcastCreationArea(BroadcastAreaPanel parent) {
        super(); 
        
        this.parent = parent;
        
        this.textarea = new JTextArea();
        this.textarea.setEditable(true);
        this.textarea.setLineWrap(true);

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.setViewportView(textarea);
    }
    
    // Get the contents of the text area.
    public String getBroadcast() {
        String text = this.textarea.getText();
        this.textarea.setText(null);
        return text;
    }
    
    // Set the contents of the text area.
    public void setBroadcast(String broadcast) {
        this.textarea.setText(null);
        this.textarea.setText(broadcast);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, 50);
    }
}
