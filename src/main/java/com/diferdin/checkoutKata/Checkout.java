package com.diferdin.checkoutKata;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio on 12/03/2017.
 */
public class Checkout {

    private final PriceRuleSet priceRuleSet;
    private List<String> skus;

    public Checkout(PriceRuleSet priceRuleSet) {
        this.priceRuleSet = priceRuleSet;
        skus = new LinkedList<>();
    }

    public void scan(String s) {
        skus.add(s);
    }

    public int getTotal() {
        return priceRuleSet.calculateTotalPrice(skus);
    }
}
