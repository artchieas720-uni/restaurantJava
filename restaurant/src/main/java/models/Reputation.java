package models;

import models.enums.CustomerReaction;
import models.enums.EmployeeRole;

import java.util.List;

public class Reputation {

    private int reputationScore = 0;

    public void adjustReputation(CustomerReaction reaction) {
        if (reaction == CustomerReaction.HAPPY) {
            reputationScore += 5;
        } else {
            reputationScore -= 5;
        }
        reputationScore = Math.max(-100, Math.min(10000, reputationScore));
    }

    public int getReputationScore() {
        return reputationScore;
    }
}
