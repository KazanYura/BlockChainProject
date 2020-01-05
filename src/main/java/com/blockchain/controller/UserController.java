package com.blockchain.controller;

import com.blockchain.entity.User;
import com.blockchain.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserManager userManager;

    @GetMapping(value = "/")
    public ResponseEntity listUsers() {
        return ResponseEntity.ok(userManager.getAllUsers());
    }

    @PostMapping(value = "/add")
    public ResponseEntity addUser(@RequestBody User user) {
        userManager.addUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody Integer userId) {
        userManager.deleteUser(userId);
        return ResponseEntity.ok(userId);
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}