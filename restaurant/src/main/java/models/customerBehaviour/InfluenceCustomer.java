package models.customerBehaviour;

import interfaces.CustomerBehavior;

public class InfluenceCustomer implements CustomerBehavior {

    @Override
    public double pay(double cash) {
        return cash * 1.9;
    }

    @Override
    public String reaction() {
        return "That was well";
    }

    @Override
    public void consume() {

    }
}
