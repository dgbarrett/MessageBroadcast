/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.server.security;

import messagebroadcast.security.CryptUtil;

/**
 *
 * @author damon
 */
public class ServerCrypt {
    
    private KeyLocker locker;

    public ServerCrypt() {
        this.locker = new KeyLocker();
    }
    
    public String decrypt(String data) {
        byte[] decrypted = null;
        try {
            decrypted = CryptUtil.decrypt(locker.getPrivateKey(), 
                                          CryptUtil.decodeBase16(data), 
                                          locker.ALGORITHM);
        } catch (Exception e) {
            System.out.println("Decryption failed\n");
        }
        
        return new String(decrypted);
    }
    
    public String getPublicKey() {
        return CryptUtil.encodeBase16(this.locker.getPublicKey());
    }
    
    public int getPublicKeySize(){
        return KeyLocker.getKeySize();
    }
    
    
}
