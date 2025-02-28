package com.SparkHackathon.SecureFileStorage.Service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class AwsSecretsManagerService {

    private final SecretsManagerClient secretsManagerClient;
    private final ObjectMapper objectMapper;

    public AwsSecretsManagerService() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(Region.US_EAST_1) // Change to your AWS region
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public AwsCredentialsProvider getAwsCredentials() {
        String secretName = "AwsCredentials"; // Name of your secret in Secrets Manager

        GetSecretValueRequest request = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);

        try {
            Map<String, String> secretMap = objectMapper.readValue(response.secretString(), Map.class);

            String accessKey = secretMap.get("accessKey");
            String secretKey = secretMap.get("secretKey");

            return StaticCredentialsProvider.create(new AwsCredentials() {
                @Override
                public String accessKeyId() {
                    return accessKey;
                }

                @Override
                public String secretAccessKey() {
                    return secretKey;
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AWS credentials from Secrets Manager", e);
        }
    }

    public Map<String, String> getGmailCredentials() {
        String secretName = "GmailCredentials"; // Name of your secret in Secrets Manager

        GetSecretValueRequest request = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);

        try {
            Map<String, String> secretMap = objectMapper.readValue(response.secretString(), Map.class);

            String username = secretMap.get("username");
            String password = secretMap.get("password");

            return secretMap;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gmail credentials from Secrets Manager", e);
        }
    }
}
