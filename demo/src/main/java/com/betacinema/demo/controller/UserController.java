package com.betacinema.demo.controller;

import com.betacinema.demo.service.IUser;
import com.betacinema.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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


}
