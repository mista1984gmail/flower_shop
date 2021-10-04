package com.mista.soft.service;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.domain.flowers.Freshness;

import javax.validation.constraints.NotNull;

public interface BouquetService {
    void saveBouquet(@NotNull Bouquet bouquet) throws Exception;
    Bouquet getBouquet(int id) throws Exception;
    void showInfo() throws Exception;
    void deleteBouquet (int id) throws Exception;
    void showFlowersAndAccessoriesInfo(int id) throws Exception;
    Flowers addFlowerInBouquet() throws Exception;
    Accessories addAccessoriesInBouquet() throws Exception;
    void totalCalculation(int id) throws Exception;
    void sortingByFreshness(int id) throws Exception;
    public void chooseFlowerRangeOfStemLength(int id, double start, double end) throws Exception;
}
