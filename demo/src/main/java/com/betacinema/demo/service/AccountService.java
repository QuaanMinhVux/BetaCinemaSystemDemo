package com.betacinema.demo.service;

import com.betacinema.demo.entity.Account;
import com.betacinema.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements IAccount{
    @Autowired
    private AccountRepository repository;
    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean checkAccount(Account account) {
        Account acc = null;
        acc = repository.findByEmail(account.getEmail());
        if(acc == null){
            return false;
        }else{
            if(acc.getPassword().equals(account.getPassword())){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public Account get(String email) {
        return repository.findByEmail(email);
    }

}
