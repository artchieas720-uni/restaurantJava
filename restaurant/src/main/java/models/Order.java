package models;

import models.enums.OrderStatus;
import models.enums.OrderType;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private static int orderCounter = 0;
    private int orderId;
    private Customer customer;
    private List<Recipe> orderedRecipes;
    private double totalAmount;
    private OrderType orderType;
    private OrderStatus status;
    private LocalDateTime orderTime;

    public Order(Customer customer, List<Recipe> orderedRecipes,  OrderType orderType) {
        this.customer = customer;
        this.orderedRecipes = orderedRecipes;
        this.totalAmount = calculateTotalAmount();
        this.orderId = ++orderCounter;
        this.orderType = orderType;
        this.status = OrderStatus.WAITING;
        this.orderTime = LocalDateTime.now();
    }


    private double calculateTotalAmount() {
        double total = 0;
        for (Recipe recipe : orderedRecipes) {
            total += recipe.getSellingPrice();
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Recipe> getOrderedRecipes() {
        return orderedRecipes;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


}
