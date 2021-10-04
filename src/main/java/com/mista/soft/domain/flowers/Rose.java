package com.mista.soft.domain.flowers;

import java.io.Serializable;

public class Rose extends Flowers implements Serializable {

    public Rose(String nameFlower, String flowerColor, double steamLength, Freshness freshnessFlower, int number, double price) {
        super(nameFlower, flowerColor, steamLength, freshnessFlower, number, price);
    }

    @Override
    public String toString() {
        return System.lineSeparator() +"Rose: " +
                "name='" + nameFlower + '\'' +
                ", color='" + flowerColor + '\'' +
                ", steam length=" + steamLength +
                ", freshness=" + freshnessFlower +
                ", number=" + number +
                ", price=" + price;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
