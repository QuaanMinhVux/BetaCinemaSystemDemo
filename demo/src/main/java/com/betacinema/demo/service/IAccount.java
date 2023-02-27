package com.betacinema.demo.service;

import com.betacinema.demo.entity.Account;

import java.util.List;

public interface IAccount {
    public List<Account> getAll();
    public boolean checkAccount(Account account);
    public Account get(String email);
}
