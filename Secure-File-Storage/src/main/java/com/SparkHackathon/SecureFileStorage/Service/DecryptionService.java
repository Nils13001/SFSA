package com.SparkHackathon.SecureFileStorage.Service;

//import org.springframework.stereotype.Service;
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

//@Service
public class DecryptionService {

    private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";

    public void decryptFile(String encryptedFilePath, String decryptedFilePath) throws Exception {
        // Read encrypted AES key & private key from files (For testing, in reality, this will be stored securely)
        byte[] encryptedAesKey = Base64.getDecoder().decode(Files.readAllBytes(Paths.get("C:\\FTE_Data\\Project Work\\Secure-File-Storage\\encrypted_aes_key.txt")));
        byte[] privateKeyBytes = Base64.getDecoder().decode(Files.readAllBytes(Paths.get("C:\\FTE_Data\\Project Work\\Secure-File-Storage\\private_key.txt")));

        // Convert private key bytes to PrivateKey object
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        // Decrypt AES Key using RSA
        byte[] aesKeyBytes = decryptRSA(encryptedAesKey, privateKey);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, AES_ALGORITHM);

        // Read encrypted file
        byte[] encryptedData = Files.readAllBytes(Paths.get(encryptedFilePath));

        // Decrypt file data
        byte[] decryptedData = decryptAES(encryptedData, aesKey);

        // Save decrypted file
        try (FileOutputStream fos = new FileOutputStream(decryptedFilePath)) {
            fos.write(decryptedData);
        }
    }

    private byte[] decryptAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private byte[] decryptRSA(byte[] data, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
