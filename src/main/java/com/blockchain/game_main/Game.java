package com.blockchain.game_main;

import com.blockchain.entity.User;
import com.blockchain.hash.Hasher;
import com.blockchain.transaction.Wallet;

import java.util.ArrayList;
import java.util.Date;

public class Game {
    private ArrayList<UserGameHolder> users = new ArrayList<>();
    private int limit = 10;
    public Wallet wallet;
    private Long timestamp = new Date().getTime();
    private String hash;
    public boolean isEmptySpace(){
        return users.size() < limit;
    }
    public void addPlayer(UserGameHolder user){
        users.add(user);
    }
    public Game(){
        wallet = new Wallet();
        hash = Hasher.applySha256(wallet.publicKey.toString() + Integer.toString(limit)
                + Long.toString(timestamp));
    }

    public String getHash() {
        return hash;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users_simple = new ArrayList<>();
        for (UserGameHolder u: users) {
            users_simple.add(u.getUser());
        }
        return users_simple;
    }

    public byte[] getWallet() {
        return wallet.publicKey.getEncoded();
    }
}
