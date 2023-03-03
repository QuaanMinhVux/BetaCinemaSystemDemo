package com.betacinema.demo.service;

import com.betacinema.demo.entity.User;
import java.util.List;

public interface IUser {
    public void addUser(User user) throws Exception;
    public List<User> getAllUser();
    public User getUserByID(int id);
    public User getUserByEmail(String email);
    public User resetPassword(String id, String password);
    public User delete(User user) throws Exception;
    public User update(User user);
}
