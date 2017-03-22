package com.diferdin.checkoutKata;

import com.diferdin.checkoutKata.model.Item;
import com.diferdin.checkoutKata.rules.PriceRule;
import com.diferdin.checkoutKata.rules.SpecialPrice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by antonio on 12/03/2017.
 */
public class Checkout {

     class Transaction {

        private final Set<PriceRule> priceRuleSet;
        private Map<Item, Long> shoppingBasket = new HashMap<>();

        Transaction(Set<PriceRule> priceRuleSet) {
            this.priceRuleSet = priceRuleSet;
        }

        Transaction add(Item item) {
            Long amount = shoppingBasket.get(item);

            if (amount != null) {
                amount++;
            } else {
                amount = 1L;
            }

            shoppingBasket.put(item, amount);
            return this;
        }

        Map<Item, Long> getShoppingBasket() {
            return this.shoppingBasket;
        }

        Set<PriceRule> getPriceRuleSet() {
            return this.priceRuleSet;
        }
    }

    int getTotal(Transaction transaction) {
        int totalPrice = 0;

        Set<PriceRule> priceRules = transaction.getPriceRuleSet();
        Map<Item, Long> shoppingBasket = transaction.getShoppingBasket();

        for (PriceRule rule : priceRules) {
            if (shoppingBasket.containsKey(rule.getItem())) {
                long amount = shoppingBasket.get(rule.getItem());
                SpecialPrice specialPrice = rule.apply(amount);
                totalPrice += specialPrice.getPrice();
                shoppingBasket.put(rule.getItem(), amount - specialPrice.getRemainder());
            }
        }

        return totalPrice;

    }


    Transaction startTransaction(final Set<PriceRule> priceRules) {
        return new Transaction(priceRules);
    }
}
