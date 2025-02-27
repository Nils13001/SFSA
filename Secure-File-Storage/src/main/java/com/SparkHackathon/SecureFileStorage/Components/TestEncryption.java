//package com.SparkHackathon.SecureFileStorage.Components;
//
//import com.SparkHackathon.SecureFileStorage.Service.DecryptionService;
//import com.SparkHackathon.SecureFileStorage.Service.EncryptionService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//@Component
//public class TestEncryption implements CommandLineRunner {
//
//    private final EncryptionService encryptionService;
//    private final DecryptionService decryptionService;
//
//    public TestEncryption(EncryptionService encryptionService, DecryptionService decryptionService) {
//        this.encryptionService = encryptionService;
//        this.decryptionService = decryptionService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//
//        // Get original file path
//        System.out.print("Enter the path of the file to encrypt: ");
//        String originalFilePath = scanner.nextLine().trim();
//
//        // Ask user for encrypted file name
//        System.out.print("Enter a new name for the encrypted file: ");
//        String encryptedFilePath = scanner.nextLine().trim();
//        if (encryptedFilePath.isEmpty()) {
//            encryptedFilePath = originalFilePath; // Keep the same name
//        } else {
//            encryptedFilePath = Paths.get(originalFilePath).getParent() + "/" + encryptedFilePath;
//        }
//
//        // Perform encryption
//        System.out.println("Encrypting file: " + originalFilePath);
//        encryptionService.encryptFile(originalFilePath, encryptedFilePath);
//        System.out.println("Encryption complete! Encrypted file saved at: " + encryptedFilePath);
//
//        // Ask user for decrypted file name
//        System.out.print("Enter a new name for the decrypted file: ");
//        String decryptedFilePath = scanner.nextLine().trim();
//        if (decryptedFilePath.isEmpty()) {
//            decryptedFilePath = originalFilePath; // Keep the same name
//        } else {
//            decryptedFilePath = Paths.get(originalFilePath).getParent() + "/" + decryptedFilePath;
//        }
//
//        // Perform decryption
//        System.out.println("Decrypting file: " + encryptedFilePath);
//        decryptionService.decryptFile(encryptedFilePath, decryptedFilePath);
//        System.out.println("Decryption complete! Decrypted file saved at: " + decryptedFilePath);
//
//        scanner.close();
//    }
//}
