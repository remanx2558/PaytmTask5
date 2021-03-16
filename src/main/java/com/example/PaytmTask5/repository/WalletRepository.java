package com.example.PaytmTask5.repository;


import com.example.PaytmTask5.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// by extending JpaRepository this interface contains all pre-created methods for logic of HTTP requests
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // these methods are custom finder methods, i.e for custom queries for mysql
    public List<Wallet> findByMobileWallet(long mobileWallet);
    public List<Wallet> findByHaswallet(boolean haswallet);
    public List<Wallet> findByIsCustomer(boolean isCustomer);


}