package com.aarteaga.redisproject.repository;

import com.aarteaga.redisproject.model.Wallet;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, String> {
    Wallet findByCellphoneNumber(String number);
}
