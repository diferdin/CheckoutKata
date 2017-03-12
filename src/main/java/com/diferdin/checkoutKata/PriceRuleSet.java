package com.diferdin.checkoutKata;

import java.util.Collection;

/**
 * Created by antonio on 12/03/2017.
 */
public interface PriceRuleSet {

    int calculateTotalPrice(Collection<String> skus);

}
