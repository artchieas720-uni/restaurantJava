package models.customerBehaviour;

import interfaces.CustomerBehavior;

public class QuirkCustomer implements CustomerBehavior {

    @Override
    public double pay(double amountToPay) {
        return amountToPay * 0.9;
    }

    @Override
    public String reaction() {
        return "It's too much for me I am gonna pay only a half";
    }

    @Override
    public void consume() {

    }
}
