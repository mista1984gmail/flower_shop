package com.mista.soft.service;

import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.accessories.Basket;
import com.mista.soft.domain.accessories.Ribbon;
import com.mista.soft.domain.accessories.WrappingPaper;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.*;
import com.mista.soft.exeption.IdIsNotAllowedOnDbException;
import com.mista.soft.repository.BouquetRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class BouquetServiceImpl implements BouquetService{
    private BouquetRepository repository;
    public static final Consumer<Bouquet> LOG_ACTION = bouquet ->
            log.info(bouquet.getNameBouquet() + ", id: " + bouquet.getId());
    public BouquetServiceImpl(BouquetRepository repository) {
        this.repository = repository;
    }
    @Override
    public void saveBouquet(Bouquet bouquet) throws Exception {
        log.info("Trying to save bouquet: {}", bouquet);
        boolean isBouquetSaved = repository.saveBouquet(bouquet);
        String success = isBouquetSaved ? "" : "not ";
        log.info("Bouquet was {}saved: {}", success, bouquet);
    }

    @Override
    public Bouquet getBouquet(int id) throws Exception {
        log.info("Trying to get bouquet with id = '{}'", id);
        Optional<Bouquet> bouquet = repository.getBouquet(id);
        if (bouquet.isPresent()) {
            log.info("{} is gotten", bouquet.get());
            return bouquet.get();
        } else {
            log.info("Creating new bouquet because no bouquet with id");
            return Bouquet.builder().id(id).build();
        }
    }

    @Override
    public void showInfo() throws Exception {
        log.info("Showing info about bouquet");
        try{
            repository.getBouquets().forEach(LOG_ACTION);}
        catch (IdIsNotAllowedOnDbException e){
            e.getMessage();
        }
    }

    @Override
    public void deleteBouquet(int id) throws Exception {
        log.info("Trying to delete bouquet with id= '{}'", id);
        log.info("Bouquet with id= '{}' delete", id);
        repository.deleteBouquet(id);
    }

    @Override
    public void showFlowersAndAccessoriesInfo(int id) throws Exception {
        log.info("Showing flowers and accessories in bouquet");
        log.info(String.valueOf(repository.flowerslist(id)));
        log.info(String.valueOf(repository.accessorieslist(id)));
        totalCalculation(id);
    }

    @Override
    public Accessories addAccessoriesInBouquet() throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("Enter type of accessories: 1 - RIBBON; 2 - WRAPPING PAPER; 3 - BASKET;");
        int index = scanner.nextInt();

        log.info("Enter name of accessories:");
        String nameAccessories = scanner.next();

        log.info("Enter number of accessories:");
        int nunberOfAccessories = scanner.nextInt();

        log.info("Enter price of accessories:");
        double priseOfAccessories = scanner.nextDouble();

        switch (index){
            case 1:
                Accessories accessories1 = new Ribbon(nameAccessories,nunberOfAccessories,priseOfAccessories);
                log.info("Accessories added");
                return accessories1;
            case 2:
                Accessories accessories2 = new WrappingPaper(nameAccessories,nunberOfAccessories,priseOfAccessories);
                log.info("Accessories added");
                return accessories2;
            case 3:
                Accessories accessories3 = new Basket(nameAccessories,nunberOfAccessories,priseOfAccessories);
                log.info("Accessories added");
                return accessories3;
            default:
                log.info("There is no such option, please choose another option.");
        }

        return null;
    }

    @Override
    public void totalCalculation(int id) throws Exception {
        double totalCost=0;
        List<Flowers> flowersList=repository.flowerslist(id);
        for (Flowers flowers:flowersList) {
            totalCost = totalCost + flowers.getPrice()*flowers.getNumber();
        }
        List<Accessories> accessoriesList=repository.accessorieslist(id);
        for (Accessories accessories:accessoriesList) {
            totalCost = totalCost + accessories.getPriceAccessories()*accessories.getNumberAccessories();
        }

        System.out.println(("TOTAL COST: " + totalCost + " $"));

    }

    @Override
    public void sortingByFreshness(int id) throws Exception {
        log.info("Showing flowers");
        List<Flowers> flowersList=repository.flowerslist(id);
        Collections.sort(flowersList, new Comparator<Flowers>() {
            @Override
            public int compare(Flowers o1, Flowers o2) {
                if (o1.getFreshnessFlower().ordinal() > o2.getFreshnessFlower().ordinal()){
                    return 1;
                } else if (o1.getFreshnessFlower().ordinal() < o2.getFreshnessFlower().ordinal()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (Flowers flowers:flowersList) {
            System.out.print(flowers);
        }
        System.out.println();
    }

    @Override
    public void chooseFlowerRangeOfStemLength(int id, double start, double end) throws Exception {
        log.info("Showing flowers");
        List<Flowers> flowersList=repository.flowerslist(id);
        List<Flowers> listChoose = flowersList.stream()
                .filter(s ->(s.getSteamLength()  >= start && s.getSteamLength() <= end))
                .collect(Collectors.toList());
        for (Flowers flowers:listChoose) {
            System.out.print(flowers);
        }
        System.out.println();
    }

    @Override
    public Flowers addFlowerInBouquet() throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("Enter flower: ROSE; TULIP; CHRYSANTHEMUM: CHAMOMILE; FORGET-ME-NOT");
        String type = scanner.nextLine();

        log.info("Enter name of flower:");
        String nameFlower = scanner.nextLine();

        log.info("Enter color of flower:");
        String colorFlower = scanner.nextLine();

        log.info("Enter stem length of flower:");
        double stemLength = scanner.nextDouble();

        log.info("Enter freshness of flower: 1 - HIGH; 2 - AVERAGE; 3 - LOW;");
        int j = scanner.nextInt();
        Freshness freshness = null;
        switch (j){
            case 1:
                freshness = Freshness.HIGH_FRESHNESS;
                break;
            case 2:
                freshness = Freshness.AVERAGE_FRESHNESS;
                break;
            case 3:
                freshness = Freshness.LOW_FRESHNESS;
                break;
            default:
                log.info("There is no such option, please choose another option.");
        }

        log.info("Enter number of flower:");
        int nunberOfFlower = scanner.nextInt();

        log.info("Enter price of flower:");
        double priseOfFlower = scanner.nextDouble();

        switch (type){
            case "ROSE":
                Rose rose = new Rose(nameFlower,colorFlower,stemLength,freshness,nunberOfFlower,priseOfFlower);
                log.info("Flowers added");
                return rose;
            case "TULIP":
                Tulip tulip = new Tulip(nameFlower,colorFlower,stemLength,freshness,nunberOfFlower,priseOfFlower);
                log.info("Flowers added");
                return tulip;
            case "CHRYSANTHEMUM":
                Chrysanthemum chrysanthemum = new Chrysanthemum(nameFlower,colorFlower,stemLength,freshness,nunberOfFlower,priseOfFlower);
                log.info("Flowers added");
                return chrysanthemum;
            case "CHAMOMILE":
                Chamomile chamomile = new Chamomile(nameFlower,colorFlower,stemLength,freshness,nunberOfFlower,priseOfFlower);
                log.info("Flowers added");
                return chamomile;
            case "FORGET-ME-NOT":
                ForgetMeNot forgetMeNot = new ForgetMeNot(nameFlower,colorFlower,stemLength,freshness,nunberOfFlower,priseOfFlower);
                log.info("Flowers added");
                return forgetMeNot;
            default:
                log.info("There is no such option, please choose another option.");
        }

        return null;
    }


}
