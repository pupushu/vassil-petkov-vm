package com.vassil.petkov.vendingmachine.services;

import com.vassil.petkov.vendingmachine.exceptions.InsufficientFundsException;
import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;

import java.util.Stack;

public interface MoneyService {
    boolean insertCoin(CoinModel coin) throws InvalidCoinException;
    Stack<CoinModel> reset();
    String buyProduct(String productId) throws InsufficientFundsException, ProductNotFoundException;
}
