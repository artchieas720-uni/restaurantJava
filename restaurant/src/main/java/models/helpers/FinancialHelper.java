package models.helpers;

import models.Customer;
import models.enums.OrderType;
import models.enums.TransactionType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinancialHelper {

    public static class Transaction {
        private final LocalDateTime date;
        private final String description;
        private final double amount;
        private TransactionType type;
        private Customer customer;
        private OrderType orderType;

        public Transaction(LocalDateTime date, String description, double amount, TransactionType type, Customer customer, OrderType orderType) {
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.type = type;
            this.customer = customer;
            this.orderType = orderType;
        }

        public Transaction(LocalDateTime date, String description, double amount, TransactionType type) {
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.type = type;
        }

        public LocalDateTime getDate() { return date; }
        public String getDescription() { return description; }
        public double getAmount() { return amount; }
        public TransactionType getType() { return type; }
        public Customer getCustomer() { return customer; }
        public OrderType getOrderType() { return orderType; }

        @Override
        public String toString() {
            return "Transaction{" +
                    "date=" + date +
                    ", description='" + description + '\'' +
                    ", amount=" + amount +
                    ", type=" + type +
                    '}';
        }
    }

    private final List<Transaction> allTransactions = new ArrayList<>();

    public void addTransaction(LocalDateTime date, String description, double amount, TransactionType type, Customer customer, OrderType orderType) {
        allTransactions.add(new Transaction(date, description, amount, type, customer, orderType));
    }

    public void addTransaction(LocalDateTime date, String description, double amount, TransactionType type) {
        allTransactions.add(new Transaction(date, description, amount, type));
    }

    public double getTotalIncome(LocalDateTime from, LocalDateTime to) {
        return allTransactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpenses(LocalDateTime from, LocalDateTime to) {
        return allTransactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSES)
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance(LocalDateTime from, LocalDateTime to) {
        return getTotalIncome(from, to) - getTotalExpenses(from, to);
    }

    public List<Transaction> getTransactions() {
        return allTransactions;
    }

    public List<Transaction> getTransactionsForDay(LocalDate date) {
        return allTransactions.stream()
                .filter(t -> t.getDate().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsForWeek(LocalDate dateInWeek) {
        LocalDate startOfWeek = dateInWeek.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        return allTransactions.stream()
                .filter(t -> {
                    LocalDate txDate = t.getDate().toLocalDate();
                    return !txDate.isBefore(startOfWeek) && !txDate.isAfter(endOfWeek);
                })
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsForMonth(YearMonth month) {
        return allTransactions.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .collect(Collectors.toList());
    }

    public void printSummaryReport(List<Transaction> transactions) {
        System.out.println("------ RAPORT FINANSOWY ------");
        transactions.forEach(System.out::println);
        double income = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expenses = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSES)
                .mapToDouble(Transaction::getAmount)
                .sum();
        double net = income - expenses;
        System.out.printf("Przychody: %.2f zł\n", income);
        System.out.printf("Wydatki:  %.2f zł\n", expenses);
        System.out.printf("Zysk netto: %.2f zł\n", net);
        System.out.println("------------------------------");
    }



}
