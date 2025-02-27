package com.SparkHackathon.SecureFileStorage.Controller;

import com.SparkHackathon.SecureFileStorage.Service.EncryptionService;
import com.SparkHackathon.SecureFileStorage.Service.MailService;
import com.SparkHackathon.SecureFileStorage.Service.S3Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final EncryptionService encryptionService;
    private final MailService mailService;
    public S3Controller(S3Service s3Service, EncryptionService encryptionService, MailService mailService){
        this.s3Service = s3Service;
        this.encryptionService = encryptionService;
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String testing() {
        return "Hello World";
    }

    @GetMapping("/list-files")
    public List<String> listFiles() {
        return s3Service.listFiles();
    }

    @GetMapping("/download-url")
    public String getDownloadUrl(@RequestParam("fileName") String fileName) {
        return s3Service.getDownloadUrl(fileName);
    }


//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") String file,
//                             @RequestParam("action") String action,
//                             @RequestParam(value = "newFileName", required = false) String newFileName)
//            throws Exception {
//        if(Objects.equals(action, "upload"))
//            return s3Service.uploadFile(file);
//        else if (Objects.equals(action, "encrypt")) {
//            String newFilePath = Paths.get(file).getParent() + "/" + newFileName;
//            encryptionService.encryptFile(file, newFilePath);
//            System.out.println("File Encrypted Successfully...");
//            return s3Service.uploadFile(newFilePath);
//        }else
//            return "Wrong Action";
//    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") String file,
                             @RequestParam("action") String action,
                             @RequestParam(value = "newFileName", required = false) String newFileName,
                             @RequestParam(value = "sendEmail", required = false, defaultValue = "false") boolean sendEmail,
                             @RequestParam(value = "email", required = false) String email) throws Exception {

        File localFile = new File(file);
        if (!localFile.exists() || localFile.isDirectory()) {
            return "Error: The provided file path is incorrect or the file does not exist.";
        }

        String uploadedFilePath;
        String fileToSend = file;
        String uploadStatus;

        if (Objects.equals(action, "upload")) {
            uploadStatus = s3Service.uploadFile(file);
        } else if (Objects.equals(action, "encrypt")) {
            String newFilePath = Paths.get(file).getParent() + "/" + newFileName;
            encryptionService.encryptFile(file, newFilePath);
            uploadStatus = s3Service.uploadFile(newFilePath);
            fileToSend = newFilePath;
        } else {
            return "Error: Invalid action.";
        }

        // If file already exists, notify the user via email
        if (uploadStatus.contains("File already exists")) {
            if (sendEmail && email != null && !email.isEmpty()) {
                mailService.sendFileAlreadyExistsNotification(email, file);
            }
            return uploadStatus;
        }

        // If file was uploaded, send success email
        if (sendEmail && email != null && !email.isEmpty()) {
            mailService.sendFileUploadNotification(email, fileToSend);
        }

        return uploadStatus;
    }


    //Working Perfect, just uploading using path
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") String file) throws IOException {
//        return s3Service.uploadFile(file);
//    }

    //Working Perfectly, just uploading directly
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        return s3Service.uploadFile(file);
//    }
}
