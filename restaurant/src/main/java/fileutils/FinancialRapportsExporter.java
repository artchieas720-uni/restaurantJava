package fileutils;

import java.io.BufferedWriter;

import models.enums.TransactionType;
import models.helpers.FinancialHelper.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FinancialRapportsExporter {

    public static void exportReportToTxt(String filename, List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"))) {
            writer.write("------ Financial Rapport ------\n\n");

            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }

            double income = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.INCOME)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double expenses = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.EXPENSES)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double net = income - expenses;

            writer.write("\n------ Summarise ------\n");
            writer.write(String.format("Income: %.2f zł\n", income));
            writer.write(String.format("Expenses:  %.2f zł\n", expenses));
            writer.write(String.format("Profit: %.2f zł\n", net));
            writer.write("------------------------------\n");

            System.out.println("✅ Report was exported to the file: " + filename + ".txt");
        } catch (IOException e) {
            System.out.println("❌ There was Error like: " + e.getMessage());
        }
    }

}
