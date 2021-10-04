package com.mista.soft.repository;

import com.mista.soft.db.DataBase;
import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.exeption.NotUniqueIdException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BouquetRepositotyImpl  implements  BouquetRepository{
    @Override
    public boolean saveBouquet(Bouquet bouquet) throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            dataBase.executeSaveOperation(bouquet);
            return true;
        } catch (NotUniqueIdException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Bouquet> getBouquet(int id) throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            return dataBase.executeGetOperation(id);
        }
    }

    @Override
    public List<Bouquet> getBouquets() throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            return dataBase.executeGetAllOperation();
        }
    }

    @Override
    public void deleteBouquet(int id) throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            dataBase.executeDeleteOperation(id);
        } catch (NotUniqueIdException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Flowers> flowerslist(int id) throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            return dataBase.getFlowersBouquet(id);
        }
    }

    @Override
    public List<Accessories> accessorieslist(int id) throws Exception {
        try (DataBase dataBase = DataBase.getInstance()) {
            return dataBase.getAccessoriesBouquet(id);
        }
    }
}
