package com.SparkHackathon.SecureFileStorage.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendFileUploadNotification(String toEmail, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("File Uploaded Successfully");
        helper.setText("Dear User,\n\nYour file has been uploaded successfully to AWS S3.\n\nBest Regards,\nSecure File Storage Team");

        // Attach encrypted file
        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(message);
        System.out.println("Email sent successfully to: " + toEmail);
    }

    public void sendFileAlreadyExistsNotification(String toEmail, String filePath) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("File Exists Already");
        helper.setText("Dear User,\n\nYour file has not been uploaded as it already exists in AWS S3.\n\nBest Regards,\nSecure File Storage Team");

        mailSender.send(message);
        System.out.println("Email sent successfully to: " + toEmail);
}
}
