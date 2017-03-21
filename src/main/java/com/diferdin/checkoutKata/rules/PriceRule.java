package com.diferdin.checkoutKata.rules;

import com.diferdin.checkoutKata.model.Item;

import java.util.Collection;

/**
 * Created by antonio on 12/03/2017.
 */
public interface PriceRule {

    Item getItem();
    SpecialPrice apply(long amount);

}
