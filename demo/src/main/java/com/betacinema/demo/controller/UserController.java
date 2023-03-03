package com.betacinema.demo.controller;

import com.betacinema.demo.entity.PasswordEntity;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.ResetPasswordService;
import jakarta.mail.MessagingException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUser iUser;
    @Autowired
    private ResetPasswordService resetPasswordService;
    @GetMapping("/user")
    public List<User> getAll(){
        return iUser.getAllUser();
    }
    @PostMapping("/register")
    public ResponseEntity<User> createNew(@RequestBody User user) {
        User u = iUser.getUserByEmail(user.getEmail());
        if(u != null){
            return ResponseEntity.badRequest().build();
        }else{
            try {
                iUser.addUser(user);
            }catch(Exception exception){
            }
            return ResponseEntity.ok(user);
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Void> sendResetLink(@RequestParam String email) throws UserPrincipalNotFoundException, MessagingException {
        resetPasswordService.sendResetLink(email);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable("id") String id, @RequestBody PasswordEntity password){
        iUser.resetPassword(id,password.getPassword());
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateInformation(@PathVariable("id") String id){
        iUser.update(iUser.getUserByID(Integer.parseInt(id)));
        return ResponseEntity.noContent().build();
    }


}
