package com.mista.soft.domain.accessories;

import java.io.Serializable;

public class Basket extends Accessories implements Serializable {

    public Basket(String nameAccessories, int numberAccessories, double priceAccessories) {
        super(nameAccessories, numberAccessories, priceAccessories);
    }

    @Override
    public String toString() {
        return System.lineSeparator() +"Basket: "+
                "name='" + nameAccessories + '\'' +
                ", number=" + numberAccessories +
                ", price=" + priceAccessories;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
