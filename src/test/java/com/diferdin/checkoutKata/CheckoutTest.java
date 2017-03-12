package com.diferdin.checkoutKata;

import com.diferdin.checkoutKata.rules.MultiPriceRuleSet;
import com.diferdin.checkoutKata.rules.PriceRuleSet;
import com.diferdin.checkoutKata.rules.SpecialPrice;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 12/03/2017.
 */
public class CheckoutTest {

    private int calculatePrice(String items) {

        Checkout checkout = new Checkout(getProvidedPriceRuleSet());

        for(int i = 0; i < items.length(); i++) {
            checkout.scan(String.valueOf(items.charAt(i)));
        }

        return checkout.getTotal();
    }

    private PriceRuleSet getProvidedPriceRuleSet() {
        Map<String, SpecialPrice> priceRules = new HashMap<>();
        priceRules.put("A", new SpecialPrice(50, 3, 130));
        priceRules.put("B", new SpecialPrice(30, 2, 45));
        priceRules.put("C", new SpecialPrice(20));
        priceRules.put("D", new SpecialPrice(15));
        return new MultiPriceRuleSet(priceRules);
    }

    @Test
    public void shouldCalculateTotal() {
        assertEquals(0, calculatePrice(""));
        assertEquals(50, calculatePrice("A"));
        assertEquals(30, calculatePrice("B"));
        assertEquals(20, calculatePrice("C"));
        assertEquals(15, calculatePrice("D"));
        assertEquals(40, calculatePrice("CC"));
        assertEquals(30, calculatePrice("DD"));
        assertEquals(35, calculatePrice("CD"));
        assertEquals(80, calculatePrice("AB"));
        assertEquals(35, calculatePrice("CD"));
        assertEquals(130, calculatePrice("AAB"));
        assertEquals(60, calculatePrice("CCC"));
        assertEquals(45, calculatePrice("DDD"));
        assertEquals(115, calculatePrice("ABCD"));
    }

    @Test
    public void shouldIncludeMultiPriceInTotal() {
        assertEquals(130, calculatePrice("AAA"));
        assertEquals(45, calculatePrice("BB"));
        assertEquals(175, calculatePrice("ABABA"));
        assertEquals(305, calculatePrice("AABAABAA"));
        assertEquals(375, calculatePrice("ACABADABACAD"));
    }

    @Test
    public void shouldCalculateTotalIncrementally() {
        Checkout checkout = new Checkout(getProvidedPriceRuleSet());

        checkout.scan("A");
        assertEquals(50, checkout.getTotal());

        checkout.scan("B");
        assertEquals(80, checkout.getTotal());

        checkout.scan("B");
        assertEquals(95, checkout.getTotal());

        checkout.scan("A");
        assertEquals(145, checkout.getTotal());

        checkout.scan("A");
        assertEquals(175, checkout.getTotal());

    }

}
