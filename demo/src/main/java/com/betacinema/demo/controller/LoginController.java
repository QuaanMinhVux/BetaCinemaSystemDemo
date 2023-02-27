package com.betacinema.demo.controller;

import com.betacinema.demo.entity.Account;
import com.betacinema.demo.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private IAccount account;
    @GetMapping("/login")
    public List<Account> getAll(){
        return account.getAll();
    }
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account acc){
        boolean check = false;
        check = account.checkAccount(acc);
        if(!check){
            return ResponseEntity.notFound().build();
        }else{
            Account account1 = account.get(acc.getEmail());
            return ResponseEntity.ok(account1);
        }
    }
}
