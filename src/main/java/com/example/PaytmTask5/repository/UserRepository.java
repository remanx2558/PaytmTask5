package com.example.PaytmTask5.repository;

import com.example.PaytmTask5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}