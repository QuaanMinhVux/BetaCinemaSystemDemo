package com.betacinema.demo.controller;

import com.betacinema.demo.service.IUser;
import com.betacinema.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUser iUser;
    @GetMapping("/user")
    public List<User> getAll(){
        return iUser.getAllUser();
    }
    @PostMapping("/register")
    public ResponseEntity<User> createNew(@RequestBody User user){
        User u = iUser.getUserByEmail(user.getEmail());
        if(u != null){
            return ResponseEntity.badRequest().build();
        }else{
            iUser.addUser(user);
            return ResponseEntity.ok(user);
        }
    }


}
