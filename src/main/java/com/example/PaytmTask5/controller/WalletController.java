package com.example.PaytmTask5.controller;

import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.User;
import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.repository.WalletRepository;
import com.example.PaytmTask5.service.UserService;
import com.example.PaytmTask5.service.WalletService;
import com.example.PaytmTask5.utilities.PostValidator;
import com.example.PaytmTask5.utilities.PutValidator;
import com.example.PaytmTask5.utilities.UtilityMethods;
import com.example.PaytmTask5.exception.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

// wallet controller class for accepting and managing HTTP Requests
@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // basically calling CRUD methods of the service class and specifying the response to return
    @GetMapping("/wallet")
    public List<Wallet> list() {
        logger.log(Level.INFO, "list of all wallets returned at "+ UtilityMethods.get_current_time());
        return walletService.listAll();
    }

    //get by wallet Id
    @GetMapping(value = "/wallet", params = "walletId")
    public ResponseEntity<?> get(@RequestParam("walletId") Long id) {
        try {
            Wallet wallet = walletService.get(id);
            ResponseEntity<Wallet> r = new ResponseEntity<>(wallet, OK);
            logger.log(Level.INFO, wallet.toString());
            return r;
        }
        catch (NoSuchElementException e) {
            ResponseBody responseBody = new ResponseBody("Cannot read nonexistent wallet", "Not found");
            logger.log(Level.INFO, responseBody.toString());
            return new ResponseEntity<>(responseBody, NOT_FOUND);
        }
    }

    ///get walllet by mobile number
    @GetMapping("/mobile/{mobileN}")
    public Wallet getWalletByMobile(@PathVariable(value = "mobileN") long number) {
        List<Wallet> currWallet=this.walletService.findByMobileWallet(number);
        if(!currWallet.isEmpty()){
            return currWallet.get(0);
        }
        else{
            throw new ResourceNotFoundException("Wallet not Found with mobile : " + number);
        }
    }

    // using User object as a request body where it will have only one field, i.e, mobile number and type
    @PostMapping("/merchant/register")
    public Wallet createMerchanWallet(@Valid @RequestBody Wallet walletBody) {// we will be requiring request body to get data to fill
        PostValidator.walletPostValidate(walletBody, userService,walletService);
        walletBody.setCustomer(false);
        walletBody.setHaswallet(true);
        walletService.save(walletBody);
        return walletBody;
    }
    @PostMapping("/customer/register")
    public Wallet createCustomereWallet(@Valid @RequestBody Wallet walletBody) {// we will be requiring request body to get data to fill
        PostValidator.walletPostValidate(walletBody, userService,walletService);
        walletBody.setCustomer(true);
        walletBody.setHaswallet(true);
        walletService.save(walletBody);
        return walletBody;
    }

//PUT
    //activate or deactivate wallet
    //change mobile
    //change balance
    @PutMapping(value = "/wallet", params = "userId")
    public ResponseEntity<ResponseBody> addBalanceByUserID(@RequestParam("userId") Long id,
                                                           @RequestBody Wallet balance) {
        List<Wallet> wallets = PutValidator.canBalanceBeAdded(walletService, id, balance);
        ResponseBody responseBody;
        if(wallets.isEmpty()) {
            responseBody = new ResponseBody("balance is empty in wallet", "OK");
            logger.log(Level.INFO, responseBody.toString());
            return new ResponseEntity<>(responseBody, OK);
        }
        Wallet wallet = wallets.get(0);

        // setting wallet balance and then saving it
        wallet.setBalance(wallet.getBalance() + balance.getBalance());
        responseBody = new ResponseBody(
                "Balance of "+balance.getBalance()+" added to wallet with id = "+wallet.getId(),
                "OK");
        logger.log(Level.INFO, wallet.toString());
        walletService.save(wallet);
        return new ResponseEntity<>(responseBody, OK);
    }

    @DeleteMapping(value = "/wallet", params = "walletId")
    public ResponseEntity<ResponseBody> deleteWalletByID(@RequestParam("walletId") Long id) {
        ResponseBody responseBody;
        try {
            Wallet existingWallet = walletService.get(id);
            responseBody = new ResponseBody("Deleted wallet successfully with id = "+id, "OK");
            logger.log(Level.INFO, existingWallet.toString());
            walletService.delete(id);
            return new ResponseEntity<>(responseBody, OK);
        }
        catch (NoSuchElementException e) {
            responseBody = new ResponseBody("Cannot delete nonexistent wallet", "Not found");
            logger.log(Level.INFO, responseBody.toString());
            return new ResponseEntity<>(responseBody, NOT_FOUND);
        }
    }
}