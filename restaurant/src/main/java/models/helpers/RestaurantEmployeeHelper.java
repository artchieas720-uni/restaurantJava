package models.helpers;

import models.Employee;
import models.enums.EmployeeRole;
import models.enums.EmployeeStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantEmployeeHelper {


    private List<Employee> employeeList;
    private List<Employee> todaysStaff;

    private final int minRequiredChefs = 1;
    private final int minRequiredWaiters = 1;

    private int maxAllowedChefs;
    private int maxAllowedWaiters;

    public RestaurantEmployeeHelper(List<Employee> employeeList, int restaurantLevel) {
        this.employeeList = employeeList;
        this.todaysStaff = new ArrayList<>();
        updateMaxStaffByLevel(restaurantLevel);
    }

    public void updateMaxStaffByLevel(int level) {
        this.maxAllowedChefs = 2 + level ;
        this.maxAllowedWaiters = 1 + level;
    }

    public void assignTodaysStaff(List<Employee> staffForToday) {
        this.todaysStaff = staffForToday;
    }

    public boolean restaurantCanFunction() {
        int chefs = getCurrentChefCount();
        int waiters = getCurrentWaiterCount();

        return chefs >= minRequiredChefs && waiters >= minRequiredWaiters;
    }

    public int getCurrentChefCount() {
        return (int) todaysStaff.stream()
                .filter(e -> e.getRole() == EmployeeRole.CHEF || e.getRole() == EmployeeRole.MASTERCHIEF)
                .count();
    }

    public int getCurrentWaiterCount() {
        return (int) todaysStaff.stream()
                .filter(e -> e.getRole() == EmployeeRole.WAITER)
                .count();
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public List<Employee> getTodaysStaff() {
        return todaysStaff != null ? todaysStaff : new ArrayList<>();
    }

    public int getMaxAllowedChefs() {
        return maxAllowedChefs;
    }

    public int getMaxAllowedWaiters() {
        return maxAllowedWaiters;
    }

    public int getMinRequiredChefs() {
        return minRequiredChefs;
    }

    public int getMinRequiredWaiters() {
        return minRequiredWaiters;
    }

    public void getYourBestSquadOnBoard() {
        List<Employee> hiredChefs = employeeList.stream()
                .filter(e -> e.getStatus() == EmployeeStatus.HIRED &&
                        (e.getRole() == EmployeeRole.CHEF || e.getRole() == EmployeeRole.MASTERCHIEF))
                .sorted(Comparator.comparingDouble(Employee::getFatigue)
                        .thenComparing(Comparator.comparingDouble(Employee::getSkillsRate).reversed()))
                .limit(maxAllowedChefs)
                .collect(Collectors.toList());

        List<Employee> hiredWaiters = employeeList.stream()
                .filter(e -> e.getStatus() == EmployeeStatus.HIRED && e.getRole() == EmployeeRole.WAITER)
                .sorted(Comparator.comparingDouble(Employee::getFatigue)
                        .thenComparing(Comparator.comparingDouble(Employee::getSkillsRate).reversed()))
                .limit(maxAllowedWaiters)
                .collect(Collectors.toList());

        List<Employee> optimalStaff = new ArrayList<>();
        optimalStaff.addAll(hiredChefs);
        optimalStaff.addAll(hiredWaiters);

        this.todaysStaff = optimalStaff;
    }

    public void printSquadOnBoard() {
        System.out.println("📋 Skład na dzisiaj:");

        if (todaysStaff == null || todaysStaff.isEmpty()) {
            System.out.println("❌ Brak przydzielonego personelu na dziś.");
            return;
        }

        for (Employee employee : todaysStaff) {
            System.out.println("🧑‍🍳 " + employee.getRole() + " | " + employee.getName() +
                    " | Fatigue: " + String.format("%.2f", employee.getFatigue()) +
                    " | Skills: " + String.format("%.2f", employee.getSkillsRate()));
        }
    }

}


