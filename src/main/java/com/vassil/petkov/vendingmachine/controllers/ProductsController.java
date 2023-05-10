package com.vassil.petkov.vendingmachine.controllers;

import com.vassil.petkov.vendingmachine.exceptions.InsufficientFundsException;
import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.products.UpdateProductModel;
import com.vassil.petkov.vendingmachine.models.products.VendingMachineProductModel;
import com.vassil.petkov.vendingmachine.services.MoneyService;
import com.vassil.petkov.vendingmachine.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final MoneyService moneyService;

    @Autowired
    public ProductsController(ProductsService productsService, MoneyService moneyService) {
        this.productsService = productsService;
        this.moneyService = moneyService;
    }

    @GetMapping("/product/buy/{productId}")
    @ResponseBody
    public String buyProduct(@PathVariable String productId){
        try {
            return moneyService.buyProduct(productId);
        } catch (InsufficientFundsException | ProductNotFoundException e) {
            return  e.getMessage();
        }
    }

    @DeleteMapping("/product/remove/{productId}")
    @ResponseBody
    public ResponseEntity<String> removeProduct(@PathVariable String productId){
        boolean done = productsService.removeProduct(productId);
        if (done) {
            return new ResponseEntity<>("200", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product/update")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductModel updateProduct){
        try {
            boolean done = productsService.updateProduct(updateProduct);
            if (done){
                return new ResponseEntity<>("200", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("417", HttpStatus.EXPECTATION_FAILED);
            }
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product/add")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody VendingMachineProductModel product){
        boolean done = productsService.addProduct(product);
        if (done){
            return new ResponseEntity<>("200", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("417", HttpStatus.EXPECTATION_FAILED);

        }
    }
}
