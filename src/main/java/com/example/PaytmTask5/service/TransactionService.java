package com.example.PaytmTask5.service;

import com.example.PaytmTask5.model.Transaction;
import com.example.PaytmTask5.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    // Using all pre-created methods of TransactionRepository
    public void save(Transaction transaction) { transactionRepository.save(transaction); }
    //3:Dispaly Single
    public Transaction get(Long id) { return transactionRepository.findById(id).get(); }
    public List<Transaction> findBySender(long sender){ return transactionRepository.findBySender(sender); }
    public List<Transaction> findByReceiver(long receiver){ return transactionRepository.findBySender(receiver); }
    //1:saving transactions in the database

    public Transaction saveSingle(Transaction transModel) {
        return transactionRepository.save(transModel);
    }

    //2: Display all
    public List<Transaction> displayall() {
        return transactionRepository.findAll();
    }

    //saving transactions in the database
    public Transaction addtransaction(Transaction transModel) {
        return transactionRepository.save(transModel);
    }

}