package com.SparkHackathon.SecureFileStorage.Components;

import com.SparkHackathon.SecureFileStorage.Service.DecryptionService;

import java.nio.file.Paths;
import java.util.Scanner;


public class DecryptionUtility {

    public static void main(String[] args) throws Exception {

        DecryptionService decryptionService = new DecryptionService();

        Scanner scanner = new Scanner(System.in);

        // Get original file path
        System.out.print("Enter the path of the file to decrypt: ");
        String encryptedFilePath = scanner.nextLine().trim();

        // Ask user for decrypted file name
        System.out.print("Enter a new name for the decrypted file: ");
        String decryptedFilePath = scanner.nextLine().trim();
        if (decryptedFilePath.isEmpty()) {
            decryptedFilePath = encryptedFilePath; // Keep the same name
        } else {
            decryptedFilePath = Paths.get(encryptedFilePath).getParent() + "/" + decryptedFilePath;
        }

        // Perform decryption
        System.out.println("Decrypting file: " + encryptedFilePath);
        decryptionService.decryptFile(encryptedFilePath, decryptedFilePath);
        System.out.println("Decryption complete! Decrypted file saved at: " + decryptedFilePath);

        scanner.close();
    }
}
