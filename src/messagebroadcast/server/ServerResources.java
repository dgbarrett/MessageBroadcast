package messagebroadcast.server;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import messagebroadcast.server.security.ServerCrypt;

public class ServerResources {
    
    private static final int QUEUE_SIZE = 50;
    
    private ArrayBlockingQueue<String> messageQueue;    
    private ArrayBlockingQueue<String> msgIds;
    private ServerCrypt crypto;
    
    public ServerResources() {
        this.messageQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);        
        this.msgIds = new ArrayBlockingQueue<>(QUEUE_SIZE);

        this.crypto = new ServerCrypt();
    }
    
    public String[] getMessageQueue() {
        return messageQueue.toArray(new String[1]);
    }
    
    public String[] getMessageIds() {
        return msgIds.toArray(new String[1]);
    }
    
    public String getPublicKey() {
        return this.crypto.getPublicKey();
    }
    
    public int getPublicKeySize() {
        return this.crypto.getPublicKeySize();
    }
    
    public synchronized boolean addMessage(String message) {
        if (this.messageQueue.size() >= QUEUE_SIZE) {
            try {
                this.messageQueue.take();
                this.msgIds.take();
            } catch (InterruptedException e) {
                return false;
            }
        }
        
        this.messageQueue.add(message);
        this.msgIds.add(UUID.randomUUID().toString());

        return true;
    }
    
    public synchronized boolean messageQueueIsEmpty() {
        return this.messageQueue.isEmpty();
    }
    
    public String decrypt(String msg) {
        return this.crypto.decrypt(msg);
    }
}
