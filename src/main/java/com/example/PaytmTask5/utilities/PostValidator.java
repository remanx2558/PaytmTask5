package com.example.PaytmTask5.utilities;
import com.example.PaytmTask5.model.User;

import com.example.PaytmTask5.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PostValidator {
//    private static Logger logger = Logger.getLogger("PostValidator");
//
//    public static boolean isEmailValidated(String emailID, UserRepository userRepository) {
//        return userRepository.findByEmailid(emailID).isEmpty();
//    }
//
//    public static boolean isMobileNumberValidated(long mobileNumber, UserRepository userRepository) {
//        if(mobileNumber == 0) return true;
//        return userRepository.findByMobilenumber(mobileNumber).isEmpty();
//    }
//    public static String postResponseMessage(User user, UserRepository userRepository) {
//
//         if(user.getFirstName() == null) return "Firstname cannot be empty";
//        else if(user.getLastName() == null) return "Lastname cannot be empty";
//        else if(!isEmailValidated(user.getEmail(), userRepository))
//            return "User with an identical email already exists";
//        else if(!isMobileNumberValidated(user.getMobile(), userRepository))
//            return "User with an identical mobile number already exists";
//        else return "";
//    }
}
