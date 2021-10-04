package com.mista.soft.app;

import com.mista.soft.db.DataBase;
import com.mista.soft.domain.accessories.Accessories;
import com.mista.soft.domain.bouquet.Bouquet;
import com.mista.soft.domain.flowers.Flowers;
import com.mista.soft.repository.BouquetRepositotyImpl;
import com.mista.soft.service.BouquetServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class App 
{

    public static final BouquetRepositotyImpl BOUQUET_REPOSITORY = new BouquetRepositotyImpl();
    public static final BouquetServiceImpl BOUQUET_SERVICE = new BouquetServiceImpl(BOUQUET_REPOSITORY);

    public static void main( String[] args ) throws Exception {
        DataBase.getInstance().load();
        int userInput = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            // instructions
            log.info("Enter 0 to exit the application");//exiting the application
            log.info("Enter 1 to show all bouquets");//show all bouquets
            log.info("Enter 2 to delete bouquet");//removing a bouquet
            log.info("Enter 3 to show flowers and accessories in the bouquet");//show flowers and accessories in a bouquet
            log.info("Enter 4 to add bouquet");//add bouquet
            log.info("Enter 5 to add the flowers in bouquet");//add flowers to the bouquet
            log.info("Enter 6 to add the accessories in bouquet");//add accessories to the bouquet
            log.info("Enter 7 to sort flowers by freshness");//sort flowers by freshness
            log.info("Enter 8 to find flowers in a given range of stem length");//choose flowers by stem length

            System.out.println("_______________________________________________________________________");
            // reading input
            userInput = scanner.nextInt();

            // choosing option
            switch (userInput) {
                case 0:
                    log.info("Goodbye!");
                    break;
                case 1:
                    BOUQUET_SERVICE.showInfo();
                    break;
                case 2:
                    deleteBouquet();
                    break;
                case 3:
                    showFlowersAndAccessoriesInBouquet();
                    break;
                case 4:
                    addBouquet();
                    break;
                case 5:
                    addFlowersInBouquet();
                    break;
                case 6:
                    addAccessoriesInBouquet();
                    break;
                case 7:
                    sortFlowersByFreshness();
                    break;
                case 8:
                    chooseFlowersRangeByStemLength();;
                    break;
                default:
                    log.info("There is no such option, please choose another option.");
            }

        } while (userInput != 0);

        DataBase.getInstance().save();
    }
    private static void chooseFlowersRangeByStemLength() throws Exception {
        int id;
        double start;
        double end;
        log.info("Input id Bouquet for find flowers by stem length");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        log.info("Input the minimum sample value for flowers by stem length");
        start = scanner.nextDouble();
        log.info("Input the maximum sample value for flowers by stem length");
        end = scanner.nextDouble();
        BOUQUET_SERVICE.chooseFlowerRangeOfStemLength(id,start,end);
    }

    private static void sortFlowersByFreshness() throws Exception {
        int id;
        log.info("Input id Bouquet for sorting flowers by freshness");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        BOUQUET_SERVICE.sortingByFreshness(id);
    }

    private static void showFlowersAndAccessoriesInBouquet() throws Exception {
        int id;
        log.info("Input id Bouquet for show flowers and accessories");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        BOUQUET_SERVICE.showFlowersAndAccessoriesInfo(id);
    }


    private static void deleteBouquet() throws Exception {
        int idForDelete;
        log.info("Input id Bouquet for delete");
        Scanner scanner = new Scanner(System.in);
        idForDelete = scanner.nextInt();
        BOUQUET_SERVICE.deleteBouquet(idForDelete);
    }



    private static void addBouquet() throws Exception {
        Bouquet.BouquetBuilder builder = Bouquet.builder();

        log.info("Input information about Bouquet");
        Scanner scanner = new Scanner(System.in);

        log.info("Name: ");
        builder.nameBouquet(scanner.nextLine());
        builder.flowersList(new ArrayList<>());
        builder.accessoriesList(new ArrayList<>());
        Bouquet bouquet = builder.build();

        BOUQUET_SERVICE.saveBouquet(bouquet);
    }
    private static void addFlowersInBouquet() throws Exception {
        log.info("Input the Bouquet id to add flowers");
        Scanner scanner = new Scanner(System.in);
        int idBouquet=scanner.nextInt();
        Bouquet bouquet = BOUQUET_SERVICE.getBouquet(idBouquet);
        List<Flowers> flowersList= bouquet.getFlowersList();
        flowersList.add(BOUQUET_SERVICE.addFlowerInBouquet());
    }
    private static void addAccessoriesInBouquet() throws Exception {
        log.info("Input the Bouquet id to add accessories");
        Scanner scanner = new Scanner(System.in);
        int idBouquet=scanner.nextInt();
        Bouquet bouquet = BOUQUET_SERVICE.getBouquet(idBouquet);
        List<Accessories> accessoriesList= bouquet.getAccessoriesList();
        accessoriesList.add(BOUQUET_SERVICE.addAccessoriesInBouquet());
    }

}
