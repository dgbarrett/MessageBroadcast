package messagebroadcast.client.gui;

import java.util.TimerTask;

public class ServerUpdateTask extends TimerTask {
    
    private MessageBroadcastGUI parent;

    public ServerUpdateTask(MessageBroadcastGUI parent) {
        super();
        
        this.parent = parent;
    }
    
    @Override
    public void run() {
        parent.updateBroadcastsFromServer();
    }
    
}
