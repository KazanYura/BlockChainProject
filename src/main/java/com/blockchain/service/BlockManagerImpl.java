package com.blockchain.service;

import java.util.Date;
import java.util.List;

import com.blockchain.dao.BlockDAO;
import com.blockchain.entity.Block;
import com.blockchain.hash.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockManagerImpl implements BlockManager
{
    @Autowired
    private BlockDAO blockDAO;
    @Override
    @Transactional
    public void addBlock(Block block) {
        blockDAO.addBlock(block);
    }

    @Override
    @Transactional
    public List<Block> getAllBlocks() {
        return blockDAO.getAllBlocks();
    }

    public void setBlockDAO(BlockDAO blockDAO) {
        this.blockDAO = blockDAO;
    }
}