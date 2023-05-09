package com.vassil.petkov.vendingmachine.models.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductModel {
    private String oldProductId;
    private VendingMachineProductModel newProduct;
}
