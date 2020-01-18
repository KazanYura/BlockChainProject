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

    @PostMapping(value = "/add")
    public ResponseEntity addUser(@RequestBody User user) {
        user.setBalance((float) 100.0);
        userManager.addUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody User user) {
        if (validateUser(user)){
            userManager.deleteUser(user.getId());
            return ResponseEntity.ok(user.getId());
        }
        else{
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @PostMapping("/validate")
    public ResponseEntity validateUserWithRoute(@RequestBody User user){
        if (validateUser(user))
            return ResponseEntity.ok(this.userManager.getUser(user));
        else
            return ResponseEntity.status(404).body("User not found");
    }
    private boolean validateUser(User user){
        return userManager.validateUser(user);
    }
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}