package com.diferdin.checkoutKata.rules;

/**
 * Created by antonio on 12/03/2017.
 */
public class SpecialPrice {

    private final long price;
    private final long remainder;

    public SpecialPrice(final long price, final long remainder) {
        this.price = price;
        this.remainder = remainder;
    }

    public long getPrice() {
        return price;
    }

    public long getRemainder() {
        return remainder;
    }

    @Override
    public String toString() {
        return "Special price [price=" + price + "p, remainder=" + remainder + "]";
    }
}
