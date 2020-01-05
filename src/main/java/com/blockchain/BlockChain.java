package com.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.blockchain.repository")
@ComponentScan(basePackages = "com.blockchain")
public class BlockChain {
    public static void main(String[] args) {
        SpringApplication.run(BlockChain.class);
    }
}
