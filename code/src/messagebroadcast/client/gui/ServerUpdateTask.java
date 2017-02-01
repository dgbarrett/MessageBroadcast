package messagebroadcast.client.gui;

import java.util.TimerTask;

/*
    Background task used to check for/retrieve Message Broadcasts from central server.
*/
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
