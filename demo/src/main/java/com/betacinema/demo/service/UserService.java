package com.betacinema.demo.service;

import com.betacinema.demo.repository.UserRepository;
import com.betacinema.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUser{
    @Autowired
    private UserRepository repository;

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUserByID(int id) {
        return repository.getById(id);
    }
}
