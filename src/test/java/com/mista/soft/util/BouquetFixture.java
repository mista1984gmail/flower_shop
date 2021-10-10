package com.mista.soft.util;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;

import java.util.List;

public class BouquetFixture {
    private static final String NAME = "Bouquet #1";
    private static List<Flowers> flowersList;
    private static List<Accessories> accessoriesList;


    public static Bouquet createBouquet(){
        Bouquet bouquet = new Bouquet();
        bouquet.setNameBouquet(NAME);
        bouquet.setFlowersList(flowersList);
        bouquet.setAccessoriesList(accessoriesList);
        return bouquet;
    }

}
