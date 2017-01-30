/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.server;

import java.util.ArrayList;

public class MessageQueue {
    private final ArrayList<String> messages;
    private final int maxSize;
    
    public MessageQueue(int size) {
        this.maxSize = size;
        this.messages = new ArrayList<>();
    }
    
    public void append(String message) {
        if (messages.size() >= this.maxSize) {
            this.messages.remove(0);
        }
        this.messages.add(message);
    }
    
    public ArrayList<String> dump() {
        return new ArrayList<>(this.messages);
    }
}
