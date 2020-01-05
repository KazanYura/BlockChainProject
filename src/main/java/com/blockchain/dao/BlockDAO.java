package com.blockchain.dao;

import com.blockchain.entity.Block;

import java.util.List;

public interface BlockDAO {
    void addBlock(Block block);

    List<Block> getAllBlocks();
}
