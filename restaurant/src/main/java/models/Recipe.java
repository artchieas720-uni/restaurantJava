package models;

import models.enums.DishType;
import models.enums.IngredientType;

import java.util.List;
import java.util.Map;

public class Recipe {
    private String name;
    private Map<IngredientType, Integer> IngredientsToPrepare;
    private int preparationTimeInMinutes;
    private double costToMake;
    private double sellingPrice;
    private int requiredLevelToPrepare;
    private DishType dishType;


    public Recipe(String name, Map<IngredientType, Integer> IngredientsToPrepare, int preparationTimeInMinutes, int requiredLevelToPrepare, Magazine magazine, DishType dishType) {
        this.name = name;
        this.IngredientsToPrepare = IngredientsToPrepare;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
        this.sellingPrice = setSellingPrice(magazine, 1);
        this.costToMake = costToPrepare(magazine);
        this.requiredLevelToPrepare = requiredLevelToPrepare;
        this.dishType = dishType;
    }

    public boolean isPossibleToPrepare(Magazine magazine){
        for( Map.Entry<IngredientType, Integer> each : IngredientsToPrepare.entrySet()){
            long availableCount = magazine.getResources().stream()
                    .filter(ingredient -> ingredient.getType() == each.getKey())
                    .count();
            if (availableCount < each.getValue()) {
                return false;
            }
        }
        return true;
    }

    public double costToPrepare(Magazine magazine) {
        double cost = 0;

        for (Map.Entry<IngredientType, Integer> each : IngredientsToPrepare.entrySet()) {
            IngredientType type = each.getKey();
            int howMuchNeed = each.getValue();

            List<Ingredient> ingredients = magazine.getResources().stream()
                    .filter(i -> i.getType() == type)
                    .limit(howMuchNeed)
                    .toList();

            for (Ingredient i : ingredients) {
                cost += i.getPrice();
            }
        }
        return cost;
    }

    public double setSellingPrice(Magazine magazine, double marginMultiplier) {
        double baseCost = costToPrepare(magazine);
        return baseCost * marginMultiplier;
    }

    //GETTER AND SETTER


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<IngredientType, Integer> getIngredientsToPrepare() {
        return IngredientsToPrepare;
    }

    public void setIngredientsToPrepare(Map<IngredientType, Integer> ingredientsToPrepare) {
        IngredientsToPrepare = ingredientsToPrepare;
    }

    public int getPreparationTimeInMinutes() {
        return preparationTimeInMinutes;
    }

    public void setPreparationTimeInMinutes(int preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public double getCostToMake() {
        return costToMake;
    }

    public void setCostToMake(double costToMake) {
        this.costToMake = costToMake;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getRequiredLevelToPrepare() {
        return requiredLevelToPrepare;
    }

    public void setRequiredLevelToPrepare(int requiredLevelToPrepare) {
        this.requiredLevelToPrepare = requiredLevelToPrepare;
    }

    public DishType getDishType() {
        return dishType;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", IngredientsToPrepare=" + IngredientsToPrepare +
                ", preparationTimeInMinutes=" + preparationTimeInMinutes +
                ", costToMake=" + costToMake +
                ", sellingPrice=" + sellingPrice +
                ", requiredLevelToPrepare=" + requiredLevelToPrepare +
                '}';
    }
}
