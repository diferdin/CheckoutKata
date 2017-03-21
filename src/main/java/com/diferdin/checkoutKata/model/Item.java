package com.diferdin.checkoutKata.model;


/**
 * Created by antonio on 20/03/2017.
 */
public final class Item {

    private final String sku;

    public Item(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
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

        Item obj = (Item)object;
        if(sku == null) {
            if(obj.getSku() != null) {
                return false;
            }
        } else if (!sku.equals(obj.getSku())) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        return prime * result + ((sku == null) ? 0 : sku.hashCode());


    }
}
