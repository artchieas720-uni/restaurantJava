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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestaurantAPP {


    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/java/configs/log4j.properties");

        GameConfigReader config = new GameConfigReader("src/main/java/configs/conf.txt");
        RestaurantManage restaurantManage = new RestaurantManage("Pomidorek");

        GeneratorForEmployee generator = new GeneratorForEmployee();
//        SwingUtilities.invokeLater(() -> {
//            new loginFrame().setVisible(true);
//        });

        restaurantManage.getMagazine().getEquipmentHelper().addEquipment(EquipmentType.TABLE, 2);
        restaurantManage.getMagazine().getEquipmentHelper().addEquipment(EquipmentType.FREEZER, 6);
        restaurantManage.getMagazine().getEquipmentHelper().addEquipment(EquipmentType.KITCHEN, 1);
        System.out.println(restaurantManage.getMagazine().getEquipmentHelper().restaurantCanOperate());


        for (int i = 0; i < 2; i++) {
            Employee chef = generator.generateOneEmployee(EmployeeRole.CHEF);
            restaurantManage.hireEmployee(chef);
        }


        for (int i = 0; i < 2; i++) {
            Employee waiter = generator.generateOneEmployee(EmployeeRole.WAITER);
            restaurantManage.hireEmployee(waiter);
        }

        restaurantManage.getEmployeeHelper().getYourBestSquadOnBoard();


        List<Recipe> recipes = new ArrayList<>();
        Magazine magazine = restaurantManage.getMagazine();

        Map<IngredientType, Integer> pastaIngredients = new HashMap<>();
        pastaIngredients.put(IngredientType.TOMATO, 2);
        pastaIngredients.put(IngredientType.PASTA, 1);
        recipes.add(new Recipe("Spaghetti Pomidoro", pastaIngredients, 15, 1, magazine, DishType.MAIN_COURSE));

        Map<IngredientType, Integer> saladIngredients = new HashMap<>();
        saladIngredients.put(IngredientType.LETTUCE, 1);
        saladIngredients.put(IngredientType.TOMATO, 1);
        recipes.add(new Recipe("Sałatka Wiosenna", saladIngredients, 10, 1, magazine, DishType.STARTER));


        Menu menu = new Menu(recipes);
        restaurantManage.setMenu(menu);


        magazine.addResource(IngredientType.TOMATO, 2.0, 100, restaurantManage);
        magazine.addResource(IngredientType.PASTA, 1.5, 100, restaurantManage);
        magazine.addResource(IngredientType.LETTUCE, 1.0, 100, restaurantManage);


        for (int i = 0; i < 10; i++) {
            System.out.println("\n===== Godziny " + (i + 1) + " w restauracji '" + restaurantManage.getRestaurantName() + "' =====");
            restaurantManage.advanceTime(1);
            System.out.println("💰 Stan konta: " + restaurantManage.getMoney() + " zł");
            // System.out.println(restaurantManage.getReputation().getReputationScore());
            // System.out.println(restaurantManage.getEmployeeHelper().getTodaysStaff());
            //restaurantManage.getFinancialHelper().printSummaryReport(restaurantManage.getFinancialHelper().getTransactions());
        }

        System.out.println("\n Symulacja ended");
    }

}


