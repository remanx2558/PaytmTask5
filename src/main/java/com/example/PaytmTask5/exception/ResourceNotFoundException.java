package com.example.PaytmTask5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String message){
        super(message);
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.log(Level.INFO, message.toString());

    }


}