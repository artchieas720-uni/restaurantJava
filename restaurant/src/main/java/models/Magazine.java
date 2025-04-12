package models;

import models.enums.EquipmentType;
import models.enums.IngredientType;
import models.enums.TransactionType;
import models.generators.GeneratorForEmployee;
import models.helpers.RestaurantEquipmentHelper;
import utils.TimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Magazine {

    private List<Ingredient> resources;
    private RestaurantEquipmentHelper restaurantEquipmentHelper;

    public Magazine() {
        this.resources = new ArrayList<>();
        this.restaurantEquipmentHelper = new RestaurantEquipmentHelper();
    }

    public List<Ingredient> getResources() {
        return resources;
    }

    public Magazine(List<Ingredient> resources, RestaurantEquipmentHelper restaurantEquipmentHelper) {
        this.resources = resources;
        this.restaurantEquipmentHelper = restaurantEquipmentHelper;
    }


    public int getCurrentStorageSize() {
        return resources.stream().mapToInt(Ingredient::getCount).sum();
    }


    public int getMaxStorageCapacity() {
        int fridge = restaurantEquipmentHelper.getEquipmentCount(EquipmentType.FRIDGE) * 50;
        int freezer = restaurantEquipmentHelper.getEquipmentCount(EquipmentType.FREEZER) * 100;
        return fridge + freezer;
    }

    public void removeExpiredIngredients(LocalDateTime currentTime) {
        resources.removeIf(ingredient -> ingredient.isExpired(currentTime));
    }

    public RestaurantEquipmentHelper getEquipmentHelper() {
        return restaurantEquipmentHelper;
    }

    public void setEquipmentHelper(RestaurantEquipmentHelper restaurantEquipmentHelper) {
        this.restaurantEquipmentHelper = restaurantEquipmentHelper;
    }

    public void addResource(Ingredient newIngredient) {
        int totalStorage = getCurrentStorageSize();
        int maxCapacity = getMaxStorageCapacity();

        if (totalStorage + newIngredient.getCount() > maxCapacity) {
            System.out.println("❌ Magazyn pełny! Nie można dodać więcej składników.");
            return;
        }

        boolean merged = false;

        for (Ingredient existing : resources) {
            if (existing.getType() == newIngredient.getType() &&
                    existing.getExpireDate().equals(newIngredient.getExpireDate())) {

                int spaceLeft = maxCapacity - totalStorage;
                int toAdd = Math.min(spaceLeft, newIngredient.getCount());

                existing.setCount(existing.getCount() + toAdd);
                merged = true;
                break;
            }
        }

        if (!merged) {
            this.resources.add(newIngredient);
        }

        System.out.println("✅ Dodano składnik: " + newIngredient.getType() +
                " (ilość: " + newIngredient.getCount() + ")");
    }


    public void addResource(IngredientType type, double price , int count) {
        Ingredient ingredient = new Ingredient(type, price, count);
        addResource(ingredient);

    }

    public void addResource(IngredientType type, double price , int count, RestaurantManage restaurantManage) {
        Ingredient ingredient = new Ingredient(type, price, count);
        addResource(ingredient);
        restaurantManage.getFinancialHelper().addTransaction(
                restaurantManage.getTimeManager().getCurrentTime(),
                "Zakup składników: " + type + " x" + count,
                count,
                TransactionType.EXPENSES,
                null,
                null
        );
    }

    public void sortIngredientsByExpiration() {
        resources.sort(Comparator.comparing(Ingredient::getExpireDate));
    }


}
