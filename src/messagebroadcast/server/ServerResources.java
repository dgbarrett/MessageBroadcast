package messagebroadcast.server;

import java.util.concurrent.ArrayBlockingQueue;
import messagebroadcast.server.security.ServerCrypt;

class ServerResources {
    
    private static final int QUEUE_SIZE = 50;
    
    private ArrayBlockingQueue<String> messageQueue;
    private ServerCrypt crypto;
    
    public ServerResources() {
        this.messageQueue = new ArrayBlockingQueue<>(50);
        this.crypto = new ServerCrypt();
    }
    
    public String[] getMessageQueue() {
        return messageQueue.toArray(new String[1]);
    }
    
    public String getPublicKey() {
        return this.crypto.getPublicKey();
    }
    
    public synchronized boolean addMessage(String message) {
        if (this.messageQueue.size() >= QUEUE_SIZE) {
            try {
                this.messageQueue.take();
            } catch (InterruptedException e) {
                return false;
            }
        }
        
        this.messageQueue.add(message);
        return true;
    }
    
    public String decrypt(String msg) {
        return this.crypto.decrypt(msg);
    }
}
