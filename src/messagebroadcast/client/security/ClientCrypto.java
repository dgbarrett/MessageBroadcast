package messagebroadcast.client.security;

import messagebroadcast.security.CryptoUtil;

/*
    All crypto/security functions needed by a BroadcastClient
*/
public class ClientCrypto {
    
    private static final String ALGORITHM = "RSA";
    
    // Encrypt string using public key.
    public String encrypt(String data, String publicKey) {
       byte[] encrypted = null;
       try {
           encrypted = CryptoUtil.encrypt(CryptoUtil.decodeBase16(publicKey), 
                                         data.getBytes(), 
                                         ALGORITHM);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return CryptoUtil.encodeBase16(encrypted);
    }
    
    // Clean string before sending to server.
    public String clean(String msg) {
        return msg.toLowerCase().replace("\n", " ").replace("  ", " ").replace(";", "-");
    }
    
}
