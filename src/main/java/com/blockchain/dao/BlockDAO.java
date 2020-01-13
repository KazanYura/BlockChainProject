package com.blockchain.dao;

import com.blockchain.entity.Block;

import java.util.List;


public interface BlockDAO {
    void addBlock(Block block);
    void editBlock(Block block);
    List<Block> getAllBlocks();
    List<Block> getBlockByUserId(int userId);
    Block getBlockByHash(String hash);
}
