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

    @Override
    public void addBlock(Block block) {
        this.entityManager.persist(block);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Block> getAllBlocks() {
        return this.entityManager.createNativeQuery("select * from blocks").getResultList();
    }

}