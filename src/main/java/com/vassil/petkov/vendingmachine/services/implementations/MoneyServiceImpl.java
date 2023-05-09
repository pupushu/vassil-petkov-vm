package com.vassil.petkov.vendingmachine.services.implementations;

import com.vassil.petkov.vendingmachine.constants.Constants;
import com.vassil.petkov.vendingmachine.exceptions.InsufficientFundsException;
import com.vassil.petkov.vendingmachine.exceptions.InvalidCoinException;
import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.coins.CoinModel;
import com.vassil.petkov.vendingmachine.models.products.VendingMachineProductModel;
import com.vassil.petkov.vendingmachine.services.MoneyService;
import com.vassil.petkov.vendingmachine.services.ProductsService;
import com.vassil.petkov.vendingmachine.utils.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

@Service
public class MoneyServiceImpl implements MoneyService {
    public static Stack<CoinModel> insertedCoins = new Stack<>();
    public static BigDecimal totalAmountInserted = new BigDecimal(0);

    private final ProductsService productsService;

    @Autowired
    public MoneyServiceImpl(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public boolean insertCoin(CoinModel coin) throws InvalidCoinException {
        boolean coinIsValid = MoneyUtil.validateCoin(coin);
        if (coinIsValid) {
            insertedCoins.push(coin);
            totalAmountInserted = MoneyUtil.addMoney(totalAmountInserted, coin);
            return true;
        }
        return false;
    }

    @Override
    public Stack<CoinModel> reset() {
        Stack<CoinModel> result =(Stack<CoinModel>) insertedCoins.clone();
        insertedCoins.removeAllElements();
        totalAmountInserted = new BigDecimal(0);
        return result;
    }



    @Override
    public String buyProduct(String productId) throws ProductNotFoundException, InsufficientFundsException {
            VendingMachineProductModel result = productsService.extractSingleProductFromData(productId);
            boolean areCoinsEnough = checkIfMoneyProvidedAreEnough(result.getPrice());
            if (result.getQuantity() < 1){
                throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
            }
            if (areCoinsEnough){
                productsService.lowerProductQuantity(result);
                totalAmountInserted = new BigDecimal(0);
                insertedCoins.removeAllElements();
                return result.getName();
            }else {
                throw new InsufficientFundsException(Constants.INSUFFICIENT_FUND_EXCEPTION_MESSAGE);
            }
        }

    private boolean checkIfMoneyProvidedAreEnough(double price) {
        int result = totalAmountInserted.compareTo(BigDecimal.valueOf(price));
        return result >= 0;
    }

}
