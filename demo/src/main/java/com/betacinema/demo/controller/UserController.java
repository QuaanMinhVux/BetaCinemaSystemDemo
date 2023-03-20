package com.betacinema.demo.controller;

import com.betacinema.demo.entity.PasswordEntity;
import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.ResetPasswordService;
import jakarta.mail.MessagingException;
import org.jetbrains.annotations.NotNull;
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
    @GetMapping("/users")
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
        if(iUser.getUserByEmail(email) == null){
            return ResponseEntity.notFound().build();
        }
        resetPasswordService.sendResetLink(email);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable("id") String id, @RequestBody PasswordEntity password){
        try {
            if(iUser.getUserByID(Integer.parseInt(id)) == null){
                return ResponseEntity.notFound().build();
            }
            iUser.resetPassword(id,password.getPassword());
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateInformation(@RequestBody @NotNull User user){
        User u = iUser.getUserByEmail(user.getEmail());
        try {
            if(u == null){
                return ResponseEntity.notFound().build();
            }
            u = iUser.update(user);
            return ResponseEntity.ok(u);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable("id") String id){
        User u = iUser.getUserByID(Integer.parseInt(id));
        if(u == null){
            return ResponseEntity.notFound().build();
        }
        try {
            iUser.delete(u);
        }catch (Exception e){

        }
        return ResponseEntity.noContent().build();

    }
    @GetMapping("user/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User u = iUser.getUserByEmail(email);
        if(u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }


}
