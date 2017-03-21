package com.diferdin.checkoutKata.rules;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * Created by antonio on 20/03/2017.
 */
public class SinglePriceRuleTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SinglePriceRule.class).verify();
    }
}
