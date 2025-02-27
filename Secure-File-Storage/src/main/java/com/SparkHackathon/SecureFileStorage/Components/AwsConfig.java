package com.SparkHackathon.SecureFileStorage.Components;
import com.SparkHackathon.SecureFileStorage.Service.AwsSecretsManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

//
//@Configuration
//public class AwsConfig {
//    @Bean
//    public S3Client s3Client() {
//        return S3Client.builder()
//                .region(Region.US_EAST_1) // Change to your AWS region
//                .credentialsProvider(ProfileCredentialsProvider.builder().build())
//                .build();
//    }
//}

@Configuration
public class AwsConfig {

//    @Value("${aws.accessKey}")
//    private String accessKey;
//
//    @Value("${aws.secretKey}")
//    private String secretKey;
//
//    @Value("${aws.region}")
//    private String region;

//    @Bean
//    public S3Client s3Client() {
//        return S3Client.builder()
//                .region(Region.of(region)) // Reads region from properties
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create(accessKey, secretKey)
//                )) // Uses hardcoded credentials
//                .build();
//    }

    private final AwsSecretsManagerService awsSecretsManagerService;

    public AwsConfig(AwsSecretsManagerService awsSecretsManagerService) {
        this.awsSecretsManagerService = awsSecretsManagerService;
    }

    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider = awsSecretsManagerService.getAwsCredentials();

        return S3Client.builder()
                .region(Region.US_EAST_1) // Change to your AWS region
                .credentialsProvider(credentialsProvider)
                .build();
    }
}