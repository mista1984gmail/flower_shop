package com.mista.soft.db;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.exeption.IdIsNotAllowedOnDbException;
import com.mista.soft.exeption.NotUniqueIdException;
import com.mista.soft.io.SerializationUtils;

import java.io.Serializable;
import java.util.*;

public final class DataBase implements Serializable,AutoCloseable {
    private Map<Integer, Bouquet> BOUQUETS;

    private static final int MAX_ALLOWED_ID = 100;
    private static DataBase instance = new DataBase();
    private int currentId = 0;

    private DataBase() {
        BOUQUETS = new HashMap<>();}

    public static DataBase getInstance(){
        return instance;
    }

    public void executeSaveOperation(Bouquet bouquet)
            throws NotUniqueIdException {
        bouquet.setId(currentId);
        if (BOUQUETS.containsKey(bouquet.getId())) {
            throw new NotUniqueIdException(bouquet);
        }
        BOUQUETS.put(currentId++, bouquet);
    }

    public Optional<Bouquet> executeGetOperation(int id) {
        if (id > MAX_ALLOWED_ID) {
            throw new IdIsNotAllowedOnDbException(id);
        }
        return Optional.ofNullable(BOUQUETS.get(id));
    }

    public void save() {
        SerializationUtils.serialize(this);
    }


    public void load() {
        Object deserialized = SerializationUtils.deserialize();
        if (deserialized instanceof DataBase) {
            instance = (DataBase) deserialized;
        }
    }
    public List<Bouquet> executeGetAllOperation() {
        return new ArrayList<>(BOUQUETS.values());
    }

    public void executeDeleteOperation(int id){
        BOUQUETS.remove(id);
        DataBase.getInstance().save();
    }
    public List<Flowers> getFlowersBouquet(int id){
       return BOUQUETS.get(id).getFlowersList();

   }
    public List<Accessories> getAccessoriesBouquet(int id){
        return BOUQUETS.get(id).getAccessoriesList();

    }

    @Override
    public void close() throws Exception {

    }
}
