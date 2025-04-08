import interfaces.CustomerBehavior;
import models.*;
import models.enums.CustomerPreference;
import models.enums.IngredientType;
import models.generators.GeneratorForCustomer;
import models.generators.GeneratorForEmployee;
import org.apache.log4j.PropertyConfigurator;
import utils.TimeManager;

import java.util.HashMap;
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
        Ingredient marchewka2 = new Ingredient(IngredientType.VEGETABLE, 1.9, 3);
        Ingredient mieso = new Ingredient(IngredientType.MEAT, 23, 1);

        restaurantManage.getMagazine().addResource(marchewka);
        restaurantManage.getMagazine().addResource(marchewka2);
        restaurantManage.getMagazine().addResource(mieso);


        Employee e1 = generatorForEmployee.generateOneEmployee();
        restaurantManage.hireEmployee(e1);
        System.out.println(restaurantManage.getEmployees());
        restaurantManage.fireEmplyee(e1);
        System.out.println(restaurantManage.getEmployees());


        CustomerBehavior behavior = GeneratorForCustomer.getRandomBehavior();
        Customer customer = new Customer("Jan", "Nowak", CustomerPreference.MEAT, 50.0, behavior);

        double toPay = customer.payTheBill(40.0);
        System.out.println("Zapłacił: " + toPay);
        System.out.println(customer.makeASpeech());

        Map<IngredientType, Integer> beefSteakIngredients = new HashMap<>();
        beefSteakIngredients.put(IngredientType.MEAT, 1);
        beefSteakIngredients.put(IngredientType.VEGETABLE, 2);

        Recipe beefSteak = new Recipe("Beef Steak", beefSteakIngredients, 20, 1, restaurantManage.getMagazine());
        beefSteak.costToPrepare(restaurantManage.getMagazine());
        beefSteak.setSellingPrice(restaurantManage.getMagazine(),1.3);
        System.out.println(beefSteak.costToPrepare(restaurantManage.getMagazine()));
        System.out.println(beefSteak);

        System.out.println(beefSteak.isPossibleToPrepare(restaurantManage.getMagazine()));
        System.out.println(restaurantManage.getMagazine().getResources());


    }
}
