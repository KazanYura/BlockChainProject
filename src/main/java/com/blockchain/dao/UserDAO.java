package com.blockchain.dao;

import com.blockchain.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User employee);

    List<User> getAllUsers();

    void deleteUser(Integer UserId);
}
