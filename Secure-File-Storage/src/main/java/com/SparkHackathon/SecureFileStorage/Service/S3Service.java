package com.SparkHackathon.SecureFileStorage.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String getDownloadUrl(String fileName) {
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        return s3Client.utilities().getUrl(request).toString();
    }

    public List<String> listFiles() {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        return response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    //Working Perfectly, Providing the File Path and Uploading
    public String uploadFile(String filePath) throws IOException {
//        Scanner sc = new Scanner(System.in);

        String key = Paths.get(filePath).getFileName().toString();
        try {
            s3Client.headObject(HeadObjectRequest.builder().bucket(bucketName).key(key).build());
            return "File already exists: " + key;
        } catch (S3Exception e) {
            if (e.statusCode() != 404) {
                throw e; // Rethrow if it's not a 'Not Found' error
            }
        }
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(Files.readAllBytes(Paths.get(filePath))));
        return "File uploaded successfully: " + key;
    }


//    //Working Perfectly, Providing the File Path and Uploading
//    public String uploadFile(String filePath) throws IOException {
//        byte[] fileData = Files.readAllBytes(Paths.get(filePath));
//        String key = Paths.get(filePath).getFileName().toString();
//        try {
//            s3Client.headObject(HeadObjectRequest.builder().bucket(bucketName).key(key).build());
//            return "File already exists: " + key;
//        } catch (S3Exception e) {
//            if (e.statusCode() != 404) {
//                throw e; // Rethrow if it's not a 'Not Found' error
//            }
//        }
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(Files.readAllBytes(Paths.get(filePath))));
//        return "File uploaded successfully: " + key;
//    }

    //Working Perfectly, Providing the File itself
//    public String uploadFile(MultipartFile file) throws IOException {
//        String key = file.getOriginalFilename();
//        try {
//            s3Client.headObject(HeadObjectRequest.builder().bucket(bucketName).key(key).build());
//            return "File already exists: " + key;
//        } catch (S3Exception e) {
//            if (e.statusCode() != 404) {
//                throw e; // Rethrow if it's not a 'Not Found' error
//            }
//        }
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
//        return "File uploaded successfully: " + key;
//    }

    public String deleteFile(String filename){

        return "File Deleted Successfully";
    }

//    public void uploadFile(String bucketName, String fileName, byte[] fileData) {
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(fileName)
//                .build();
//
//        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));
//        System.out.println("File uploaded successfully: " + fileName);
//    }

}
