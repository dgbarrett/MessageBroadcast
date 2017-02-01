package messagebroadcast.server;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import messagebroadcast.server.security.ServerCrypto;

/*
    Resources needed by the BroadcastServer
*/
public class ServerResources {
    
    private static final int QUEUE_SIZE = 50;
    
    // Queue of messages of fixed size (Thread safe collection type).
    private ArrayBlockingQueue<String> messageQueue; 
    // Corresponding queue of message id's (Thread safe collection type).
    private ArrayBlockingQueue<String> msgIds;
    // Required crypto functions.
    private ServerCrypto crypto;
    
    public ServerResources() {
        this.messageQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);        
        this.msgIds = new ArrayBlockingQueue<>(QUEUE_SIZE);

        // Generates new keypair.
        this.crypto = new ServerCrypto();
    }
    
    // Get messages
    public String[] getMessageQueue() {
        // Passed array is just to determine runtime return type of toArray
        return messageQueue.toArray(new String[1]);
    }
    
    // Get message ids
    public String[] getMessageIds() {
        return msgIds.toArray(new String[1]);
    }
    
    // Get the current public key on the server.
    public String getPublicKey() {
        return this.crypto.getPublicKey();
    }
    
    // Get the current public key size (in bits);
    public int getPublicKeySize() {
        return this.crypto.getPublicKeySize();
    }
    
    // Add message to the message queue
    public synchronized boolean addMessage(String message) {
        //If we have reached max size...
        if (this.messageQueue.size() >= QUEUE_SIZE) {
            try {
                // Remove oldest broadcast
                this.messageQueue.take();
                this.msgIds.take();
            } catch (InterruptedException e) {
                return false;
            }
        }
        
        // Add new broadcast.
        this.messageQueue.add(message);
        // Generate new UUID for broadcast.
        this.msgIds.add(UUID.randomUUID().toString());

        return true;
    }
    
    // Returns true if there is nothing in the message queue.
    public synchronized boolean messageQueueIsEmpty() {
        return this.messageQueue.isEmpty();
    }
    
    // Decrypt a string (uses current private key which is hidden behind this.crypto).
    public String decrypt(String msg) {
        return this.crypto.decrypt(msg);
    }
}
