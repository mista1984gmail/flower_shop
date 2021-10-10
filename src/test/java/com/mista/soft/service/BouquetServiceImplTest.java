package com.mista.soft.service;

import com.mista.soft.db.DataBase;
import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.accessories.Basket;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.domain.flowers.Freshness;
import com.mista.soft.domain.flowers.Rose;
import com.mista.soft.exeption.IdIsNotAllowedOnDbException;
import com.mista.soft.repository.BouquetRepository;
import com.mista.soft.util.BouquetFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static sun.nio.cs.Surrogate.is;

@ExtendWith(MockitoExtension.class)
public class BouquetServiceImplTest {

    @InjectMocks
    private static BouquetServiceImpl service;

    @Mock
    private static BouquetRepository repository;

    @AfterEach
    public void clear() {
        DataBase.getInstance().clearDb();
    }


    @Test
    public void testSaveBouquet() throws Exception{
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repository.saveBouquet(any(Bouquet.class))).thenReturn(true);

        // WHEN
        service.saveBouquet(bouquet);

        // THEN
        verify(repository).saveBouquet(bouquet);
    }
    @Test
    public void testGetBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repository.getBouquet(0)).thenReturn(Optional.of(bouquet));

        // WHEN
        Bouquet bouquetFromRepo = service.getBouquet(0);

        // THEN
        assertThat(bouquetFromRepo).isEqualTo(bouquet);
        verify(repository).getBouquet(0);

    }
    @Test
    void testShowInfo() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        List<Bouquet>list=new ArrayList<>();
        list.add(bouquet);
        when(repository.getBouquets()).thenReturn(list);
        // WHEN
        List <Bouquet> listFromRepo = repository.getBouquets();
        // THEN
        Assert.assertEquals(list,listFromRepo);
    }
    @Test
    void testDeleteBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repository.saveBouquet(any(Bouquet.class))).thenReturn(true);
        // WHEN
        service.saveBouquet(bouquet);
        service.deleteBouquet(0);
        List <Bouquet> listFromRepo = repository.getBouquets();
        // THEN
        Assert.assertEquals(listFromRepo.size(),0);
    }

    @Test
    void testAddAccessoriesInBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        List<Accessories>accessoriesList=new ArrayList<>();
        Accessories accessories = new Basket("Basket",1,5.5);
        accessoriesList.add(accessories);
        bouquet.setAccessoriesList(accessoriesList);
        when(repository.getBouquet(0)).thenReturn(Optional.of(bouquet));
        // WHEN
        Bouquet bouquetFromRepo = service.getBouquet(0);
        List<Accessories>accessoriesFromRepo=bouquetFromRepo.getAccessoriesList();
        // THEN
        Assert.assertEquals(accessoriesFromRepo,accessoriesList);
    }
    @Test
    void testAddFlowerInBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        List<Flowers>flowersList=new ArrayList<>();
        Flowers flowers = new Rose("rose","blue",40, Freshness.HIGH_FRESHNESS,9,2.5);
        flowersList.add(flowers);
        bouquet.setFlowersList(flowersList);
        when(repository.getBouquet(0)).thenReturn(Optional.of(bouquet));
        // WHEN
        Bouquet bouquetFromRepo = service.getBouquet(0);
        List<Flowers>flowersFromRepo=bouquetFromRepo.getFlowersList();
        // THEN
        Assert.assertEquals(flowersFromRepo,flowersList);
    }



}
