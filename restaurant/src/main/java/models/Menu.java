package models;

import java.util.List;

public class Menu {

    private List<Recipe> recipes;

    public Menu(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipeToMenu(Recipe recipe){
        recipes.add(recipe);
    }

    public void removeRecipeFromMenu(Recipe recipe){
        recipes.remove(recipe);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
