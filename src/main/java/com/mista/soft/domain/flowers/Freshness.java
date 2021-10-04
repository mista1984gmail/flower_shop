package com.mista.soft.domain.flowers;

import java.io.Serializable;

public enum Freshness implements Serializable {
    HIGH_FRESHNESS(1,"high"),
    AVERAGE_FRESHNESS(2,"average"),
    LOW_FRESHNESS(3,"low");

    /*HIGH_FRESHNESS - высокая свежесть,
    AVERAGE_FRESHNESS - средняя свежесть,
    LOW_FRESHNESS - низкая свежесть*/

    private int id;
    private String name;




    Freshness(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
