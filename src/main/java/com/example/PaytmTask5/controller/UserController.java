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

@RequestMapping("api/users")
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

        return this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user not Fondd with id : "+userId));
    }


    @PostMapping
    public User createUser(@Valid @RequestBody User user) {// we will be requiring request body to get data to fill
       // String msg = PostValidator.postResponseMessage(user, userRepository);
//        if(msg != "") {
//
//            logger.log(Level.INFO, "undesired input");
//            throw new ResourceNotFoundException("undesired input");
//        }
        ArrayList<User> allUsers=getAllusers();
        for(int i=0;i<allUsers.size();i++) {
            User curr=allUsers.get(i);

            if((curr.getFirstName().compareTo(user.getFirstName())==0) && (curr.getLastName().compareTo(user.getLastName())==0)) {
                throw new ResourceNotFoundException("This User Name already Exist");
            }
            else if((curr.getEmail().compareTo(user.getEmail())==0)) {
                throw new ResourceNotFoundException("This Mail already Exist");
            }
            else if ((curr.getMobile().compareTo(user.getMobile())==0)){
                throw new ResourceNotFoundException("This Mobile alreeady Exist");
            }

        }

        logger.log(Level.INFO, user.toString());
        return this.userRepository.save(user);

    }

    @PutMapping("/{uid}")
    public User updateUser(@RequestBody User user, @PathVariable("uid") long userId) {

        User existingUser = this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user not Fondd with id : "+userId));

//        if(!PutValidator.canBeUpdated(user, existingUser)) {
//            logger.log(Level.INFO, "Only email and address can be updated");
//            throw new ResourceNotFoundException("Only email and address can be updated");
//        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress1(user.getAddress1());
        existingUser.setAddress2(user.getAddress2());
        existingUser.setMobile(user.getMobile());
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
    @DeleteMapping(value = "/user")
    public ResponseEntity<?> deleteAll() {
        logger.log(Level.INFO, "all users deleted at "+UtilityMethods.get_current_time());
        ResponseBody responseBody = new ResponseBody("all users deleted", "OK");
        userRepository.deleteAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}