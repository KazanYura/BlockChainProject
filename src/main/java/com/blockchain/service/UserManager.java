package com.blockchain.service;

import com.blockchain.entity.User;

import java.util.List;

public interface UserManager {
    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(Integer userId);
}
