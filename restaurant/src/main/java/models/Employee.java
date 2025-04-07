package models;

import models.enums.EmployeeStatus;
import models.enums.EmployeeRole;

import java.util.Date;

public class Employee {
    private String name;
    private Date birthdayDate;

    private double salary;
    private double skillsRate;
    private EmployeeStatus status;
    private EmployeeRole role;

    private double fatigue;
    private int level;

    private String placeOfWorking;

    private boolean isSick;


    public Employee(String name, Date birthdayDate, double salary, int level, EmployeeRole role) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.salary = salary;
        this.level = level;
        this.skillsRate = 2 * level;
        this.status = EmployeeStatus.UNHIRED;
        this.role = role;
        this.placeOfWorking = "null";
        this.fatigue = 0;
        this.isSick = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSkillsRate() {
        return skillsRate;
    }

    public void setSkillsRate(double skillsRate) {
        this.skillsRate = skillsRate;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public double getFatigue() {
        return fatigue;
    }

    public void setFatigue(double fatigue) {
        this.fatigue = fatigue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlaceOfWorking() {
        return placeOfWorking;
    }

    public void setPlaceOfWorking(String placeOfWorking) {
        this.placeOfWorking = placeOfWorking;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", salary=" + salary +
                ", skillsRate=" + skillsRate +
                ", status=" + status +
                ", role=" + role +
                ", fatigue=" + fatigue +
                ", level=" + level +
                ", placeOfWorking='" + placeOfWorking + '\'' +
                '}';
    }
}
