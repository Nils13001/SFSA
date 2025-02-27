package com.SparkHackathon.SecureFileStorage.Service;

import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";

    public void encryptFile(String filePath, String encryptedFilePath) throws Exception {
        // Generate AES Key (Static for Now)
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(256);  // AES-256
        SecretKey aesKey = keyGenerator.generateKey();

        // Encrypt file with AES
        byte[] fileData = Files.readAllBytes(Paths.get(filePath));
        byte[] encryptedData = encryptAES(fileData, aesKey);

        // Generate RSA Key Pair (Static for Now)
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt AES key with RSA
        byte[] encryptedAesKey = encryptRSA(aesKey.getEncoded(), publicKey);

        // Save encrypted file
        try (FileOutputStream fos = new FileOutputStream(encryptedFilePath)) {
            fos.write(encryptedData);

        }

        // Save the encrypted AES key & private key for testing (Later we'll handle it securely)
        try (FileOutputStream fos = new FileOutputStream("encrypted_aes_key.txt")) {
            fos.write(Base64.getEncoder().encode(encryptedAesKey));
        }
        try (FileOutputStream fos = new FileOutputStream("private_key.txt")) {
            fos.write(Base64.getEncoder().encode(privateKey.getEncoded()));
        }
    }

    private byte[] encryptAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private byte[] encryptRSA(byte[] data, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
