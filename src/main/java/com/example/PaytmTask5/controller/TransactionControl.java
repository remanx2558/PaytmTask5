package com.example.PaytmTask5.controller;
import java.util.List;

import com.example.PaytmTask5.exception.ResourceNotFoundException;
import com.example.PaytmTask5.model.Transaction;
import com.example.PaytmTask5.model.Wallet;
import com.example.PaytmTask5.service.TransactionService;
import com.example.PaytmTask5.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.PaytmTask5.utilities.UtilityMethods.getCurrentTime;
@Transactional
@RestController
public class TransactionControl {
    @Autowired                         //dependency injection
    TransactionService transService;
    @Autowired                         //dependency injection
    WalletService walletService;
    //	   //1:Display  all transactions
    @GetMapping(value = "/transaction/all")
    public List<Transaction> displayAll() {
        return transService.displayall();
    }


//	    //2. Display specific
	    @GetMapping(value = "/transaction/{transactionid}")
	    Transaction displayTransaction2(@PathVariable(value = "transactionid") Long transactionid){
	    	return transService.get(transactionid);
	    }


    //3:  transaction status
    @GetMapping(value = "/transaction/status/{transactionid}")
    public String displayTransaction(@PathVariable("transactionid") Long transactionid) {
        Transaction checkTransaction = transService.get(transactionid);
        if (checkTransaction==null)
        {
            return "No such transaction made";
        }
        else if(checkTransaction.isStatus()==false)
        {
            return "Transaction Status: failed";
        }

        else return "Transaction Status: Successful";
    }

    //4: transfer Money :Post
    @PostMapping(value = "/transaction/make")
    public Transaction addtransaction(@RequestBody Transaction transModel) {
        Wallet sender =walletService.findByMobileWallet(transModel.getSender()).get(0);
        Wallet receiver =walletService.findByMobileWallet(transModel.getReceiver()).get(0);

        transModel.setStatus(false);
        transModel.setTime(getCurrentTime());
        transModel.setMode("online");
        if(sender==null) {
            throw new ResourceNotFoundException("sender do not exist");
        }
        else if(receiver==null) {
            throw new ResourceNotFoundException("receiver do not exist");
        }
        else if(sender.getBalance() < transModel.getAmount()) {
            transService.save(transModel);
            throw new ResourceNotFoundException("Insufficient balance of sender");
            //return "Insufficient balance";
        }
        else if(sender.isHaswallet()==false){
            throw new ResourceNotFoundException("sender wallet not activated");

        }
        else if(receiver.isHaswallet()==false){
            throw new ResourceNotFoundException("receiver wallet not activated");


        }
        ////////////////////
        long preSender=sender.getBalance();
        long preReceiver=receiver.getBalance();
        preSender=preSender-transModel.getAmount();
        preReceiver=preReceiver+transModel.getAmount();
        sender.setBalance(preSender);
        receiver.setBalance(preReceiver);
        this.walletService.save(sender);
        this.walletService.save(receiver);
        transModel.setStatus(true);
        transService.save(transModel);
        ////////////////////////
        return transModel;
    }

}
