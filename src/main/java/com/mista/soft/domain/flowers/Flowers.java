package com.mista.soft.domain.flowers;

import java.io.Serializable;
import java.util.Objects;

public abstract class Flowers implements Comparable, Serializable {
    String nameFlower;
    String flowerColor;
    double steamLength;
    Freshness freshnessFlower;
    int number;
    double price;

    public Flowers(String nameFlower, String flowerColor, double steamLength, Freshness freshnessFlower, int number, double price) {
        this.nameFlower = nameFlower;
        this.flowerColor = flowerColor;
        this.steamLength = steamLength;
        this.freshnessFlower = freshnessFlower;
        this.number = number;
        this.price = price;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public double getSteamLength() {
        return steamLength;
    }

    public void setSteamLength(double steamLength) {
        this.steamLength = steamLength;
    }

    public Freshness getFreshnessFlower() {
        return freshnessFlower;
    }

    public void setFreshnessFlower(Freshness freshnessFlower) {
        this.freshnessFlower = freshnessFlower;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flowers{" +
                "nameFlower='" + nameFlower + '\'' +
                ", flowerColor='" + flowerColor + '\'' +
                ", steamLength=" + steamLength +
                ", freshnessFlower=" + freshnessFlower +
                ", number=" + number +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flowers flowers = (Flowers) o;
        return Double.compare(flowers.steamLength, steamLength) == 0 && number == flowers.number && Double.compare(flowers.price, price) == 0 && Objects.equals(nameFlower, flowers.nameFlower) && Objects.equals(flowerColor, flowers.flowerColor) && freshnessFlower == flowers.freshnessFlower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameFlower, flowerColor, steamLength, freshnessFlower, number, price);
    }


}
