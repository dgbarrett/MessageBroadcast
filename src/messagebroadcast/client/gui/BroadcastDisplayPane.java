package messagebroadcast.client.gui;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/*
    Displays the message broadcasts retreived from the server on the gui.
*/
public class BroadcastDisplayPane extends JScrollPane {
    
    public static final int PANE_HEIGHT = 400;
    
    private BroadcastAreaPanel parent;
    private JTextPane textpane;

    public BroadcastDisplayPane(BroadcastAreaPanel parent) {
        super();
        
        this.parent = parent;
        this.textpane = new JTextPane();
        
        this.textpane.setEditable(false);
        
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.setViewportView( this.textpane );
    }
  
    // Add new broadcasts to the JTextPane (message broadcast display area).
    void updateBroadcasts(List<Map.Entry<String,String>> broadcasts) {
        StyledDocument doc = this.textpane.getStyledDocument();
        
        if (broadcasts != null) {
            for (Map.Entry<String, String> entry : broadcasts) {
                try {
                    doc.insertString(doc.getLength(), "[BROADCAST]: " + entry.getValue() + "\n", null);
                } catch (BadLocationException e) {
                    return;
                }
            } 
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, PANE_HEIGHT);
    }

}
