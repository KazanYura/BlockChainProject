package com.blockchain.controller;

import com.blockchain.entity.Block;
import com.blockchain.hash.Hasher;
import com.blockchain.service.BlockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/blocks")
public class BlockController {
    @Autowired
    private BlockManager blockManager;

    @GetMapping(value = "/")
    public ResponseEntity listBlocks() {
        return ResponseEntity.ok(blockManager.getAllBlocks());
    }

    @PostMapping(value = "/add")
    public ResponseEntity addBlock(@RequestBody Block block) {
        blockManager.addBlock(block);
        return ResponseEntity.ok(block);
    }

    public void setBlockManager(BlockManager blockManager) {
        this.blockManager = blockManager;
    }
}