
package messagebroadcast.server;


public class ServerThread implements Runnable {
    
    BroadcastServer server;
    
    public ServerThread(BroadcastServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        this.server.run();
    }
}