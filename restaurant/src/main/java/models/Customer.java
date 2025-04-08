package models;

import interfaces.CustomerBehavior;
import models.enums.CustomerPreference;

public class Customer {

    private String name;
    private String surename;
    private CustomerPreference dishPreference;
    private double budget;
    private int levelOfSatisfaction;
    private final CustomerBehavior behavior;

    public Customer(String name, String surename, CustomerPreference dishPreference, double budget, CustomerBehavior behavior) {
        this.name = name;
        this.surename = surename;
        this.dishPreference = dishPreference;
        this.budget = budget;
        this.levelOfSatisfaction = 50;
        this.behavior = behavior;
    }

    public double payTheBill(double amountToPay) {
        return behavior.pay(amountToPay);
    }

    public String makeASpeech() {
        return behavior.reaction();
    }

}
