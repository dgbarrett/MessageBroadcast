package messagebroadcast.client.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class BroadcastDisplayPane extends JScrollPane {
    
    public static final int HEIGHT = 400;
    private static final int PADDING_LR = 25;
    
    private BroadcastArea parent;
    private JTextPane textpane;

    public BroadcastDisplayPane(BroadcastArea aThis) {
        super();
        
        this.parent = parent;
        this.textpane = new JTextPane();
        
        this.textpane.setEditable(false);
        
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.setViewportView( this.textpane );
    }
    

    
    public void renderBroadcasts() {
        StyledDocument doc = this.textpane.getStyledDocument();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, HEIGHT);
    }

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
}