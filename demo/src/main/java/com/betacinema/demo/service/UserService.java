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
    public void addUser(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUserByID(int id) {
        return repository.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        user = repository.findByEmail(email);
        return user;
    }

    @Override
    public User resetPassword(User user, String password) {
        User u = getUserByEmail(user.getEmail());
        repository.delete(u);
        u.setPassword(password);
        repository.save(u);
        return u;
    }


}
