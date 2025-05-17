package com.gpt.login_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    public void sendVerificationTokenEmail(String email, String token){
        String subject = "Varify your email to Sign Up on GymProgressTracker APP!!";
        String verificationUrl = "http://localhost:8080/api/v1/register/confirmToken?token=" + token;
        String htmlContent = """
            <html>
                <body>
                    <h2>Welcome to Gym Progress Tracker!</h2>
                    <p>Hi there,</p>
                    <p>Thanks for signing up. Please click the button below to verify your email address:</p>
                    <a href="%s" style="display:inline-block;background-color:#28a745;color:#fff;padding:10px 20px;
                    text-decoration:none;border-radius:5px;">Verify Email</a>
                    <p>If the button doesn't work, copy and paste the following URL into your browser:</p>
                    <p><a href="%s">%s</a></p>
                    <br>
                    <p>Thanks,<br>The Gym Progress Tracker Team</p>
                </body>
            </html>
        """.formatted(verificationUrl, verificationUrl, verificationUrl);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = is HTML
            helper.setFrom("noreply@gymtracker.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email", e);
    }




    }
}
