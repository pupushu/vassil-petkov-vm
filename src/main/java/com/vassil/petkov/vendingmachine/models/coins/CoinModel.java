package com.vassil.petkov.vendingmachine.models.coins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoinModel {
    private int value;
    private String nominal;

    @Override
    public String toString() {
        return value + nominal;
    }
}
