package com.vassil.petkov.vendingmachine.exceptions;

public class InvalidCoinException extends Exception{
    private String message;

    public InvalidCoinException(String message) {
        super(message);
    }
}
