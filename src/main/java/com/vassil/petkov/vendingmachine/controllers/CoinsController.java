package com.vassil.petkov.vendingmachine.controllers;

import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;
import com.vassil.petkov.vendingmachine.services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Stack;

@Controller
public class CoinsController {

    private final MoneyService moneyService;

    @Autowired
    public CoinsController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }



    @PostMapping("/coin/insert")
    @ResponseBody
    public ResponseEntity<String> insertCoin(@RequestBody CoinModel coin) throws InvalidCoinException {
        boolean done = moneyService.insertCoin(coin);
            if (done) {
                return new ResponseEntity<>("200", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("417", HttpStatus.EXPECTATION_FAILED);
            }
    }

    @GetMapping("/coin/reset")
    @ResponseBody
    public Stack<CoinModel> reset() {
        return moneyService.reset();
    }

}
