package com.mista.soft.service;

import com.mista.soft.db.DataBase;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Chamomile;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.domain.flowers.Freshness;
import com.mista.soft.repository.BouquetRepository;
import com.mista.soft.util.BouquetFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
@Slf4j
public class BouquetServiceImplTest2 {

    private static BouquetServiceImpl underTest;
    private static BouquetRepository repoMock;

    @BeforeAll
    public static void init() {
        repoMock = mock(BouquetRepository.class);
        underTest = new BouquetServiceImpl(repoMock);
    }
    @AfterEach
    public void resetMock() {
        reset(repoMock);
    }
    @BeforeEach
    public void setUp() {
        log.info("Test for 'BouquetServiceImpl' are started.");
    }

    @AfterEach
    public void tearDown() {
        log.info("Test for 'BouquetServiceImpl' are finished.");
        log.info("*****************************************************************************");
    }

    @Test
    public void testSaveBouquet() throws Exception {
        //GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repoMock.saveBouquet(any(Bouquet.class))).thenReturn(true);

        //WHEN
        underTest.saveBouquet(bouquet);

        //THEN
        verify(repoMock).saveBouquet(bouquet);
    }

    @Test
    public void  testGetBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repoMock.getBouquet(eq(0))).thenReturn(Optional.of(bouquet));

        // WHEN
        Bouquet bouquetFromRepo = underTest.getBouquet(0);

        // THEN
        Assertions.assertEquals(bouquet, bouquetFromRepo);
    }
    @Test
    public void testGetBouquets() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        when(repoMock.getBouquets()).thenReturn(Arrays.asList(bouquet));

        // WHEN
       underTest.showInfo();
        Collection<Bouquet> bouquets = repoMock.getBouquets();

        // THEN
        Assertions.assertEquals(Arrays.asList(bouquet), bouquets);

    }
    @Test
    public void testDeleteBouquet() throws Exception {
        // GIVEN
        Bouquet bouquet = BouquetFixture.createBouquet();
        DataBase.getInstance().executeSaveOperation(bouquet);

        // WHEN
        underTest.deleteBouquet(0);
        List<Bouquet> bouquets;
        bouquets = repoMock.getBouquets();

        // THEN
        Assert.assertEquals(bouquets.size(),0);
    }


}
