package com.diferdin.checkoutKata.rules;

import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by antonio on 12/03/2017.
 */
public class MultiPriceRuleSet implements PriceRuleSet {

    private Map<String, SpecialPrice> pricingRules;

    public MultiPriceRuleSet(Map<String, SpecialPrice> pricingRules) {

        this.pricingRules = pricingRules;

    }

    @Override
    public int calculateTotalPrice(Collection<String> skus) {
        return skus.stream()
                .collect(groupingBy(x -> x, counting()))
                .entrySet().stream()
                .map(x -> calculatePriceFor(x.getKey(), x.getValue()))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculatePriceFor(String name, long quantity) {
        if(!pricingRules.containsKey(name)) {
            throw new IllegalArgumentException(String.format("No pricing rule found for %s", name));
        }

        SpecialPrice specialPrice = pricingRules.get(name);

        int specialBatchSize = specialPrice.getBatchSize();
        int specialBatchPrice = specialPrice.getBatchPrice();
        int unitPrice = specialPrice.getUnitPrice();

        int offerUnits = (int)(quantity/specialBatchSize);
        int remainingItems = (int)(quantity % specialBatchSize);

        return offerUnits * specialBatchPrice + remainingItems * unitPrice;

    }


}
