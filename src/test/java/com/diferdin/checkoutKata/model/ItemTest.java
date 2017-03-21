package com.diferdin.checkoutKata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * Created by antonio on 20/03/2017.
 */
public class ItemTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Item.class).verify();
    }
}
