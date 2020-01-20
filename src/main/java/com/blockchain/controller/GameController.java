package com.blockchain.controller;

import com.blockchain.MainChain;
import com.blockchain.entity.User;
import com.blockchain.exceptions.GameNotFoundException;
import com.blockchain.exceptions.UserNotFoundException;
import com.blockchain.game_main.Bet;
import com.blockchain.game_main.Game;
import com.blockchain.game_main.GameResults;
import com.blockchain.game_main.UserGameHolder;
import com.blockchain.service.UserManager;
import com.blockchain.transaction.Transaction;
import com.blockchain.transaction.TransactionOutput;
import com.blockchain.transaction.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private UserManager userManager;
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<UserGameHolder> userGameHolders = new ArrayList<>();
    private ArrayList<Bet> bets = new ArrayList<>();
    @PostMapping("/search")
    public ResponseEntity searchForGame(@RequestBody User user){

        if (validateUser(user))
            return ResponseEntity.notFound().build();
        UserGameHolder ed_user = new UserGameHolder();
        user.setBalance(100f);
        ed_user.setUser(userManager.getUser(user));
        ed_user.setWallet(new Wallet());
        if (games.isEmpty())
            games.add(new Game());
        for (Game g: games)
            if (g.isEmptySpace()) {
                if (!g.getUsers().contains(userManager.getUser(user))) {
                    g.addPlayer(ed_user);
                    userGameHolders.add(ed_user);
                    sendMoney(ed_user.getWallet(), g.wallet, user.getBalance());
                }
                return ResponseEntity.ok(g);
            }
            Game game = new Game();
            game.addPlayer(ed_user);
        games.add(game);
        userGameHolders.add(ed_user);
        sendMoney(ed_user.getWallet(),game.wallet,user.getBalance());
        return ResponseEntity.ok(games.get(games.size() - 1));
    }
    private boolean validateUser(User user){
        return !userManager.validateUser(user);
    }
    @PostMapping(value = "/getgame")
    public ResponseEntity getGame(@RequestBody Game game){
        for (Game g:games)
            if (g.getHash().equals(game.getHash()))
                return ResponseEntity.ok(g);
            return ResponseEntity.notFound().build();
    }
    @PostMapping(value="/makebet")
    public ResponseEntity makeBet(@RequestBody Bet bet){
        User u = new User();
        u.setFirstName(bet.getUserId());
        u.setPassword(bet.getPass());
        if (validateUser(u))
            return ResponseEntity.badRequest().body(UserNotFoundException.class);
        if (validateGame(bet.getGameHash()) == null)
            return ResponseEntity.badRequest().body(GameNotFoundException.class);
        User user = userManager.getUser(u);
        for (UserGameHolder userGameHolder:
             userGameHolders) {
            if (userGameHolder.getUser().getId().equals(user.getId())){
                sendMoneyBetweenPlayers(userGameHolder.getWallet(), Objects.requireNonNull(validateGame(bet.getGameHash())).wallet,bet.getBet());
                user.setBalance(userGameHolder.getWallet().getBalance() - bet.getBet());
                userGameHolder.setUser(user);
                bets.add(bet);
                break;
            }
        }
        return ResponseEntity.ok(validateGame(bet.getGameHash()));
    }
    @PostMapping(value="/roll")
    public ResponseEntity generateVictoryNumber(@RequestBody Bet game) {
        GameResults gameResults = new GameResults();
        gameResults.setGameRes(new Random().nextInt(36));
        gameResults.setGame(validateGame(game.getGameHash()));
        for (Bet b : bets) {
            if (b.getNumber() == gameResults.gameRes) {
                User u = new User();
                u.setFirstName(b.getUserId());
                u.setPassword(b.getPass());
                u = userManager.getUser(u);
                for (UserGameHolder userGameHolder :
                        userGameHolders)
                    if (userGameHolder.getUser().getId().equals(u.getId())) {
                        sendMoney(userGameHolder.getWallet(),
                                Objects.requireNonNull(validateGame(b.getGameHash())).wallet, 1.45f * b.getBet());
                        userGameHolder.getUser().setBalance(userGameHolder.getWallet().getBalance());
                    }


            }
        }
        return ResponseEntity.ok(gameResults);
    }
    private Game validateGame(String hash){
        for (Game g:games)
            if (g.getHash().equals(hash))
                return g;
        return null;
    }
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    private void sendMoney(Wallet wallet, Wallet coinbase,float value){
        Transaction genesisTransaction = new Transaction(coinbase.publicKey, wallet.publicKey, value, null);
        genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction
        genesisTransaction.transactionId = "0"; //manually set the transaction id
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
        MainChain.UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
    }
    private void sendMoneyBetweenPlayers(Wallet from,Wallet to,float value){
        from.sendFunds(to.publicKey,value);
    }
}
