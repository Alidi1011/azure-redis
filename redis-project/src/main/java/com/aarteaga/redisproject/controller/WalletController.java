package com.aarteaga.redisproject.controller;

import com.aarteaga.redisproject.model.Wallet;
import com.aarteaga.redisproject.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getWallets() {
        return walletService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Wallet customerWallet) {
        try {
            return new ResponseEntity<>(walletService.save(customerWallet), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Wallet read(@PathVariable String id) {
        return walletService.read(id);
    }

    @PutMapping
    public Wallet update(@RequestBody Wallet customerWallet) {
        return walletService.update(customerWallet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        walletService.delete(id);
    }

    @GetMapping("/phone/{phone}")
    public Wallet readByPhone(@PathVariable String phone) {
        System.out.println("phone received by readByPhone: " + phone);
        return walletService.findByCellphoneNumber(phone);
    }
}
