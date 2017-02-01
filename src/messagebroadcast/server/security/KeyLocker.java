package messagebroadcast.server.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

public final class KeyLocker {
    //Encryption key size.
    public static final int KEY_SIZE = 2048;
    
    private byte[] publicKey; 
    private byte[] privateKey;
    
    // Encryption algorithm.
    protected final String ALGORITHM = "RSA";

    protected KeyLocker() {
        this.regenKeyPair();
    }
    
    // Generates a key pair.
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
    
    // Returns the current public key.
    protected byte[] getPublicKey() {
        return Arrays.copyOf(publicKey, publicKey.length);
    }
    
    // Returns the current private key.
    protected byte[] getPrivateKey() {
        return Arrays.copyOf(privateKey, privateKey.length);
    }
    
    // Returns the current encryption key size (in bits).
    public static int getKeySize() {
        return KEY_SIZE;
    }
}
