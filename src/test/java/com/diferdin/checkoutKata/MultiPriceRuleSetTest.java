package com.diferdin.checkoutKata;

import com.diferdin.checkoutKata.rules.MultiPriceRuleSet;
import com.diferdin.checkoutKata.rules.SpecialPrice;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by antonio on 12/03/2017.
 */
public class MultiPriceRuleSetTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsIfRuleNotFound() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("No pricing rule found for 'A'"));

        MultiPriceRuleSet sut = new MultiPriceRuleSet(new HashMap<>());
        sut.calculateTotalPrice(asList("A"));
    }

    @Test
    public void canHandleUnitPrice() {
        Map<String, SpecialPrice> priceRules = new HashMap<>();
        priceRules.put("A", new SpecialPrice(10));

        MultiPriceRuleSet sut = new MultiPriceRuleSet(priceRules);

        assertThat(sut.calculateTotalPrice(asList("A")), is(10));
        assertThat(sut.calculateTotalPrice(asList("A", "A")), is(20));
        assertThat(sut.calculateTotalPrice(asList("A", "A", "A")), is(30));
    }

    @Test
    public void canHandleSpecialPrice() {
        Map<String, SpecialPrice> priceRules = new HashMap<>();
        priceRules.put("A", new SpecialPrice(10, 3, 20));

        MultiPriceRuleSet sut = new MultiPriceRuleSet(priceRules);

        assertThat(sut.calculateTotalPrice(asList("A")), is(10));
        assertThat(sut.calculateTotalPrice(asList("A", "A")), is(20));
        assertThat(sut.calculateTotalPrice(asList("A", "A", "A")), is(20));
        assertThat(sut.calculateTotalPrice(asList("A", "A", "A", "A")), is(30));
    }

    @Test
    public void canHandleZeroPrice() {
        Map<String, SpecialPrice> priceRules = new HashMap<>();
        priceRules.put("A", new SpecialPrice(0));

        MultiPriceRuleSet sut = new MultiPriceRuleSet(priceRules);

        assertThat(sut.calculateTotalPrice(asList("A")), is(0));
        assertThat(sut.calculateTotalPrice(asList("A", "A")), is(0));
    }

}
