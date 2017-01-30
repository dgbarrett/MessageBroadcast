/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.client.security;

import messagebroadcast.security.CryptUtil;

/**
 *
 * @author damon
 */
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
    
}
