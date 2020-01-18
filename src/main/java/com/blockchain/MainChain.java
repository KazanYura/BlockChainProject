package com.blockchain;

import com.blockchain.entity.Block;
import com.blockchain.transaction.TransactionOutput;
import com.blockchain.transaction.Wallet;

import java.util.ArrayList;
import java.util.HashMap;

public class MainChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>(); //list of all unspent transactions.
    public static float minimumTransaction;
}