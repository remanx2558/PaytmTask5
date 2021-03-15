package com.example.PaytmTask5.utilities;
import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.User;
import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.repository.UserRepository;
import com.example.PaytmTask5.service.WalletService;

import java.util.ArrayList;
import java.util.List;
public class PutValidator {
    public static void canBeUpdatedUser(User newUser, User existingUser, UserRepository userRepository) {

        /// m e: nn tn nt tt
        if(newUser.getMobile()!=null && !userRepository.findByMobile(newUser.getMobile()).isEmpty()){
            throw new ResourceNotFoundException("cannot change to this mobile number");
        }
        if(newUser.getEmail()!=null && !userRepository.findByEmail(newUser.getEmail()).isEmpty()){
            throw new ResourceNotFoundException("cannot change to this email id ");

        }
//
//        return  newUser.getFirstName().equalsIgnoreCase(existingUser.getFirstName()) &&
//                newUser.getLastName().equalsIgnoreCase(existingUser.getLastName()) &&
//                newUser.getMobile() == existingUser.getMobile();
    }
    public static List<Wallet> canBalanceBeAdded(WalletService walletService, Long id,
                                                 Wallet balanceWallet) {
        // find list of wallet by userID
        List<Wallet> wallets = walletService.findByOwnerID(id);

        // if wallet list is empty, user doesn't exist
        if(wallets.isEmpty()) {
            throw new ResourceNotFoundException("User does not exist");

//            Constants.setWalletPutMessage("User does not exist");
//            return wallets;
        }

        // getting wallet object from list and then balance
        Wallet wallet = wallets.get(0);
        long balance = balanceWallet.getBalance();

        // adding balance = 0 is insignificant, less than 0 is not possible
        if(balance < 1) {
            wallets.remove(0);
            throw new ResourceNotFoundException("Cannot add balance <= 0");

//            Constants.setWalletPutMessage("Cannot add balance <= 0");
//            return wallets;
        }
        return wallets;
    }
}
