package com.blockchain.dao;

import com.blockchain.entity.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class BlockDAOImpl implements BlockDAO {

    @Autowired
    private EntityManager entityManager;

    private void deleteBlock(String hash) {
        Block block = entityManager.getReference(Block.class, hash);
        if (null != block) {
            this.entityManager.remove(block);
        }
    }
    @Override
    public void addBlock(Block block) {
        this.entityManager.persist(block);
    }

    @Override
    public void editBlock(Block block) {
        Block block_1 = getBlockByHash(block.getHash());
        if (block_1 != null){
            deleteBlock(block_1.getHash());
            addBlock(block);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Block> getAllBlocks() {
        return this.entityManager.createNativeQuery("select * from blocks",Block.class).getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Block> getBlockByUserId(int userId) {
        return this.entityManager.createNativeQuery("select * from blocks where user_id = :userId and last=1",Block.class).setParameter("userId",userId).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Block getBlockByHash(String hash) {
        return (Block) this.entityManager.createNativeQuery("select * from blocks where hash = :hash",Block.class).setParameter("hash",hash).getSingleResult();
    }


}