package com.diferdin.checkoutKata;

import com.diferdin.checkoutKata.model.Item;
import com.diferdin.checkoutKata.rules.PriceRule;
import com.diferdin.checkoutKata.Checkout.Transaction;
import org.junit.Test;

import java.util.*;

import static com.diferdin.checkoutKata.rules.MultiPriceRule.multiPriceRuleFor;
import static com.diferdin.checkoutKata.rules.SinglePriceRule.singlePriceRuleFor;
import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 12/03/2017.
 */
public class CheckoutTest {

    Checkout checkout = new Checkout();

    private final static Item A = new Item("A");
    private final static Item B = new Item("B");
    private final static Item C = new Item("C");
    private final static Item D = new Item("D");


    private Set<PriceRule> providedPriceRuleSet() {
        Set<PriceRule> priceRules = new LinkedHashSet<>();
        priceRules.add(multiPriceRuleFor(A).buy(3).forPrice(130));
        priceRules.add(multiPriceRuleFor(B).buy(2).forPrice(45));
        priceRules.add(singlePriceRuleFor(A).atPrice(50));
        priceRules.add(singlePriceRuleFor(B).atPrice(30));
        priceRules.add(singlePriceRuleFor(C).atPrice(20));
        priceRules.add(singlePriceRuleFor(D).atPrice(15));

        return priceRules;
    }

    @Test
    public void shouldReturnZeroForEmptyTransaction() {
        Transaction transaction = checkout
                .startTransaction(providedPriceRuleSet())
                .add(new Item(""));

        assertEquals(0, checkout.getTotal(transaction));
    }

    @Test
    public void shouldCalculateTotalForIndividualItems() {
        Transaction transaction = checkout
                .startTransaction(providedPriceRuleSet())
                .add(A)
                .add(B)
                .add(C)
                .add(D);

        assertEquals(115, checkout.getTotal(transaction));
    }

    @Test
    public void shouldIncludeMultiPriceInTotal() {
        Transaction transaction = checkout
                .startTransaction(providedPriceRuleSet())
                .add(A)
                .add(A)
                .add(A)
                .add(B)
                .add(B);

        assertEquals(175, checkout.getTotal(transaction));
    }

    @Test
    public void shouldCalculateTotalWithMixOfSingleAndMultiPriceItems() {
        Transaction transaction = checkout
                .startTransaction(providedPriceRuleSet())
                .add(A)
                .add(C)
                .add(C)
                .add(A)
                .add(A)
                .add(D)
                .add(B)
                .add(C)
                .add(B)
                .add(D)
                .add(A)
                .add(B);

        assertEquals(345, checkout.getTotal(transaction));
    }
}
