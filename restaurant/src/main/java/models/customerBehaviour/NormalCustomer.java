package models.customerBehaviour;

import interfaces.CustomerBehavior;

public class NormalCustomer implements CustomerBehavior {

    @Override
    public double pay(double amountToPay) {
        return amountToPay;
    }

    @Override
    public String reaction() {
        return "Thanks, all was awesome";
    }

    @Override
    public void consume() {

    }
}
