package com.vassil.petkov.vendingmachine.constants;

import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;

import java.math.BigDecimal;

public enum CoinsAccepted {
    TEN_ST("10st", new BigDecimal("0.10")),
    TWENTY_ST("20st", new BigDecimal("0.20")),
    FIFTY_ST("50st", new BigDecimal("0.50")),
    ONE_LEV("1lv", new BigDecimal("1.00")),
    TWO_LEVA("2lv", new BigDecimal("2.00"));

    private final String key;
    private final BigDecimal value;

    CoinsAccepted(String key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }


    public static boolean checkCoinValidity(CoinModel coin) throws InvalidCoinException {
        for (CoinsAccepted coinsAccepted : values()) {
            if (coinsAccepted.key.equals(coin.toString()))
                return true;
        }
        throw new InvalidCoinException(Constants.INVALID_COIN_EXCEPTION_MESSAGE);
    }

    public static BigDecimal getCoinValueByKey(CoinModel coin) throws InvalidCoinException {
        for (CoinsAccepted coinsAccepted : values()) {
            if (coinsAccepted.key.equals(coin.toString()))
                return coinsAccepted.value;
        }
        throw new InvalidCoinException(Constants.INVALID_COIN_EXCEPTION_MESSAGE);
    }

}
