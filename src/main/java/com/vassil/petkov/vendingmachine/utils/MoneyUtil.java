package com.vassil.petkov.vendingmachine.utils;

import com.vassil.petkov.vendingmachine.constants.CoinsAccepted;
import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;

import java.math.BigDecimal;

public class MoneyUtil {
    public static BigDecimal addMoney(BigDecimal oldAmount, CoinModel coin) throws InvalidCoinException {
        BigDecimal newAmmount = CoinsAccepted.getCoinValueByKey(coin);
        return oldAmount.add(newAmmount);
    }

    public static boolean validateCoin(CoinModel coin){
        try {
            return CoinsAccepted.checkCoinValidity(coin);
        }catch (InvalidCoinException e){
            return false;
        }
    }

}
