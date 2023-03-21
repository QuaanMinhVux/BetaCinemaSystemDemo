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
import java.security.SecureRandom;

@Service
public class ResetPasswordService {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    private static final int EXPIRATION = 60 * 24;
    public void sendResetLink(String email, User u) throws MessagingException, UserPrincipalNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserPrincipalNotFoundException("no such user" + email);
        }
        String newPwd= randomString();
        String message = "Your new password is: " + newPwd;
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(email);
        helper.setSubject("Reset Password");
        helper.setText(message);
        javaMailSender.send(mail);
        u.setPassword(newPwd);
        userRepository.save(u);
    }
    String randomString(){
        StringBuilder sb = new StringBuilder(8);
        for(int i = 0; i < 8; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
