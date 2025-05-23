package models;

import models.enums.EmployeeStatus;
import models.enums.EmployeeRole;
import models.helpers.ExpHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// May i add some stress modifier
public class Employee {
    private String name;
    private LocalDate birthdayDate;

    private double salary;
    private double skillsRate;
    private EmployeeStatus status;
    private EmployeeRole role;

    private double fatigue;
    private int level;
    private double exp;

    private String placeOfWorking;

    private boolean isSick;
    private LocalDateTime busyUntil;



    public Employee(String name, LocalDate birthdayDate, double salary, int level, EmployeeRole role) {
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
        this.exp = 0;
    }

    // LEVEL SYSTEM

    private int xpToLevelUp = ExpHelper.getXpForLevel(this.getLevel(), 300, 2, 7);

    public void employeeLevelUp(){
        if(this.getExp() >= getXpToLevelUp()){
            this.setExp(0);
            this.setLevel(this.getLevel()+1);
        }
    }

    public void gainExp(double exp){
        this.exp += exp;
    }

    public boolean isBusy(LocalDateTime now) {
        return busyUntil != null && now.isBefore(busyUntil);
    }

    public void assignToWork(int preparationTimeInMinutes, LocalDateTime currentTime) {
        this.busyUntil = currentTime.plusMinutes(preparationTimeInMinutes);
    }

    public LocalDateTime getBusyUntil() {
        return busyUntil;
    }

    public void setBusyUntil(LocalDateTime busyUntil) {
        this.busyUntil = busyUntil;
    }

    // GETTER AND SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
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

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getXpToLevelUp() {
        return xpToLevelUp;
    }

    public void setXpToLevelUp(int xpToLevelUp) {
        this.xpToLevelUp = xpToLevelUp;
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
