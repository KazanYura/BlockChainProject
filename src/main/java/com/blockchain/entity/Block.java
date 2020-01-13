package com.blockchain.entity;

import com.blockchain.hash.Hasher;

import javax.persistence.*;
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
}