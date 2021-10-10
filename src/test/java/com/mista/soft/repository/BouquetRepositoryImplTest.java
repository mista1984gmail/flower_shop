package com.mista.soft.repository;

import com.mista.soft.db.DataBase;
import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.accessories.Basket;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Chamomile;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.domain.flowers.Freshness;
import com.mista.soft.exeption.NotUniqueIdException;
import com.mista.soft.service.BouquetServiceImpl;
import com.mista.soft.util.BouquetFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BouquetRepositoryImplTest {

    private static BouquetRepository repository=new BouquetRepositotyImpl();

    //@AfterEach
   // public void resetMock() {
    //   reset(repository);
   // }
    @AfterEach
    public void clear() {
        DataBase.getInstance().clearDb();
    }



    @Test
    public void testSaveBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();

        // WHEN
        boolean isBouquetSaved = repository.saveBouquet(bouquet);

        // THEN
        assertThat(isBouquetSaved).isTrue();
        Bouquet bouquetFromDB = DataBase.getInstance().executeGetOperation(0)
                .orElseThrow(RuntimeException::new);
        Bouquet bouquetForAssert = BouquetFixture.createBouquet();
        assertThat(bouquetForAssert).usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(bouquetFromDB);
    }
    @Test
    public void  testGetBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        DataBase.getInstance().executeSaveOperation(bouquet);

        // WHEN
        Optional<Bouquet> bouquetFromRepo = repository.getBouquet(0);

        // THEN
        assertThat(bouquetFromRepo).contains(bouquet);

    }

    @Test
    public void testGetBouquets() throws Exception {
        // GIVEN
        int expectedSize = 4;
        for (int i = 0; i < expectedSize; i++) {
            Bouquet bouquet = BouquetFixture.createBouquet();
            DataBase.getInstance().executeSaveOperation(bouquet);
        }

        // WHEN
        List<Bouquet> bouquets;
        bouquets = repository.getBouquets();

        // THEN
        assertThat(bouquets).hasSize(expectedSize).doesNotContainNull();

    }
    @Test
    public void testDeleteBouquet() throws Exception {
        // GIVEN

        Bouquet bouquet = BouquetFixture.createBouquet();
        DataBase.getInstance().executeSaveOperation(bouquet);
        // WHEN
        DataBase.getInstance().executeDeleteOperation(0);
        List<Bouquet> bouquets;
        bouquets = repository.getBouquets();

        // THEN
        Assert.assertEquals(0,bouquets.size());

    }
    @Test
    public void testFlowerslist() throws Exception {
        // GIVEN

        Bouquet bouquet = BouquetFixture.createBouquet();
        List<Flowers> flowersList=new ArrayList<>();
        Flowers flower = new Chamomile("new_flower","red",35, Freshness.LOW_FRESHNESS,9,1);
        flowersList.add(flower);
        bouquet.setFlowersList(flowersList);
        DataBase.getInstance().executeSaveOperation(bouquet);
        // WHEN
        List<Flowers> listFlowersFromRepo=repository.flowerslist(bouquet.getId());

        // THEN
        Assert.assertEquals(listFlowersFromRepo,flowersList);

    }
    @Test
    public void testAccessorieslist() throws Exception {
        // GIVEN

        Bouquet bouquet = BouquetFixture.createBouquet();
        List<Accessories> accessoriesList=new ArrayList<>();
        Accessories accessories = new Basket("Basket",1,5.5);
        accessoriesList.add(accessories);
        bouquet.setAccessoriesList(accessoriesList);
        DataBase.getInstance().executeSaveOperation(bouquet);
        // WHEN
        List<Accessories> listAccessoriesFromRepo=repository.accessorieslist(bouquet.getId());

        // THEN
        Assert.assertEquals(listAccessoriesFromRepo,accessoriesList);

    }


}
