package com.diferdin.checkoutKata.rules;

import com.diferdin.checkoutKata.model.Item;

/**
 * Created by antonio on 20/03/2017.
 */
public final class SinglePriceRule implements PriceRule {

    public static class SinglePriceRuleBuilder {

        private final Item item;

        public SinglePriceRuleBuilder(final Item item) {
            this.item = item;
        }

        public SinglePriceRule atPrice(long price) {
            return new SinglePriceRule(item, price);
        }
    }

    public static SinglePriceRuleBuilder singlePriceRuleFor(Item item) {
        return new SinglePriceRuleBuilder(item);
    }

    private final Item item;
    private final long price;

    private SinglePriceRule(final Item item, final long price) {
        super();
        this.item = item;
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public SpecialPrice apply(final long amount) {
        return new SpecialPrice(amount * price, 0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
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

        SinglePriceRule obj = (SinglePriceRule) object;
        if(item == null) {
            if(obj.getItem() != null) {
                return false;
            }
        } else if (!item.equals(obj.getItem())) {
            return false;
        }
        return price == obj.price;
    }
}
