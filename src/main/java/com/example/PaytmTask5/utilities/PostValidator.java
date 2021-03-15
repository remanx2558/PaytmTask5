package com.example.PaytmTask5.utilities;

import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.User;

import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.repository.UserRepository;
import com.example.PaytmTask5.service.UserService;
import com.example.PaytmTask5.service.WalletService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static List<User> walletPostValidate(long mobileNumber, UserService userService) {
        // if mobile number we get from request body is 0
        List<User> walletUser = new ArrayList<>();
        if (mobileNumber == 0) {
            throw new ResourceNotFoundException("Mobile number field is empty");

//            Constants.setWalletPostMessage("Mobile number field is empty");
//            return walletUser;
        }

        // getting a list of user with the specified mobile number
        walletUser = userService.findbyMobile(mobileNumber);

        // if list is empty then the user with phone number doesn't exist
        if (walletUser.isEmpty()) {
            throw new ResourceNotFoundException("User with phone number " + mobileNumber + " does not exist");

//            Constants.setWalletPostMessage("User with phone number "+ mobileNumber +" does not exist");
//            return walletUser;
        }
        // if list has a user but he is not already registered for a wallet
        else if (walletUser.size() > 0 && walletUser.get(0).getHaswallet()) {
            walletUser.remove(0);
            throw new ResourceNotFoundException("User already has a wallet registered");

//            Constants.setWalletPostMessage("User already has a wallet registered");
//            return walletUser;
        }
        return walletUser;
    }

    public static void createSuccessfulWalletAccount(List<User> walletUser, UserService userService,
                                                     WalletService walletService) {
        // getting and setting user object from userList
        User user = walletUser.get(0);
        user.setHaswallet(true);

        // creating new wallet object for the user
        Wallet wallet = new Wallet();
        wallet.setBalance(0);
        wallet.setCreation(UtilityMethods.get_current_time());
        wallet.setOwner((int) user.getUid());
        userService.save(user);
        logger.log(Level.INFO, wallet.toString());
        walletService.save(wallet);
        //Constants.setWalletPostMessage("Wallet created");
    }
}
