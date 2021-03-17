package com.example.PaytmTask5.repository;

import com.example.PaytmTask5.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// by extending JpaRepository this interface contains all pre-created methods for logic of HTTP requests
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // these methods are custom finder methods, i.e for custom queries for mysql
    // returning a page instead of a list
    public List<Transaction> findBySender(long sender);
    public List<Transaction> findByReceiver(long receiver);

}