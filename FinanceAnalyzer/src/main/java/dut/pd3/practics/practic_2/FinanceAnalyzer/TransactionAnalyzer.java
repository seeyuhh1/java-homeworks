package dut.pd3.practics.practic_2.FinanceAnalyzer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("MM-yyyy");

    // Задача 2: Розрахунок загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Задача 3: Підрахунок транзакцій за конкретний місяць і рік ("MM-yyyy")
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            String transactionMonthYear = date.format(MONTH_YEAR_FORMATTER);
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    // Задача 5: Визначення 10 найбільших витрат (від'ємні значення, найменше число = найбільша витрата)
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Самостійна робота: Найбільша витрата за вказаний період (включно з межами)
    public static Optional<Transaction> findMaxExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDateStr, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                // Найбільша витрата має найбільше від'ємне значення (наприклад, -9100 < -120)
                .min(Comparator.comparing(Transaction::getAmount));
    }

    // Самостійна робота: Найменша витрата за вказаний період (включно з межами)
    public static Optional<Transaction> findMinExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDateStr, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                // Найменша витрата найближча до нуля (наприклад, -120 > -9100)
                .max(Comparator.comparing(Transaction::getAmount));
    }

    // Самостійна робота: Сумарні витрати по категоріях (повертає додатні суми витрат для зручності звіту)
    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }

    // Самостійна робота: Сумарні витрати по місяцях ("MM-yyyy")
    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), DATE_FORMATTER).format(MONTH_YEAR_FORMATTER),
                        LinkedHashMap::new,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }
}