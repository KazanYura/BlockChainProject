package com.blockchain.service;


import java.util.Comparator;
import java.util.List;
import com.blockchain.dao.BlockDAO;
import com.blockchain.entity.Block;
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
        if (block.getPreviousHash() == null) {
            Block edited = getUserLastBlock(block.getUserId());
            if (edited == null) {
                block.setPreviousHash("0");
                blockDAO.addBlock(block);
            }
            else {
                edited.setLast(false);
                block.setPreviousHash(edited.getHash());
                blockDAO.editBlock(edited);
                blockDAO.addBlock(block);
            }
        }
        else{
            blockDAO.addBlock(block);
        }
    }
    @Override
    @Transactional
    public void editBlock(Block block){
        blockDAO.editBlock(block);
    }
    @Override
    @Transactional
    public List<Block> getAllBlocks() {
        return blockDAO.getAllBlocks();
    }

    @Override
    public Block getUserLastBlock(int userId) {
        List<Block> blocks = blockDAO.getBlockByUserId(userId);
        if (blocks.size() == 0)
            return null;
        else
            return blocks.get(blocks.size()-1);
    }

    @Override
    public Block getOneBlock(String hash) {
        return blockDAO.getBlockByHash(hash);
    }

    public void setBlockDAO(BlockDAO blockDAO) {
        this.blockDAO = blockDAO;
    }
}