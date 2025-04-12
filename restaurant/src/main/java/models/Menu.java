package models;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Recipe> recipes;

    public Menu(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Menu() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipeToMenu(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipeFromMenu(Recipe recipe) {
        recipes.remove(recipe);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Recipe findRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }


}
