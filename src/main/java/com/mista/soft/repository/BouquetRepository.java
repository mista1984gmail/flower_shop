package com.mista.soft.repository;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BouquetRepository {
    boolean saveBouquet(@NotNull Bouquet bouquet) throws Exception;
    Optional<Bouquet> getBouquet(int id) throws Exception;
    List<Bouquet> getBouquets() throws Exception;
    void deleteBouquet(int id) throws Exception;
    List<Flowers> flowerslist (int id) throws Exception;
    List<Accessories> accessorieslist (int id) throws Exception;
}
