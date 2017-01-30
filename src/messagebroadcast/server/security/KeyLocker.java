/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.server.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author damon
 */
public final class KeyLocker {
    private static final int KEY_SIZE = 512;
    private byte[] publicKey; 
    private byte[] privateKey;
    
    protected final String ALGORITHM = "RSA";

    protected KeyLocker() {
        this.regenKeyPair();
    }
    
    protected void regenKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            
            keyGen.initialize(KEY_SIZE, random);
            KeyPair generateKeyPair = keyGen.generateKeyPair();
        
            this.publicKey = generateKeyPair.getPublic().getEncoded();
            this.privateKey = generateKeyPair.getPrivate().getEncoded();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.out.println(e.getMessage());
        }
    }
    
    protected byte[] getPublicKey() {
        return Arrays.copyOf(publicKey, publicKey.length);
    }
    
    protected byte[] getPrivateKey() {
        return Arrays.copyOf(privateKey, privateKey.length);
    }
}
