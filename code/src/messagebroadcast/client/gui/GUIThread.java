package messagebroadcast.client.gui;

/*
    Runnable thread that spawns a new MessageBroadcastGUI
*/
public class GUIThread implements Runnable {
    
    final int port;
    final MessageBroadcastGUI gui;
    
    public GUIThread(int port) {
        this.port = port;
        this.gui = new MessageBroadcastGUI(this.port);
    }

    @Override
    public void run() {
        this.gui.setVisible(true);
    }
}