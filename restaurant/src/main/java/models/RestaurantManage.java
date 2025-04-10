package models;

import fileutils.GameConfigReader;
import models.enums.*;
import models.helpers.FinancialHelper;
import models.helpers.NotificationHelper;
import models.helpers.RestaurantEmployeeHelper;
import models.helpers.RestaurantEquipmentHelper;
import utils.TimeManager;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantManage{

    private final String restaurantName;
    private Magazine magazine;
    private double money;
    private double marginMultiplier = 1.3;
    private int level;
    private RestaurantEmployeeHelper restaurantEmployeeHelper;
    private GameConfigReader gameConfigReader = new GameConfigReader("src/main/java/configs/conf.txt");;
    private FinancialHelper financialHelper = new FinancialHelper();
    private RestaurantEquipmentHelper restaurantEquipmentHelper = new RestaurantEquipmentHelper();
    private NotificationHelper notificationHelper = new NotificationHelper();
    private Reputation reputation = new Reputation();



    private List<Employee> employees;

    public RestaurantManage(String restaurantName) {
        this.employees = new ArrayList<>();
        this.restaurantName = restaurantName;
        this.money = gameConfigReader.getDouble("moneyOnStart", 10000);
        this.magazine = new Magazine();
        this.level = 1;
        this.restaurantEmployeeHelper = new RestaurantEmployeeHelper(this.employees, this.level);

    }

    public void setMarginMultiplier(double multiplier) {
        this.marginMultiplier = multiplier;
    }

    public double getMarginMultiplier() {
        return this.marginMultiplier;
    }

    public RestaurantEmployeeHelper getEmployeeHelper() {
        return restaurantEmployeeHelper;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void hireEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setStatus(EmployeeStatus.HIRED);
        employee.setPlaceOfWorking(this.restaurantName);
    }

    public void fireEmployee(Employee employee){
        this.employees.remove(employee);
        employee.setStatus(EmployeeStatus.UNHIRED);
        employee.setPlaceOfWorking("");
    }


    public double calculateTotalAmount(List<Recipe> orderedRecipes) {
        double totalAmount = 0;
        for (Recipe recipe : orderedRecipes) {
            double price = recipe.setSellingPrice(this.magazine, this.marginMultiplier);
            totalAmount += price;
        }
        return totalAmount;
    }

    public boolean processCustomerOrder(Customer customer, List<Recipe> orderedRecipes, FinancialHelper financialHelper, TimeManager timeManager, OrderType orderType) {
        double totalAmount = calculateTotalAmount(orderedRecipes);

        if (!this.restaurantEmployeeHelper.restaurantCanFunction()) {
            System.out.println("❌ Restauracja nie może działać – brakuje personelu (wymagane: min. " +
                    restaurantEmployeeHelper.getMinRequiredChefs() + " kucharz(e) i " +
                    restaurantEmployeeHelper.getMinRequiredWaiters() + " kelner(ów)).");
            return false;
        }

        if (!restaurantEquipmentHelper.restaurantCanOperate()) {
            System.out.println("❌ Restauracja nie może działać – brak podstawowego wyposażenia kuchni.");
            return false;
        }

        CustomerReaction reaction = calculateCustomerReaction();
        reputation.adjustReputation(reaction);

        Map<IngredientType, Integer> totalNeeded = new HashMap<>();
        for (Recipe recipe : orderedRecipes) {
            for (Map.Entry<IngredientType, Integer> entry : recipe.getIngredientsToPrepare().entrySet()) {
                totalNeeded.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        Map<IngredientType, Long> available = new HashMap<>();
        for (Ingredient ingredient : magazine.getResources()) {
            available.merge(ingredient.getType(), 1L, Long::sum);
        }

        for (Map.Entry<IngredientType, Integer> entry : totalNeeded.entrySet()) {
            long availableCount = available.getOrDefault(entry.getKey(), 0L);
            if (availableCount < entry.getValue()) {
                System.out.println("Brakuje składników: " + entry.getKey());
                return false;
            }
        }

        for (Recipe recipe : orderedRecipes) {
            processRecipe(recipe);
        }

        double paidAmount = customer.getBehavior().pay(totalAmount);
        paidAmount = Math.min(paidAmount, customer.getBudget());
        paidAmount = customer.payTheBill(paidAmount);

        Random random = new Random();
        for (Employee employee : employees) {
            double fatigueGain = 1.0 + random.nextDouble() * 2.0;
            employee.setFatigue(employee.getFatigue() + fatigueGain);
        }

        if (paidAmount < totalAmount) {
            System.out.println("⚠️  Klient " + customer.getName() + " zapłacił tylko " + paidAmount + " zł za danie warte " + totalAmount + " zł.");
            System.out.println(customer.makeASpeech());
        } else {
            System.out.println("✅ Klient " + customer.getName() + " zapłacił pełną kwotę: " + totalAmount + " zł.");
        }

        financialHelper.addTransaction(
                timeManager.getCurrentTime(),
                "Sprzedaż dla klienta " + customer.getName(),
                paidAmount,
                TransactionType.INCOME,
                customer,
                orderType
        );

        this.money += paidAmount;

        return true;
    }


    private boolean processRecipe(Recipe recipe) {

        Map<IngredientType, Integer> neededIngredients = recipe.getIngredientsToPrepare();

        Map<IngredientType, Integer> available = new HashMap<>();
        for (Ingredient ingredient : magazine.getResources()) {
            available.merge(ingredient.getType(), ingredient.getCount(), Integer::sum);
        }

        for (Map.Entry<IngredientType, Integer> entry : neededIngredients.entrySet()) {
            int availableCount = available.getOrDefault(entry.getKey(), 0);
            if (availableCount < entry.getValue()) {
                System.out.println("Brakuje składników do przygotowania dania: " + recipe.getName() + " - " + entry.getKey());
                return false;
            }
        }

        for (Map.Entry<IngredientType, Integer> entry : neededIngredients.entrySet()) {
            IngredientType type = entry.getKey();
            int quantityNeeded = entry.getValue();
            int removed = 0;

            Iterator<Ingredient> it = magazine.getResources().iterator();
            while (it.hasNext() && removed < quantityNeeded) {
                Ingredient ing = it.next();
                if (ing.getType() == type) {
                    int toRemove = Math.min(ing.getCount(), quantityNeeded - removed);
                    ing.setCount(ing.getCount() - toRemove);
                    removed += toRemove;

                    if (ing.getCount() <= 0) {
                        it.remove();
                    }
                }
            }
        }
        System.out.println("✅ Danie: " + recipe.getName() + " zostało przygotowane i sprzedane.");
        return true;
    }

    public void levelUp() {
        this.level++;
        restaurantEmployeeHelper.updateMaxStaffByLevel(this.level);
    }

    public void handleDataExpireInFood(IngredientType type, int price, int count, TimeManager timeManager) {
        magazine.addResource(type, price, count);
        notificationHelper.notifyAboutExpiringIngredients(magazine, timeManager.getCurrentTime());
    }

    public CustomerReaction calculateCustomerReaction() {
        List<Employee> chefs = employees.stream()
                .filter(e -> e.getRole().equals(EmployeeRole.CHEF))
                .collect(Collectors.toList());

        List<Employee> waiters = employees.stream()
                .filter(e -> e.getRole().equals(EmployeeRole.WAITER))
                .collect(Collectors.toList());

        double avgChefsSkill = chefs.stream().mapToDouble(Employee::getSkillsRate).average().orElse(0);
        double avgWaiterSkill = waiters.stream().mapToDouble(Employee::getSkillsRate).average().orElse(0);

        double avgChefsFatigue = chefs.stream().mapToDouble(Employee::getFatigue).average().orElse(0);
        double avgWaiterFatigue = waiters.stream().mapToDouble(Employee::getFatigue).average().orElse(0);

        double fatiguePenalty = (avgChefsFatigue * 0.6 + avgWaiterFatigue * 0.4) * 0.5;


        double satisfactionChance = (avgChefsSkill * 0.6 + avgWaiterSkill * 0.4) - fatiguePenalty;
        satisfactionChance = Math.max(0, Math.min(satisfactionChance, 100));

        double percents = Math.random() * 100;

        return percents < satisfactionChance ? CustomerReaction.HAPPY : CustomerReaction.UNHAPPY;
    }



    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public int getLevel() {
        return level;
    }


}
