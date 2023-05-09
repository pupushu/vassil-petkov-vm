package com.vassil.petkov.vendingmachine.models.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendingMachineProductModel {
    private String id;
    private String name;
    private int quantity;
    private double price;
}
