package models.generators;

import interfaces.CustomerBehavior;
import models.customerBehaviour.*;

import java.util.Random;

public class GeneratorForCustomer {

    private static final Random random = new Random();

    public static CustomerBehavior getRandomBehavior() {
        int randomPercent = random.nextInt(100);
        if (randomPercent < 75) return new NormalCustomer();
        else if (randomPercent < 78) return new QuirkCustomer();
        else if (randomPercent < 85) return new VipCustomer();
        else if (randomPercent < 97) return new InfluenceCustomer();
        else return new RudeCustomer();
    }




}
