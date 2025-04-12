package models.helpers;

import models.Customer;
import models.Menu;
import models.Recipe;
import models.enums.DishType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerOrderHelper {


    private static final Random percent = new Random();

    public static List<Recipe> chooseRecipesForCustomer(Customer customer, Menu menu) {
        List<Recipe> allRecipes = menu.getRecipes();
        List<Recipe> preferredRecipes = new ArrayList<>();

        DishType preference = customer.getDishPreference();

        for (Recipe recipe : allRecipes) {
            if (recipe.getDishType().equals(preference)) {
                preferredRecipes.add(recipe);
            }
        }

        if (preferredRecipes.isEmpty()) {
            preferredRecipes.add(allRecipes.get(percent.nextInt(allRecipes.size())));
        } else {
            int count = percent.nextInt(1, Math.min(3, preferredRecipes.size() + 1));
            while (preferredRecipes.size() > count) {
                preferredRecipes.remove(percent.nextInt(preferredRecipes.size()));
            }
        }

        return preferredRecipes;
    }
}
