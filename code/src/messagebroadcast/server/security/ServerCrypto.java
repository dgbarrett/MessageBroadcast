package messagebroadcast.server.security;

import messagebroadcast.security.CryptoUtil;

/*
    All crypto/security functions needed by the MessageBroadcast central server.
*/
public class ServerCrypto {
    
    private KeyLocker locker;

    public ServerCrypto() {
        this.locker = new KeyLocker();
    }
    
    public String decrypt(String data) {
        byte[] decrypted = null;
        try {
            decrypted = CryptoUtil.decrypt(locker.getPrivateKey(), 
                                          CryptoUtil.decodeBase16(data), 
                                          locker.ALGORITHM);
        } catch (Exception e) {
            System.out.println("Decryption failed\n");
        }
        
        return new String(decrypted);
    }
    
    // Get the servers current public key.
    public String getPublicKey() {
        return CryptoUtil.encodeBase16(this.locker.getPublicKey());
    }
    
    // Get the servers current public key size (in bits).
    public int getPublicKeySize(){
        return KeyLocker.getKeySize();
    }
    
    
}
