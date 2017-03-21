package com.diferdin.checkoutKata.rules;

import com.diferdin.checkoutKata.model.Item;
import com.diferdin.checkoutKata.rules.MultiPriceRule;
import com.diferdin.checkoutKata.rules.SpecialPrice;
import nl.jqno.equalsverifier.EqualsVerifier;
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
public class MultiPriceRuleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MultiPriceRule.class).verify();
    }

}
