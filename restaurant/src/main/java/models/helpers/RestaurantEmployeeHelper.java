package models.helpers;

import models.Employee;
import models.enums.EmployeeRole;
import models.enums.EmployeeStatus;

import java.util.List;

public class RestaurantEmployeeHelper {


    private List<Employee> employeeList;

    private final int minRequiredChefs = 1;
    private final int minRequiredWaiters = 1;

    private int maxAllowedChefs;
    private int maxAllowedWaiters;

    public RestaurantEmployeeHelper(List<Employee> employeeList, int restaurantLevel) {
        this.employeeList = employeeList;
        updateMaxStaffByLevel(restaurantLevel);
    }

    public void updateMaxStaffByLevel(int level) {
        this.maxAllowedChefs = 2 + level ;
        this.maxAllowedWaiters = 1 + level;
    }

    public boolean restaurantCanFunction() {
        int chefCount = getCurrentChefCount();
        int waiterCount = getCurrentWaiterCount();

        if (chefCount < minRequiredChefs || waiterCount < minRequiredWaiters) {
            return false;
        }

        return true;
    }

    public int getCurrentChefCount() {
        return (int) employeeList.stream()
                .filter(e -> e.getStatus() == EmployeeStatus.HIRED &&
                        (e.getRole() == EmployeeRole.CHEF || e.getRole() == EmployeeRole.MASTERCHIEF))
                .count();
    }

    public int getCurrentWaiterCount() {
        return (int) employeeList.stream()
                .filter(e -> e.getStatus() == EmployeeStatus.HIRED &&
                        e.getRole() == EmployeeRole.WAITER)
                .count();
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}


