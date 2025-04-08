package models.helpers;

public class ExpHelper {
    public static int getXpForLevel(int level, double additionMultiplier, double powerMultiplier, double divisionMultiplier) {
        int totalXp = 0;

        for (int levelCycle = 1; levelCycle <= level; levelCycle++) {
            double value = levelCycle + additionMultiplier * Math.pow(powerMultiplier, levelCycle / divisionMultiplier);
            totalXp += (int) Math.floor(value);
        }

        return totalXp / 4;
    }
}
