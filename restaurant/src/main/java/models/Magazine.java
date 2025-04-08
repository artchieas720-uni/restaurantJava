package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Magazine {

    private List<Ingredient> resources;

    public Magazine() {
        this.resources = new ArrayList<>();
    }

    public List<Ingredient> getResources() {
        return resources;
    }

    public Magazine(List<Ingredient> resources) {
        this.resources = resources;
    }

    public void addResource(Ingredient resource) {
        this.resources.add(resource);
    }

    public void removeExpiredIngredients(LocalDateTime currentTime) {
        resources.removeIf(ingredient -> ingredient.isExpired(currentTime));
    }
}
