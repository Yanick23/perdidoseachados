package com.perdidoseachados.perdidoseachados.Servicies;

import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

;

    public void sendResetTokenEmail(String contextPath, String locale, String token, String userEmail) {
        String url = contextPath + "/user/changePassword?token=" + token;
        String message = "Reset Password: " + url;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Reset Password");
        email.setText(message);
        email.setTo(userEmail);
        email.setFrom("yanick.ribeiro23@gamil.com");

        mailSender.send(email);
    }
}
