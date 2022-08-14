package com.payman.utils;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

public class RSA {
    private static KeyPairGenerator keyPairGenerator = null;
    private final SecureRandom random = new SecureRandom();

    public KeyPair getKeyPair() {
        return keyPairGenerator.genKeyPair();
    }

    public String encryptText(String inputString, Key pubkey) {
        try {
            byte[] inputStringBytes = inputString.getBytes();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] cipherText = cipher.doFinal(inputStringBytes);
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            return  null;
        }
    }

    public String decryptText(String inputString, Key privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] inputStringBytes = Base64.getDecoder().decode(inputString.getBytes());
            byte[] plainText = cipher.doFinal(inputStringBytes);
            return new String(plainText);
        } catch (Exception e) {
            return "null";
        }
    }

    @PostConstruct
    private void init() {
        try {
            if (keyPairGenerator == null) keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
