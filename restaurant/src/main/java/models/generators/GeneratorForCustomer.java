package models.generators;

import interfaces.CustomerBehavior;
import models.customerBehaviour.NormalCustomer;
import models.customerBehaviour.QuirkCustomer;
import models.customerBehaviour.RudeCustomer;

import java.util.Random;

public class GeneratorForCustomer {

    private static final Random random = new Random();

    public static CustomerBehavior getRandomBehavior() {
        int randomPercent = random.nextInt(100);
        if (randomPercent < 70) return new NormalCustomer();
        else if (randomPercent < 98) return new QuirkCustomer();
        else return new RudeCustomer();
    }


}
