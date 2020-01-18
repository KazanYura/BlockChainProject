package com.blockchain.game_main;

import com.blockchain.entity.User;
import com.blockchain.transaction.Wallet;

public class UserGameHolder {
    private Wallet wallet;
    private User user;

    public  Wallet getWallet() {
        return wallet;
    }

    public  void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public  User getUser() {
        return user;
    }

    public  void setUser(User user) {
        this.user = user;
    }
}
