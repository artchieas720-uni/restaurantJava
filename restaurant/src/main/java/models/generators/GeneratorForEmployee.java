package models.generators;

import models.Employee;
import models.enums.EmployeeRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Random;

public class GeneratorForEmployee {

    private static final Random random = new Random();

    private static final String[] NAMES = {
            "Jan", "Anna", "Piotr", "Kasia", "Marek", "Zosia", "Tomek", "Ola",
            "Magda", "Bartek", "Karolina", "Michal", "Ewa", "Kuba", "Agnieszka", "Pawel",
            "Natalia", "Grzegorz", "Monika", "lukasz", "Weronika", "Rafal", "Joanna", "Mateusz",
            "Julia", "Adrian", "Emilia", "Sebastian", "Wiktoria", "Szymon", "Martyna", "Krzysztof",
            "Dominika", "Patryk", "Sandra", "Dawid", "Gabriela", "Artur", "Alicja", "Maciej",
            "Maja", "Bartosz", "Lena", "Filip", "Zuzanna", "Oskar", "Amelia", "Igor",
            "Nikola", "Antoni", "Maria", "Jakub", "Helena", "Tymon", "Hanna", "Marcel",
            "Liliana", "Alan", "Laura", "Franciszek", "Michalina", "Kacper", "Nadia", "Leon"
    };

    // KINDA RANDOM SECTION

    public EmployeeRole getRandomRole(){
        EmployeeRole[] role = EmployeeRole.values();
        return role[random.nextInt(role.length)];
    }

    public String getRandomName(){
        return NAMES[random.nextInt(NAMES.length)];
    }

    public double getRandomSalary(){
        return random.nextDouble(30.5D, 55.0D);
    }

    public int getRandomStartingLevel(){
        return random.nextInt(1,15);
    }

    public LocalDate getRandomDate(){
        int year = random.nextInt(1960, 2005);
        int month = random.nextInt(1, 13);
        int day = random.nextInt(1, YearMonth.of(year, month).lengthOfMonth() + 1);

        return LocalDate.of(year, month, day);
    }

    public Employee generateOneEmployee(){
        return new Employee(getRandomName(), getRandomDate(), getRandomSalary(), getRandomStartingLevel(), getRandomRole());
    }

    // INGREDIENT RANDOMS

    public static LocalDateTime generateRandomExpirationDate(LocalDateTime currentTime) {
        int daysToAdd = random.nextInt(7, 31);
        return currentTime.plusDays(daysToAdd);
    }

}
