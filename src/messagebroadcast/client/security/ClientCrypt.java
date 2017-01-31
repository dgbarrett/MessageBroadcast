package messagebroadcast.client.security;

import messagebroadcast.security.CryptUtil;

public class ClientCrypt {
    
    private static final String ALGORITHM = "RSA";
    
    public String encrypt(String data, String publicKey) {
       byte[] encrypted = null;
       try {
           encrypted = CryptUtil.encrypt(CryptUtil.decodeBase16(publicKey), 
                                         data.getBytes(), 
                                         ALGORITHM);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return CryptUtil.encodeBase16(encrypted);
    }
    
    public String clean(String msg) {
        return msg.toLowerCase();
    }
    
}
