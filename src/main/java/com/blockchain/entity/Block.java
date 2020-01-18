package com.blockchain.entity;

import com.blockchain.hash.Hasher;
import com.blockchain.transaction.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="blocks")
public class Block {

    @Column(name="userId")
    private int userId;
    @Column(name="gameResults")
    private int gameResults;
    @Column(name="prevHash")
    private String previousHash;
    @Column(name="last")
    private boolean last = true;
    @Column(name="timeStamp")
    private long timeStamp = new Date().getTime();
    @Id
    @Column(name="hash")
    private String hash = Hasher.applySha256(previousHash +Long.toString(timeStamp)+Integer.toString(userId*gameResults));
    public ArrayList<Transaction> transactions = new ArrayList<>(); //our data will be a simple message.

    public String getHash() {
        return hash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameResults() {
        return gameResults;
    }

    public void setGameResults(int gameResults) {
        this.gameResults = gameResults;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if(transaction == null) return false;
        if((!previousHash.equals("0"))) {
            if((!transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}
