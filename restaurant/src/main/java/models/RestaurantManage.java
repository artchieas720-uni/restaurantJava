package models;

import models.enums.EmployeeStatus;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManage{

    private String name;

    private List<Employee> employees;

    public RestaurantManage(String name) {
        this.employees = new ArrayList<>();
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void hireEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setStatus(EmployeeStatus.HIRED);
        employee.setPlaceOfWorking(this.name);
    }


}
