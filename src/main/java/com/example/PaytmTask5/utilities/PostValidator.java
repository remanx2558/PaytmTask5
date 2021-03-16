package com.example.PaytmTask5.utilities;

import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.User;

import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.repository.UserRepository;
import com.example.PaytmTask5.service.UserService;
import com.example.PaytmTask5.service.WalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostValidator {
    private static Logger logger = Logger.getLogger("PostValidator");

    public static boolean isEmailValidated(String emailID, UserRepository userRepository) {
        return userRepository.findByEmail(emailID).isEmpty();
    }

    public static boolean isMobileNumberValidated(long mobileNumber, UserRepository userRepository) {
        if (mobileNumber == 0) return true;
        return userRepository.findByMobile(mobileNumber).isEmpty();
    }

    public static void postResponseMessageUser(User user, UserRepository userRepository) {
        Logger logger = Logger.getLogger(user.getClass().getName());


        if (user.getFirstName() == null) {
            logger.log(Level.INFO, "undesired input");
            throw new ResourceNotFoundException("Firstname cannot be empty");
        } else if (user.getLastName() == null) {
            logger.log(Level.INFO, "undesired input");
            throw new ResourceNotFoundException("Lastname cannot be empty");
        } else if (!isEmailValidated(user.getEmail(), userRepository)) {
            logger.log(Level.INFO, "undesired input");
            throw new ResourceNotFoundException("User with an identical email already exists");

        } else if (!isMobileNumberValidated(user.getMobile(), userRepository)) {
            logger.log(Level.INFO, "undesired input");
            throw new ResourceNotFoundException("User with an identical mobile number already exists");
        }
        // else return "";//returning no error
    }

    public static void walletPostValidate(Wallet walletBody, UserService userService,WalletService walletService) {
      //information cannot be null byDefault as using table such a way
        // if mobile number we get from request body is 0


        // getting a list of user with the specified mobile number
        List<User> listUsers= userService.findbyMobile(walletBody.getMobileWallet());
        if(listUsers.isEmpty()){
            //throw no such user exist
            throw new ResourceNotFoundException("No user with this mobile exist");

        }
        List<Wallet> listWallet= walletService.findByMobileWallet(walletBody.getMobileWallet());
        if(!listWallet.isEmpty()){
            //throw Wallet already exist
            throw new ResourceNotFoundException("Wallet already exist with this mobile");

        }

        return;
    }


}
