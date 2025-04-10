import fileutils.FinancialRapportsExporter;
import fileutils.GameConfigReader;
import interfaces.CustomerBehavior;
import models.*;
import models.enums.*;
import models.generators.GeneratorForCustomer;
import models.generators.GeneratorForEmployee;
import models.helpers.FinancialHelper;
import org.apache.log4j.PropertyConfigurator;
import utils.TimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestaurantAPP {


    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/java/configs/log4j.properties");

        GameConfigReader config = new GameConfigReader("src/main/java/configs/conf.txt");
        RestaurantManage restaurantManage = new RestaurantManage("Pomidorek");

        GeneratorForEmployee generatorForEmployee = new GeneratorForEmployee();
//        SwingUtilities.invokeLater(() -> {
//            new loginFrame().setVisible(true);
//        });


        TimeManager timeManager = TimeManager.startNewGame();


        Ingredient marchewka = new Ingredient(IngredientType.VEGETABLE, 1.2, 2);
        Ingredient marchewka2 = new Ingredient(IngredientType.VEGETABLE, 1.9, 1);
        Ingredient mieso = new Ingredient(IngredientType.MEAT, 23, 1);



        restaurantManage.getMagazine().addResource(marchewka);
        restaurantManage.getMagazine().addResource(marchewka2);
        restaurantManage.getMagazine().addResource(mieso);


        Employee e1 = generatorForEmployee.generateOneEmployee();
        restaurantManage.hireEmployee(e1);
        System.out.println(restaurantManage.getEmployees());
        restaurantManage.fireEmployee(e1);
        System.out.println(restaurantManage.getEmployees());


        CustomerBehavior behavior = GeneratorForCustomer.getRandomBehavior();
        Customer customer = new Customer("Jan", "Nowak", CustomerPreference.MEAT, 90.0, behavior);

        Map<IngredientType, Integer> beefSteakIngredients = new HashMap<>();
        beefSteakIngredients.put(IngredientType.MEAT, 1);
        beefSteakIngredients.put(IngredientType.VEGETABLE, 2);

        Recipe beefSteak = new Recipe("Beef Steak", beefSteakIngredients, 20, 1, restaurantManage.getMagazine());
        beefSteak.costToPrepare(restaurantManage.getMagazine());
        System.out.println(beefSteak.costToPrepare(restaurantManage.getMagazine()));
        System.out.println(beefSteak);

        System.out.println(beefSteak.isPossibleToPrepare(restaurantManage.getMagazine()));
        System.out.println(restaurantManage.getMagazine().getResources());

        FinancialHelper fh = new FinancialHelper();

// Raport dzienny
        fh.printSummaryReport(fh.getTransactionsForDay(timeManager.getCurrentTime().toLocalDate()));

        List<Recipe> singleOrder = List.of(beefSteak);

        restaurantManage.processCustomerOrder(customer,singleOrder,fh,timeManager, OrderType.ON_PLACE);
        customer.getBehavior().consume();
        System.out.println(customer.getBehavior().reaction());

        System.out.println(restaurantManage.getMoney());

        fh.printSummaryReport(fh.getTransactions());
        System.out.println(fh.getTransactions());

        //FinancialRapportsExporter.exportReportToTxt("transactions " + timeManager.getCurrentTime().toLocalDate(), fh.getTransactions() );
//        System.out.println(restaurantManage.getMagazine().getResources());


        restaurantManage.getMagazine().addResource(marchewka);
        restaurantManage.getMagazine().addResource(marchewka2);
        restaurantManage.getMagazine().addResource(mieso);
//        System.out.println(restaurantManage.getRestaurantEquipmentHelper().getMaxStorageCapacity());
//        System.out.println(restaurantManage.getMagazine().getCurrentStorageSize());
//        System.out.println(restaurantManage.getMagazine().getMaxStorageCapacity());
        restaurantManage.getMagazine().getEquipmentHelper().addEquipment(EquipmentType.FREEZER, 2);
        System.out.println(restaurantManage.getMagazine().getCurrentStorageSize());
        System.out.println(restaurantManage.getMagazine().getMaxStorageCapacity());

        restaurantManage.getMagazine().addResource(marchewka);
        restaurantManage.getMagazine().addResource(marchewka2);
        restaurantManage.getMagazine().addResource(mieso);
        System.out.println(restaurantManage.getMagazine().getEquipmentHelper().getMaxStorageCapacity());
        System.out.println(restaurantManage.getMagazine().getCurrentStorageSize());
        System.out.println(restaurantManage.getMagazine().getMaxStorageCapacity());
    }
}
