package com.blockchain.service;

import com.blockchain.entity.Block;

import java.util.List;
import java.util.Optional;

public interface BlockManager {
    void addBlock(Block block);
    void editBlock(Block block);
    List<Block> getAllBlocks();
    Block getUserLastBlock(int userId);
    Block getOneBlock(String hash);
}
