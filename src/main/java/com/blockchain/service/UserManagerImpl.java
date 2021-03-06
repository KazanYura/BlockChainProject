package com.blockchain.service;

import java.util.List;

import com.blockchain.dao.UserDAO;
import com.blockchain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    @Service
    public class UserManagerImpl implements UserManager
    {
        @Autowired
        private UserDAO userDAO;
        @Override
        @Transactional
        public void addUser(User user) {
            userDAO.addUser(user);
        }

        @Override
        @Transactional
        public List<User> getAllUsers() {
            return userDAO.getAllUsers();
        }
        @Override
        @Transactional
        public void deleteUser(Long UserId) {
            userDAO.deleteUser(UserId);
        }

        @Override
        public User getUser(User user) {
            return userDAO.getUser(user);
        }

        @Override
        public boolean validateUser(User user) {
            return userDAO.validateUser(user);
        }

        public void setEmployeeDAO(UserDAO employeeDAO) {
            this.userDAO = employeeDAO;
        }
    }