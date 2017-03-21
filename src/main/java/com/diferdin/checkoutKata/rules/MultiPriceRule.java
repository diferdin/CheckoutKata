package com.diferdin.checkoutKata.rules;

import com.diferdin.checkoutKata.model.Item;


/**
 * Created by antonio on 12/03/2017.
 */
public final class MultiPriceRule implements PriceRule {

    public static class MultiPriceRuleBuilder {

        private final Item item;
        private int quantity;

        public MultiPriceRuleBuilder(final Item item) {
            this.item = item;
        }

        public MultiPriceRuleBuilder buy(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public MultiPriceRule forPrice(final long price) {
            return new MultiPriceRule(item, price, quantity);
        }

    }

    public static MultiPriceRuleBuilder multiPriceRuleFor(final Item item) {
        return new MultiPriceRuleBuilder(item);
    }

    private final Item item;
    private final long price;
    private final int quantity;

    private MultiPriceRule(final Item item, final long price, final int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public SpecialPrice apply(final long amount) {
        final long applies = amount / quantity;
        final long totalPrice = price * applies;
        return new SpecialPrice(totalPrice, applies * quantity);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object object) {

        if(object == null) {
            return false;
        }

        if(this == object) {
            return true;
        }

        if(getClass() != object.getClass()) {
            return false;
        }

        MultiPriceRule obj = (MultiPriceRule) object;
        if(item == null) {
            if( obj.getItem() != null) {
                return false;
            }
        } else if (!item.equals(obj.getItem())) {
            return false;
        }
        return price == obj.price && quantity == obj.quantity;
    }

    @Override
    public String toString() {
        return "item " + item + " buys " + quantity + " for " + price;
    }


}
