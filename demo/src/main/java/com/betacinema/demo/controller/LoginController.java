package com.betacinema.demo.controller;
import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private IUser iUser;
    @GetMapping("/get")
    public User getUser(@RequestBody User user){
        User u = iUser.getUserByEmail(user.getEmail());
        return u;
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User u = iUser.getUserByEmail(user.getEmail());
        boolean check = u.getPassword().equals(user.getPassword());
        if(user == null){
            return ResponseEntity.notFound().build();
        }else{
            if(check){
                return ResponseEntity.ok(u);
            }else{
                return ResponseEntity.notFound().build();
            }
        }
    }
    @GetMapping("/check")
    public boolean check(@RequestBody User user){
        User u = iUser.getUserByEmail(user.getEmail());
        return u.getPassword().equals(user.getPassword());
    }
}
