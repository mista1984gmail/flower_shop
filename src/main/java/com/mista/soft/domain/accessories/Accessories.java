package com.mista.soft.domain.accessories;

import java.io.Serializable;
import java.util.Objects;

public abstract class Accessories implements Comparable, Serializable {

    String nameAccessories;
    int numberAccessories;
    double priceAccessories;

    public Accessories(String nameAccessories, int numberAccessories, double priceAccessories) {
        this.nameAccessories = nameAccessories;
        this.numberAccessories = numberAccessories;
        this.priceAccessories = priceAccessories;
    }

    public String getNameAccessories() {
        return nameAccessories;
    }

    public void setNameAccessories(String nameAccessories) {
        this.nameAccessories = nameAccessories;
    }

    public int getNumberAccessories() {
        return numberAccessories;
    }

    public void setNumberAccessories(int numberAccessories) {
        this.numberAccessories = numberAccessories;
    }

    public double getPriceAccessories() {
        return priceAccessories;
    }

    public void setPriceAccessories(double priceAccessories) {
        this.priceAccessories = priceAccessories;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "nameAccessories='" + nameAccessories + '\'' +
                ", numberAccessories=" + numberAccessories +
                ", priceAccessories=" + priceAccessories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accessories that = (Accessories) o;
        return numberAccessories == that.numberAccessories && Double.compare(that.priceAccessories, priceAccessories) == 0 && Objects.equals(nameAccessories, that.nameAccessories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAccessories, numberAccessories, priceAccessories);
    }
}
