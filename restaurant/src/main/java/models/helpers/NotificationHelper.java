package models.helpers;

import models.Ingredient;
import models.Magazine;

import java.time.LocalDateTime;

public class NotificationHelper {
    public void notifyAboutExpiringIngredients(Magazine magazine, LocalDateTime currentTime) {
        for (Ingredient ingredient : magazine.getResources()) {
            long daysLeft = java.time.Duration.between(currentTime, ingredient.getExpireDate()).toDays();
            if (daysLeft <= 2 && daysLeft >= 0) {
                System.out.println("⚠️ Uwaga! Składnik " + ingredient.getType() +
                        " przeterminuje się za " + daysLeft + " dzień/dni.");
            }
        }
    }
}
