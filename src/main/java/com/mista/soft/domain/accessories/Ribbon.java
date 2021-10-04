package com.mista.soft.domain.accessories;

import java.io.Serializable;

public class Ribbon extends Accessories implements Serializable {
    public Ribbon(String nameAccessories, int numberAccessories, double priceAccessories) {
        super(nameAccessories, numberAccessories, priceAccessories);
    }

    @Override
    public String toString() {
        return  System.lineSeparator() +"Ribbon: "+
                "name='" + nameAccessories + '\'' +
                ", number=" + numberAccessories +
                ", price=" + priceAccessories;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
