package com.mista.soft.domain.accessories;

import java.io.Serializable;

public class WrappingPaper extends Accessories implements Serializable {

    public WrappingPaper(String nameAccessories, int numberAccessories, double priceAccessories) {
        super(nameAccessories, numberAccessories, priceAccessories);
    }

    @Override
    public String toString() {
        return System.lineSeparator() +"Wrapping paper: "+
                "name='" + nameAccessories + '\'' +
                ", number=" + numberAccessories +
                ", price=" + priceAccessories;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
