package dut.pd3.practics.practic_2.FinanceAnalyzer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class TransactionReportGenerator {

    private static final double STAR_VALUE = 1000.0;

    // Задача 4: Виведення балансу
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    // Задача 4: Кількість транзакцій за місяць
    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    // Задача 5: 10 найбільших витрат
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // Самостійна робота: Найбільша та найменша витрата за період
    public static void printPeriodExtremesReport(String startDate, String endDate,
                                                 Optional<Transaction> maxExpense,
                                                 Optional<Transaction> minExpense) {
        System.out.println("\nАналіз витрат за період з " + startDate + " по " + endDate + ":");
        maxExpense.ifPresentOrElse(
                t -> System.out.println("Найбільша витрата: " + t.getDescription() + " (" + t.getAmount() + " грн, дата: " + t.getDate() + ")"),
                () -> System.out.println("Найбільша витрата: витрат за цей період не знайдено")
        );
        minExpense.ifPresentOrElse(
                t -> System.out.println("Найменша витрата: " + t.getDescription() + " (" + t.getAmount() + " грн, дата: " + t.getDate() + ")"),
                () -> System.out.println("Найменша витрата: витрат за цей період не знайдено")
        );
    }

    // Самостійна робота: Візуалізація сумарних витрат по категоріях та місяцях символами '*'
    public static void printVisualExpensesReport(String title, Map<String, Double> expensesMap) {
        System.out.println("\n" + title + " (1 '*' = " + (int) STAR_VALUE + " грн):");
        expensesMap.forEach((key, totalAmount) -> {
            int starsCount = (int) Math.round(totalAmount / STAR_VALUE);
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.printf("%-22s | %8.2f грн | %s%n", key, totalAmount, stars);
        });
    }
}