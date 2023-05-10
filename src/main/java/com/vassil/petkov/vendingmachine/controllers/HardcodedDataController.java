package com.vassil.petkov.vendingmachine.controllers;

import com.vassil.petkov.vendingmachine.exceptions.InsufficientFundsException;
import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;
import com.vassil.petkov.vendingmachine.models.products.UpdateProductModel;
import com.vassil.petkov.vendingmachine.models.products.VendingMachineProductModel;
import com.vassil.petkov.vendingmachine.services.MoneyService;
import com.vassil.petkov.vendingmachine.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Stack;

@Controller
public class HardcodedDataController {

    private final ProductsService productsService;
    private final MoneyService moneyService;

    @Autowired
    public HardcodedDataController(ProductsService productsService, MoneyService moneyService) {
        this.productsService = productsService;
        this.moneyService = moneyService;
    }

    @GetMapping("/demo/insert-coins")
    @ResponseBody
    public String insertCoins() {
        CoinModel c1 = new CoinModel(10, "st");
        CoinModel c2 = new CoinModel(20, "st");
        CoinModel c3 = new CoinModel(50, "st");
        CoinModel c4 = new CoinModel(1, "lv");
        CoinModel c5 = new CoinModel(2, "lv");
        CoinModel c6 = new CoinModel(30, "st");
        try {
            moneyService.insertCoin(c1);
            moneyService.insertCoin(c2);
            moneyService.insertCoin(c3);
            moneyService.insertCoin(c4);
            moneyService.insertCoin(c5);
            moneyService.insertCoin(c6);
            return "done";
        } catch (InvalidCoinException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/demo/reset-coins")
    @ResponseBody
    public Stack<CoinModel> ressetCoins(){
        return moneyService.reset();
    }

    @GetMapping("/demo/product-add")
    @ResponseBody
    public List<VendingMachineProductModel> addProduct(){
        VendingMachineProductModel p1 = new VendingMachineProductModel("1", "Coca Cola", 5, 2.30);
        VendingMachineProductModel p2 = new VendingMachineProductModel("2", "Fanta", 5, 2.30);
        VendingMachineProductModel p3 = new VendingMachineProductModel("3", "Sprite", 5, 2.30);
        VendingMachineProductModel p4 = new VendingMachineProductModel("4", "Soda Water", 5, 2.30);
        VendingMachineProductModel p5 = new VendingMachineProductModel("5", "Water", 5, 2.30);
        VendingMachineProductModel p6 = new VendingMachineProductModel("6", "Pepsi", 5, 2.30);
        VendingMachineProductModel p7 = new VendingMachineProductModel("7", "Mirinda", 5, 2.30);
        VendingMachineProductModel p8 = new VendingMachineProductModel("8", "7 up", 5, 2.30);
        VendingMachineProductModel p9 = new VendingMachineProductModel("9", "Coffee", 5, 2.30);
        VendingMachineProductModel p10 = new VendingMachineProductModel("10", "Orange juice", 5, 2.30);
        VendingMachineProductModel p11 = new VendingMachineProductModel("11", "Invalid product", 5, 2.30);
        productsService.addProduct(p1);
        productsService.addProduct(p2);
        productsService.addProduct(p3);
        productsService.addProduct(p4);
        productsService.addProduct(p5);
        productsService.addProduct(p6);
        productsService.addProduct(p7);
        productsService.addProduct(p8);
        productsService.addProduct(p9);
        productsService.addProduct(p10);
        productsService.addProduct(p11);
        return productsService.getAllProducts();
    }

    @GetMapping("/demo/product-remove")
    @ResponseBody
    public List<VendingMachineProductModel> removeProduct(){
        productsService.removeProduct("7");
        return productsService.getAllProducts();

    }

    @GetMapping("/demo/product-update")
    @ResponseBody
    public List<VendingMachineProductModel> updateProduct() throws ProductNotFoundException {
        UpdateProductModel m1 = new UpdateProductModel("2", new VendingMachineProductModel("2", "Fanta", 15, 7.38));
        productsService.updateProduct(m1);
        return productsService.getAllProducts();
    }

    @GetMapping("/demo/product-buy")
    @ResponseBody
    public String buyProduct() throws InsufficientFundsException, ProductNotFoundException {
        return moneyService.buyProduct("1");
    }
}
