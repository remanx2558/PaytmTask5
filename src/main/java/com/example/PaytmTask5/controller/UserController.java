package com.example.PaytmTask5.controller;
import java.util.ArrayList;

import javax.validation.Valid;

import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.User;
import com.example.PaytmTask5.repository.UserRepository;
import com.example.PaytmTask5.utilities.PostValidator;
import com.example.PaytmTask5.utilities.PutValidator;
import com.example.PaytmTask5.utilities.UtilityMethods;
import com.example.PaytmTask5.exception.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping
    public ArrayList<User> getAllusers(){

        logger.log(Level.INFO, "list of all users returned at "+ UtilityMethods.get_current_time());
        return (ArrayList<User>) this.userRepository.findAll();

    }

    @GetMapping("/{uid}")
    public User getUserById(@PathVariable (value = "uid") long userId ) {

        return this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user not Found with id : "+userId));
    }


    @PostMapping
    public User createUser(@Valid @RequestBody User user) {// we will be requiring request body to get data to fill
        PostValidator.postResponseMessageUser(user, userRepository);//checking conditions
        logger.log(Level.INFO, user.toString());
        return this.userRepository.save(user);
    }

    @PutMapping("/{uid}")
    public User updateUser(@RequestBody User user, @PathVariable("uid") long userId) {

        User existingUser = this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user not Fondd with id : "+userId));

        PutValidator.canBeUpdatedUser(user,existingUser,userRepository);//condition checking
//        if(!PutValidator.canBeUpdatedUser(user, existingUser)) {
//            logger.log(Level.INFO, "Only email and address can be updated");
//            throw new ResourceNotFoundException("Only email and address can be updated");
//        }
        if(user.getFirstName()!=null){existingUser.setFirstName(user.getFirstName());}
        if(user.getLastName()!=null){existingUser.setLastName(user.getLastName());}
        if(user.getAddress1()!=null){ existingUser.setAddress1(user.getAddress1());}
        if(user.getAddress2()!=null){existingUser.setAddress2(user.getAddress2());}
        if(user.getMobile()!=null ){existingUser.setMobile(user.getMobile());}
        if(user.getEmail()!=null ){existingUser.setEmail(user.getEmail());}
        if(user.getGender()!=null ){existingUser.setGender(user.getGender());}
        ///balance is not updated here
        ///same number or email in data base cannot be changed
        ///pk cannot be changed

        logger.log(Level.INFO, user.toString());

        return this.userRepository.save(existingUser);//this will directly save into database
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<User> deleteUser(@PathVariable("uid") long userId){

        User existingUser = this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user not Fondd with id : "+userId));
        this.userRepository.delete(existingUser);
        logger.log(Level.INFO, existingUser.toString() + "deleted");
        return ResponseEntity.ok().build();


    }
    @DeleteMapping(value = "/admin/alluser")
    public ResponseEntity<?> deleteAll() {
        logger.log(Level.INFO, "all users deleted at "+UtilityMethods.get_current_time());
        ResponseBody responseBody = new ResponseBody("all users deleted", "OK");
        userRepository.deleteAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}