package com.vassil.petkov.vendingmachine.exceptions;

public class InsufficientFundsException extends Exception{

    private String message;

    public InsufficientFundsException(String message) {
        super(message);
    }
}
