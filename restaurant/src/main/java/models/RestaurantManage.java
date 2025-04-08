package models;

import models.enums.EmployeeStatus;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManage{

    private final String restaurantName;
    private Magazine magazine;
    private double money;

    GameConfigReader gameConfigReader = new GameConfigReader("src/main/java/configs/conf.txt");;

    private List<Employee> employees;

    public RestaurantManage(String restaurantName) {
        this.employees = new ArrayList<>();
        this.restaurantName = restaurantName;
        this.money = gameConfigReader.getDouble("moneyOnStart", 10000);
        this.magazine = new Magazine();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void hireEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setStatus(EmployeeStatus.HIRED);
        employee.setPlaceOfWorking(this.restaurantName);
    }

    public void fireEmplyee(Employee employee){
        this.employees.remove(employee);
        employee.setStatus(EmployeeStatus.UNHIRED);
        employee.setPlaceOfWorking("");
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
}
