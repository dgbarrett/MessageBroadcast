package messagebroadcast.gui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(375, HEIGHT);
    }
}
