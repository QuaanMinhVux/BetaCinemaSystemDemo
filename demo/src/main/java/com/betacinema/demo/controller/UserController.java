package com.betacinema.demo.controller;

import com.betacinema.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String test(){
        return "Hello world";
    }
    public User get(@RequestBody User user){
        return user;
    }

}
