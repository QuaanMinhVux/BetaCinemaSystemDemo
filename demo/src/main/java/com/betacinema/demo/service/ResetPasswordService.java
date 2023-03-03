package com.betacinema.demo.service;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class ResetPasswordService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    private static final int EXPIRATION = 60 * 24;
    public void sendResetLink(String email) throws MessagingException, UserPrincipalNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserPrincipalNotFoundException("no such user" + email);
        }
        String url = "http://localhost:8080/reset-password/=" + user.getUserID();
        String message = "Please click the link bellow to reset your password: " + url;
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(email);
        helper.setSubject("Password Rest Link: ");
        helper.setText(message);
        javaMailSender.send(mail);
    }
}
