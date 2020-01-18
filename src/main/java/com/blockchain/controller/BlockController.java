package com.blockchain.controller;

import com.blockchain.entity.Block;
import com.blockchain.hash.Hasher;
import com.blockchain.service.BlockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/blocks")
public class BlockController {
    @Autowired
    private BlockManager blockManager;


    @PostMapping(value = "/add")
    public ResponseEntity addBlock(@RequestBody Block block) {
        blockManager.addBlock(block);
        return ResponseEntity.ok(block);
    }
    @GetMapping(value="/hash")
    public ResponseEntity getBlock(@RequestBody Block hash){
        return ResponseEntity.ok(blockManager.getOneBlock(hash.getHash()));
    }
    @PutMapping(value = "/edit")
    public ResponseEntity editBlock(@RequestBody Block block){
        blockManager.editBlock(block);
        return ResponseEntity.ok(block);
    }
    @GetMapping(value="/userlastblock")
    public ResponseEntity getLastUserBlock(@RequestBody Block block){
        return ResponseEntity.ok(blockManager.getUserLastBlock(block.getUserId()));
    }
    public void setBlockManager(BlockManager blockManager) {
        this.blockManager = blockManager;
    }
}