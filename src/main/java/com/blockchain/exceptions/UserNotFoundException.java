package com.blockchain.exceptions;

public class UserNotFoundException extends Exception {
    private int status = 404;
    private String title = "User not found";
    public UserNotFoundException(String message){
        super(message);
    }
}
