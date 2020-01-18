package com.blockchain.game_main;

public class Bet {
    private String gameHash;
    private String userId;
    private String pass;
    private float bet;
    private int number;
    public String getGameHash() {
        return gameHash;
    }

    public void setGameHash(String gameHash) {
        this.gameHash = gameHash;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBet(float bet) {
        this.bet = bet;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    public float getBet() {
        return bet;
    }

    public int getNumber() {
        return number;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
