package com.example.PaytmTask5.service;

import com.example.PaytmTask5.model.User;
import com.example.PaytmTask5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Using all pre-created methods of UserRepository
    public List<User> listAll() { return userRepository.findAll(); }

    public void save(User user) { userRepository.save(user); }

    public User get(Long id) { return userRepository.findById(id).get(); }

    public void delete(Long id) { userRepository.deleteById(id); }

    public void deleteAll() { userRepository.deleteAll(); }

    public List<User> findByEmail(String emailid) { return userRepository.findByEmail(emailid); }

    public List<User> findbyMobile(long mobilenumber) {
        return userRepository.findByMobile(mobilenumber);
    }

    public List<User> findbyGender(String gender) { return userRepository.findByGender(gender);  }

    public List<User> findbyHaswallet(boolean haswallet) { return userRepository.findByHaswallet(haswallet);  }



}