package models.customerBehaviour;

import interfaces.CustomerBehavior;

public class RudeCustomer implements CustomerBehavior {
    @Override
    public double pay(double amountToPay) {
        return 0;
    }

    @Override
    public String reaction() {
        return "I will not pay!";
    }

    @Override
    public void consume() {

    }
}
