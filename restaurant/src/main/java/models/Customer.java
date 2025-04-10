package models;

import interfaces.CustomerBehavior;
import models.enums.CustomerPreference;

public class Customer {

    private String name;
    private String surename;
    private CustomerPreference dishPreference;
    private double budget;
    private final CustomerBehavior behavior;

    public Customer(String name, String surename, CustomerPreference dishPreference, double budget, CustomerBehavior behavior) {
        this.name = name;
        this.surename = surename;
        this.dishPreference = dishPreference;
        this.budget = budget;
        this.behavior = behavior;
    }

    public double payTheBill(double amountToPay) {
        double finalAmount = behavior.pay(amountToPay);

        if (this.budget >= finalAmount) {
            this.budget -= finalAmount;
            return finalAmount;
        } else {
            System.out.println("Klient " + name + " nie ma wystarczających środków na pokrycie rachunku.");
            double paid = this.budget;
            this.budget = 0;
            return paid;
        }
    }

    public String makeASpeech() {
        return behavior.reaction();
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public CustomerPreference getDishPreference() {
        return dishPreference;
    }

    public double getBudget() {
        return budget;
    }

    public CustomerBehavior getBehavior() {
        return behavior;
    }

}
