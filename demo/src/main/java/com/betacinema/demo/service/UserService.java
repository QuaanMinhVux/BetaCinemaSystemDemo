package com.betacinema.demo.service;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService implements IUser{
    @Autowired
    private UserRepository repository;

    @Override
    public void addUser(User user){
        User u = repository.findByEmail(user.getEmail());
        if(u == null){
            repository.save(user);
        }else{
            throw new RuntimeException("Email exsist");
        }
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUserByID(int id) {
        User user =null;
        user = repository.getById(id);
        if(user != null) {
            return user;
        }else{
            throw new RuntimeException("User not exsist");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        user = repository.findByEmail(email);
        return user;
    }

    @Override
    public User resetPassword(String id, String password) {
        User u = null;
        u = repository.findById(Integer.parseInt(id)).get();
        repository.delete(u);
        u.setPassword(password);
        repository.save(u);
        return u;
    }

    @Override
    public User delete(User user){
        User u = repository.findByEmail(user.getEmail());
        if(u != null){
            repository.delete(u);
            return u;
        }else{
            throw new RuntimeException("User doesn't exsist");
        }
    }

    @Override
    public User update(User user) {
        User u = repository.findByEmail(user.getEmail());
        if(u == null){
            throw new RuntimeException("User doesn't exist");
        }
        else{
            repository.delete(u);
            u.setVip(user.isVip());
            u.setBalance(user.getBalance());
            u.setUserName(user.getUserName());
            repository.save(u);
        }
        return u;
    }

    @Override
    public User update(User user, BigDecimal balance) {
        User u = getUserByEmail(user.getEmail());
        if(u == null){
            return null;
        }else{
            u.setBalance(balance);
            repository.delete(user);
            repository.save(u);
        }
        return u;
    }

    @Override
    public User update(User user, boolean VIP) {
        User u = getUserByEmail(user.getEmail());
        if(u == null){
            return null;
        }else{
            u.setVip(VIP);
            repository.delete(user);
            repository.save(u);
        }
        return u;
    }


}
