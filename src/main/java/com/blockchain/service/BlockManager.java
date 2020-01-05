package com.blockchain.service;

import com.blockchain.entity.Block;

import java.util.List;

public interface BlockManager {
    void addBlock(Block block);

    List<Block> getAllBlocks();
}
