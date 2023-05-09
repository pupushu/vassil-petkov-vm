package com.vassil.petkov.vendingmachine.services;

import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.products.UpdateProductModel;
import com.vassil.petkov.vendingmachine.models.products.VendingMachineProductModel;

import java.util.List;

public interface ProductsService {
    boolean addProduct(VendingMachineProductModel product);
    boolean updateProduct(UpdateProductModel updateProduct) throws ProductNotFoundException;
    boolean removeProduct(String productId);
    VendingMachineProductModel extractSingleProductFromData(String productId) throws ProductNotFoundException;
    void lowerProductQuantity(VendingMachineProductModel result) throws ProductNotFoundException;
    List<VendingMachineProductModel> getAllProducts();
}
