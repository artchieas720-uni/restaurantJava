package models;

import models.enums.IngredientType;
import models.enums.ProviderType;
import models.generators.GeneratorForEmployee;
import utils.TimeManager;

import java.time.LocalDateTime;


public class Ingredient {

    private IngredientType type;
    private ProviderType provider;
    private LocalDateTime expireDate;
    private double price;
    private int count;


    public Ingredient(IngredientType type, double price, int count, TimeManager timeManager, int daysLong) {
        this.type = type;
        this.price = price;
        this.count = count;
        this.provider = ProviderType.FROM_START;
        this.expireDate = timeManager.getCurrentTime().plusDays(daysLong);
    }

    public Ingredient(IngredientType type, double price, int count) {
        this.type = type;
        this.price = price;
        this.count = count;
        this.provider = ProviderType.FROM_START;
        this.expireDate = GeneratorForEmployee.generateRandomExpirationDate(TimeManager.startNewGame().getCurrentTime());
    }

    public boolean isExpired(LocalDateTime currentTime) {
        return currentTime.isAfter(expireDate);
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    // GETTER AND SETTER


    public IngredientType getType() {
        return type;
    }

    public ProviderType getProvider() {
        return provider;
    }

    public double getPrice() {
        return this.price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "type=" + type +
                ", provider=" + provider +
                ", expireDate=" + expireDate +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
