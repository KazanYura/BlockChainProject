package com.blockchain.exceptions;

public class GameNotFoundException extends Exception {
    private int status = 404;
    private String title = "Game not found";
    public GameNotFoundException(String message){
        super(message);
    }
}
