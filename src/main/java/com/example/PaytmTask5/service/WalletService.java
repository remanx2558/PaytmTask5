package com.example.PaytmTask5.service;

import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    // Using all pre-created methods of WalletRepository
    public List<Wallet> listAll() { return walletRepository.findAll(); }

    public Wallet get(Long id) { return walletRepository.findById(id).get(); }

    public void save(Wallet wallet) { walletRepository.save(wallet); }

    public void delete(Long id) { walletRepository.deleteById(id); }

    public List<Wallet> findByOwnerID(long owner) { return walletRepository.findByOwner(owner); }
}