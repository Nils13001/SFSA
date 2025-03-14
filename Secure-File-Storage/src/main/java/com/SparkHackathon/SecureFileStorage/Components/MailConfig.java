package com.SparkHackathon.SecureFileStorage.Components;

import com.SparkHackathon.SecureFileStorage.Service.AwsSecretsManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

@Configuration
public class MailConfig {

    private final AwsSecretsManagerService awsSecretsManagerService;

    public MailConfig(AwsSecretsManagerService awsSecretsManagerService) {
        this.awsSecretsManagerService = awsSecretsManagerService;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");

//        mailSender.setPort(587);
        mailSender.setPort(465);
        Map<String, String> gmailCredentials = awsSecretsManagerService.getGmailCredentials();

        String username = gmailCredentials.get("username");
        String password = gmailCredentials.get("password");

        mailSender.setUsername(username); // Gmail username (email)
        mailSender.setPassword(password);
        mailSender.setPort(587);
        

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}
