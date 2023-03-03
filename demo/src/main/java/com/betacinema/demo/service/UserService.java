package com.betacinema.demo.service;

import com.betacinema.demo.repository.UserRepository;
import com.betacinema.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        User u = repository.findById(Integer.parseInt(id)).get();
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
        User u = repository.findById(user.getUserID()).get();
        User u2 = repository.findByEmail(user.getEmail());
        if(u == null){
            throw new RuntimeException("User doesn't exist");
        }else if(u2 != null){

            throw new RuntimeException("Email incorrect");
        }
        else{
            repository.delete(u);
            repository.save(user);
        }
        return user;
    }


}
