package com.vassil.petkov.vendingmachine.exceptions;

public class ProductNotFoundException extends Exception{
    private String message;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
